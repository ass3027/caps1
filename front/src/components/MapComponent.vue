<template>
  <div class="map_wrap">
    <div
      :id="index"
      style="width:100%;height:100%;position:relative;overflow:hidden;"
    />
    <div
      v-if="schedule===undefined"
      id="menu_wrap"
      class="bg_white"
    >
      <div class="option">
        <div>
          <form @submit.prevent>
            키워드 : <input
              v-model="keyWord"
              type="text"
              size="15"
            >
            <button @click="searchPlaces">
              지도검색
            </button>
            <button @click="OurDbSearch">
              장소검색
            </button>
          </form>
        </div>
      </div>
      <hr>
      <ul id="placesList">
        <li
          v-for="(place,index) in places"
          :key="index"
          class="item"
        >
          <!--          이거 위에 넣을까... 근데 좀 이상함-->
          <!--          @mouseover="displayinfoWindow(markers[index],place.place_name)"-->
          <!--          @mouseout="infoWindow.close()"-->
          <span :class="[`markerbg marker_${index+1}`]" />
          <div class="info1">
            <h5 @click="logLocationInfo(place)">
              {{ place.place_name }}
            </h5>
            <template v-if="place.road_address_name">
              <span>{{ place.road_address_name }}</span>
              <span class="jibun gray">{{ place.address_name }}</span>
            </template>
            <span v-else>{{ place.address_name }}</span>
            <span class="tel">{{ place.phone }}</span>
          </div>
        </li>
      </ul>
      <div id="pagination" />
    </div>
  </div>
</template>

<script>
import {EventBus} from "@/eventBus/eventBus";
import axios from 'axios'

export default {
  name: "MapComponent",
  props:{
    schedule:Map,
    index:String,
    rec:String

  },
  data() {
    return {
      geocoder: {},
      marker: 0,
      infoWindow: {},
      map: {},
      markers: [],
      ps: {},
      keyWord: '복현',
      places: [],
      placePositionArray:[],
      path:{},
      pathMarkers:[],
      markerImage:{},
      eachDistance:[],
      checkDBSearch:false,
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6334d5e4689d4570c9306cc116099288&libraries=services,clusterer,drawing"

      document.head.appendChild(script);

      if(this.schedule===undefined){
        EventBus.$on("updateDate", (date) => {
          const schedule = this.$store.state.calendar.calendar.date[date]
          this.setPathAndShow(schedule)
          EventBus.$emit('passDistance',this.eachDistance)
        })
      }
    }

    if(this.rec!=undefined){
      this.keyWord = this.rec
      this.OurDbSearch()
    }


  },
  methods: {
    setPathAndShow(schedule){
      this.placePositionArray = []
      this.removePathMarker()

      let tempMarker
      let index=1
      let distanceOverlay
      let position
      for(let it of schedule.values()){
        if(it.mapX!==0&&it.mapY!==0){
          position = new kakao.maps.LatLng(it.mapX,it.mapY)

          this.placePositionArray.push(position)

          tempMarker =  new kakao.maps.Marker({
            position:position,
            image:this.markerImage
          })
          tempMarker.setMap(this.map)

          distanceOverlay = new kakao.maps.CustomOverlay({
            content: `<div style="position:relative;bottom:10px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;font-size:12px;padding:5px;background:#fff;"><span class="number">${index}번째</span></div>`,
            position: new kakao.maps.LatLng(it.mapX+0.001,it.mapY),
            yAnchor: 1,
            zIndex: 2
          });
          distanceOverlay.setMap(this.map)

          this.pathMarkers.push({'marker':tempMarker,'overlay':distanceOverlay})
          index++
        }
      }
      this.getEachDistance()
      this.path.setPath(this.placePositionArray)
    },
    dd(index) {
      this.markers[index].setMap(this.map)
    },
    initMap() {
      const container = document.getElementById(this.index);
      navigator.geolocation.getCurrentPosition((position)=>{

        let centerPosition;

        if(this.schedule===undefined) {
          centerPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude)
        }
        else {
          centerPosition = new kakao.maps.LatLng(this.schedule.values().next().value.mapX, this.schedule.values().next().value.mapY)
        }

        let center = centerPosition
        const options = {
          center,
          level: 5,
        };

        //마커이미지 이벤트버스 리스너에서 쓸거
        this.markerImage = new kakao.maps.MarkerImage(
          'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png',
          new kakao.maps.Size(31, 35),
          {offset: new kakao.maps.Point(14, 35)}
        )

        //장소 검색 객체 생성
        this.ps = new kakao.maps.services.Places();

        // 주소-좌표 변환 객체를 생성합니다
        this.geocoder = new kakao.maps.services.Geocoder();

        //지도 객체를 등록합니다.
        //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
        this.map = new kakao.maps.Map(container, options);

        // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
        var mapTypeControl = new kakao.maps.MapTypeControl();

        // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
        // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
        this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

        // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
        var zoomControl = new kakao.maps.ZoomControl();

        this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

        this.marker = new kakao.maps.Marker({
          position: center
        })

        this.marker.setMap(this.map);

        this.marker.setDraggable(true);

        this.infoWindow = new kakao.maps.InfoWindow({zindex: 1});

        if(this.schedule===undefined){
          kakao.maps.event.addListener(this.map, 'click', (mouseEvent) => {


            var clickPosition = mouseEvent.latLng;


            this.marker.setPosition(clickPosition);

            this.searchDetailAddrFromCoords(clickPosition, (result, status) => {
              if (status === kakao.maps.services.Status.OK) {
                //var detailAddr = (result[0].road_address==undefined) ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                let detailAddr = '';
                let mapAddress = 0;

                if (result[0].address === undefined && result[0].road_address === undefined) {
                  detailAddr += `<div>주소를 찾을 수 없습니다</div>`
                } else {
                  if (result[0].road_address !== undefined) {
                    mapAddress = result[0].road_address
                    detailAddr += '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>'
                  }
                  mapAddress = result[0].address.address_name
                  detailAddr += '<div >지번 주소 : ' + result[0].address.address_name + '</div>';
                }

                const content = '<div >' + detailAddr + '</div>';


                this.infoWindow.setContent(content);

                this.infoWindow.open(this.map, this.marker);

                const mapData = {
                  address: mapAddress,
                  mapY: clickPosition.La,
                  mapX: clickPosition.Ma,
                  pl_id:undefined
                }

                if (mapAddress !== 0) {
                  this.$store.commit('calendar/updateCalendarDate', mapData)
                }
              }
            })
          })
        }else {
          kakao.maps.event.addListener(this.map, 'click', (mouseEvent) => {


            var clickPosition = mouseEvent.latLng;

          })
        }

        //경로 띄우기

        this.showPath()

        if(this.schedule!==undefined){
          this.setPathAndShow(this.schedule)
        }
      });



    },
    showPath(){

      this.path = new kakao.maps.Polyline({
        map:this.map,
        path:this.placePositionArray,
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: '#db4040', // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid' // 선의 스타일입니다
      })
    },
    getEachDistance(){
      this.eachDistance = []
      const tempPolyline = new kakao.maps.Polyline({
        map:null,
        path:[],
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: '#db4040', // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid' // 선의 스타일입니다
      })
      for(let i=0;i<this.placePositionArray.length-1;i++){
        tempPolyline.setPath(this.placePositionArray.slice(i,i+2))
        this.eachDistance.push( Math.floor( tempPolyline.getLength() ) )
      }
    },
    // showDistance(){
    //
    // }
    searchAddrFromCoords(coords, callback) {
      this.geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    },
    searchDetailAddrFromCoords(coords, callback) {
      this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    },

    searchPlaces() {
      this.checkDBSearch = false
      if (!this.keyWord.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요')
        return false;
      }

      this.ps.keywordSearch(this.keyWord, this.placesSearchCB);
    },

    OurDbSearch(){
      this.checkDBSearch = true
      axios.get(`/api/place/${this.keyWord}`)
        .then( (res)=>{
          const converted = []

          for(let i=0,it;i<30&&i<res.data.length;i++){
            it=res.data[i]
            converted.push({
              "address_name":it.addr1,
              "category_group_code":it.cat1,
              "category_group_name":it.cat2,
              "category_name":it.cat3,
              "distance":undefined,
              "id":it.pl_id,
              "phone":it.store_phone,
              "place_name":it.title,
              "place_url":undefined,
              "road_address_name":it.addr2,
              "x":it.mapx,
              "y":it.mapy,
            })
          }
          this.placesSearchCB(converted,kakao.maps.services.Status.OK)
        })
    },

    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        this.displayPlaces(data);

        //this.displayPagination(pagination);

      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다')

      } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다');
      }
    },
    displayPlaces(places) {
      this.places = places;
      var menuEl = document.getElementById('menu_wrap'),
        bounds = new kakao.maps.LatLngBounds();
      //listStr = '';


      this.removeMarker();

      for (let i = 0; i < places.length; i++) {
        //마커 생성 및 지도 표시
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
        // itemEl = this.getListItem(i, places[i]); //검색 결과 항목 element 생성
        this.addMarker(placePosition, i);
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);
        kakao.maps.event.addListener(this.markers[i], 'mouseover', () => {
          this.displayinfowindow(this.markers[i], places[i].place_name);
        });

        kakao.maps.event.addListener(this.markers[i], 'mouseout', () => {
          this.infoWindow.close();
        })
      }

      menuEl.scrollTop = 0;
      this.map.setBounds(bounds);
    },


    addMarker(position, idx, title) {
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png',
        imageSize = new kakao.maps.Size(36, 37),
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691),
          spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10),
          offset: new kakao.maps.Point(13, 37)
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage
        });
      title + 1//????에러피하기
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      marker.setDraggable(true);
      this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    //귀찮아서 이렇게 할게요 ㅠ
    removePathMarker() {

      for (let i = 0; i < this.pathMarkers.length; i++) {
        this.pathMarkers[i].marker.setMap(null);
        this.pathMarkers[i].overlay.setMap(null);
      }
      this.pathMarkers = [];
    },
    removeMarker2(index) {

      this.markers[index].setMap(null);
    },
    displayPagination(pagination) {

      var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = 'on';
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            }
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },

    displayinfoWindow(marker, title) {
      let content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

      this.infoWindow.setContent(content);
      this.infoWindow.open(this.map, marker);
    },

    logLocationInfo(place) {

      // 이동할 위도 경도 위치를 생성합니다
      let moveLatLon = new kakao.maps.LatLng(place.y, place.x);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.map.panTo(moveLatLon);
      this.map.setLevel(3)
      let id = undefined
      if(this.checkDBSearch){
        id= place.id
      }
      const mapData = {
        address: place.address_name,
        mapY: place.x,
        mapX: place.y,
        pl_id:id
      }
      this.$store.commit('calendar/updateCalendarDate', mapData)

    },
//dd
//     distanceView(markers) {
//       this.clickLine = new kakao.maps.Polyline({
//         map: this.map, // 선을 표시할 지도입니다
//         path: markers, // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
//         strokeWeight: 3, // 선의 두께입니다
//         strokeColor: '#db4040', // 선의 색깔입니다
//         strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
//         strokeStyle: 'solid' // 선의 스타일입니다
//       });
//     }

    // removeAllChildNods(el) {
    //   while (el.hasChildNodes()) {
    //     el.removeChild(el.lastChild);
    //   }
    // }

  }

}
</script>

<style scoped>
.dot {overflow:hidden;float:left;width:12px;height:12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');}
.dotOverlay {position:relative;bottom:10px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;font-size:12px;padding:5px;background:#fff;}
.dotOverlay:nth-of-type(n) {border:0; box-shadow:0 1px 2px #888;}
.number {font-weight:bold;color:#ee6152;}
.dotOverlay:after {content:'';position:absolute;margin-left:-6px;left:50%;bottom:-8px;width:11px;height:8px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')}
.distanceInfo {position:relative;top:5px;left:5px;list-style:none;margin:0;}
.distanceInfo .label {display:inline-block;width:50px;}
.distanceInfo:after {content:none;}

.map {
  width: 40%;
  height: 40%;
  float: left;
}

.map_wrap, .map_wrap * {
  margin: 0;
  padding: 0;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  font-size: 12px;
}

.map_wrap a, .map_wrap a:hover, .map_wrap a:active {
  color: #000;
  text-decoration: none;
}

.map_wrap {
  position: relative;
  width: 100%;
  height: 100%;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.bg_white {
  background: #fff;
}

#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5F5F5F;
  margin: 3px 0;
}

#menu_wrap .option {
  text-align: center;
}

#menu_wrap .option p {
  margin: 10px 0;
}

#menu_wrap .option button {
  margin-left: 5px;
}

#placesList li {
  list-style: none;
}

#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}

#placesList .item span {
  display: block;
  margin-top: 4px;
}

#placesList .item h5, #placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

#placesList .item .info1 {
  padding: 10px 0 10px 55px;
}

#placesList .info1 .gray {
  color: #8a8a8a;
}

#placesList .info1 .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}

#placesList .info1 .tel {
  color: #009900;
}

#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
}

#placesList .item .marker_1 {
  background-position: 0 -10px;
}

#placesList .item .marker_2 {
  background-position: 0 -56px;
}

#placesList .item .marker_3 {
  background-position: 0 -102px
}

#placesList .item .marker_4 {
  background-position: 0 -148px;
}

#placesList .item .marker_5 {
  background-position: 0 -194px;
}

#placesList .item .marker_6 {
  background-position: 0 -240px;
}

#placesList .item .marker_7 {
  background-position: 0 -286px;
}

#placesList .item .marker_8 {
  background-position: 0 -332px;
}

#placesList .item .marker_9 {
  background-position: 0 -378px;
}

#placesList .item .marker_10 {
  background-position: 0 -423px;
}

#placesList .item .marker_11 {
  background-position: 0 -470px;
}

#placesList .item .marker_12 {
  background-position: 0 -516px;
}

#placesList .item .marker_13 {
  background-position: 0 -562px;
}

#placesList .item .marker_14 {
  background-position: 0 -608px;
}

#placesList .item .marker_15 {
  background-position: 0 -654px;
}

#pagination {
  margin: 10px auto;
  text-align: center;
}

#pagination a {
  display: inline-block;
  margin-right: 10px;
}

#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>

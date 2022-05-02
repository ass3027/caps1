<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width:100%;height:100%;position:relative;overflow:hidden;"
    />
    <div
      id="menu_wrap"
      class="bg_white"
    >
      <div class="option">
        <div>
          <form @submit.prevent="searchPlaces()">
            키워드 : <input
              v-model="keyword"
              type="text"
              size="15"
            >
            <button type="submit">
              검색하기
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
          @mouseover="displayInfowindow(marker[index],place.place_name)"
          @mouseout="infowindow.close()"
        >
          <span :class="[`markerbg marker_${index+1}`]" />
          <div class="info1">
            <h5>{{ place.place_name }}</h5>
            <template v-if="place.road_address_name">
              <span>{{ place.road_address_name }}</span>
              <span class="jibun gray">{{ place.adress_name }}</span>
            </template>
            <span v-else>{{ place.address_name }}</span>
            <span class="tel">{{ place.phone }}</span>
          </div>
        </li>
      </ul>
      <div id="pagination" />
    </div>
    <ul
      v-for="(data,index) in [0,1,2,3,4,5]"
      :key="index"
    >
      <button @click="removeMarker2(index)">
        {{ data }}ss
      </button>
      <button @click="dd(index)">
        {{ data }}bvbv
      </button>
    </ul>
  </div>
</template>

<script>
export default {
  name: "MapComponent",
  data() {
    return {
      geocoder  : {},
      marker    : 0,
      infowindow: {},
      map       : {},
      markers   : [],
      ps        : {},
      keyword   : '',
      places    : []
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

    }
  },
  methods: {
    dd(index){
      this.markers[index].setMap(this.map)
    },
    initMap() {
      const container = document.getElementById("map");

      var center = new kakao.maps.LatLng(35.895552292215164, 128.62142110909582)
      const options = {
        center,
        level: 5,
      };

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

      this.infowindow = new kakao.maps.InfoWindow({zindex: 1});


      kakao.maps.event.addListener(this.map, 'click', (mouseEvent) => {


        var clickPosition = mouseEvent.latLng;


        this.marker.setPosition(clickPosition);

        this.searchDetailAddrFromCoords(clickPosition, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            console.log(result)
            console.log(clickPosition)
            //var detailAddr = (result[0].road_address==undefined) ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            var detailAddr = '';
            if (result[0].road_address !== undefined) {
              detailAddr += '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>'
            }
            detailAddr += '<div >지번 주소 : ' + result[0].address.address_name + '</div>';

            var content = '<div class="bAddr">' + detailAddr + '</div>';

            // var infowindow = new kakao.maps.InfoWindow({
            //   position : clickPosition,
            //   content : finalAddr
            // });

            this.infowindow.setContent(content);

            this.infowindow.open(this.map, this.marker);

            if (this.selectedTag !== '') {
              const info = this.selectedTag.childNodes[1];
              info.textContent = result[0].address.address_name
            }
          }

        })


      })
    },
    searchAddrFromCoords(coords, callback) {
      this.geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    },
    searchDetailAddrFromCoords(coords, callback) {
      this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    },

    searchPlaces() {

      if (!this.keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요')
        return false;
      }

      this.ps.keywordSearch(this.keyword, this.placesSearchCB);
    },

    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        this.displayPlaces(data);

        this.displayPagination(pagination);

      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다')

      } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다');
      }
    },

    displayPlaces(places) {
      this.places = places;
      console.log(places.length)
      var menuEl = document.getElementById('menu_wrap'),
          bounds = new kakao.maps.LatLngBounds();
      //listStr = '';


      this.removeMarker();

      for (var i = 0; i < places.length; i++) {
        //마커 생성 및 지도 표시
        console.log(1)
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);

        // itemEl = this.getListItem(i, places[i]); //검색 결과 항목 element 생성

        this.addMarker(placePosition, i);

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        kakao.maps.event.addListener(this.markers[i], 'mouseover', () => {
          this.displayInfowindow(this.markers[i], places[i].place_name);
        });

        kakao.maps.event.addListener(this.markers[i], 'mouseout', () => {
          this.infowindow.close();
        })


      }

      menuEl.scrollTop = 0;
      console.log(this.markers)
      this.map.setBounds(bounds);
    },


    addMarker(position, idx, title) {
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png',
          imageSize = new kakao.maps.Size(36,37),
          imgOptions = {
            spriteSize  : new kakao.maps.Size(36, 691),
            spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10),
            offset      : new kakao.maps.Point(13, 37)
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image   : markerImage
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
    removeMarker2(index){
      console.log(this.markers)
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
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },

    // removeAllChildNods(el) {
    //   while (el.hasChildNodes()) {
    //     el.removeChild(el.lastChild);
    //   }
    // }

  }

}
</script>

<style scoped>
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
  height: 500px;
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

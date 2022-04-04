<template>
<<<<<<< Updated upstream
  <div id="map" style="width:800px;height:600px;"></div>
=======
  <div>
    <div
      id="map"
      style="width:800px;height:600px;float:left"
    />
    <div
      id="plan"
      style="float:right"
    >
      <input
        v-model="startDate"
        type="date"
      >
      <input
        v-model="endDate"
        type="date"
      >
      <button @click="apply()">
        Apply
      </button>
      <div v-if="buttonClicked">
        <DateComponent
          v-for="(date,index) in dateArr "
          :id="index+`s`"
          :key="index"
          :date="date"
          @select="selecting"
        />
      </div>
    </div>
  </div>
>>>>>>> Stashed changes
</template>

<script>
export default {
  name: 'MapView',
  data()  {
    return {
      geooder: '',
      markers:[],
      markerCount:0,

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
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level : 5,
      };

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

      kakao.maps.event.addListener(this.map,'click', (mouseEvent) => {


        var clickPosition = mouseEvent.latLng;


        this.markers[this.markerCount] = new kakao.maps.Marker({
          position: clickPosition
        });
        if(this.markerCount>0){
          this.markers[this.markerCount-1].setMap(null);
        }

        this.markers[this.markerCount].setMap(this.map);

        this.markers[this.markerCount].setDraggable(true);

        this.searchDetailAddrFromCoords(clickPosition, (result,status)=>{
          if (status === kakao.maps.services.Status.OK) {
            console.log(result)
            //var detailAddr = (result[0].road_address==undefined) ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            var detailAddr='';
            if(result[0].road_address!=undefined) {
              detailAddr+='<div>도로명주소 : ' + result[0].road_address.address_name + '</div>'
            }
            detailAddr += '<div >지번 주소 : ' + result[0].address.address_name + '</div>';

            var content = '<div class="bAddr">' + detailAddr + '</div>';

            // var infowindow = new kakao.maps.InfoWindow({
            //   position : clickPosition,
            //   content : finalAddr
            // });
            var infowindow = new kakao.maps.InfoWindow({zindex:1});
            infowindow.setContent(content);

            infowindow.open(this.map, this.markers[this.markerCount]);

            this.markerCount++

          }

        })


      })
    },
    searchAddrFromCoords(coords, callback){
      this.geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    },
    searchDetailAddrFromCoords(coords, callback){
      this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }
  }
}
</script>
<style>
  .bAddr {
    padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;border-radius: 2px;
  }
</style>


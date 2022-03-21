<template>
  <div>
    <div id="map" style="width:800px;height:600px;float:left"></div>
    <div id="plan" style="float:right">
      <input type="date" v-model="startDate">
      <input type="date" v-model="endDate">
      <button @click="apply()">Apply</button>
      <div v-if="buttonClicked">
        <DateComponent v-for="(date,index) in dateArr "
                       :key="index"
                       :date="date"
                       :id="index+`s`"
                       @select="selecting"
        >

        </DateComponent>
      </div>
    </div>
  </div>

</template>

<script>
/* eslint-disable */
import DateComponent from '@/components/DateComponent'
/* eslint-disable */

export default {


  name      : 'MapView',
  components: {
    DateComponent
  },
  data() {
    return {
      geooder      : '',
      marker       : 0,
      startDate    : new Date("2022-03-01T00:00:00.000Z"),
      endDate      : new Date("2022-03-11T00:00:00.000Z"),
      startDateC   : new Date(),
      endDateC     : new Date(),
      dateArr      : [],
      buttonClicked: false,
      selectedTag: '',
      infowindow:{},

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
      this.startDate = new Date("2022-03-01T00:00:00.000Z");
      this.endDate = new Date("2022-03-11T00:00:00.000Z")
      this.apply();

    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");

      var center = new kakao.maps.LatLng(35.895552292215164, 128.62142110909582)
      const options = {
        center,
        level: 5,
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

      this.marker = new kakao.maps.Marker({
        position: center
      })

      this.marker.setMap(this.map);

      this.marker.setDraggable(true);

      var infowindow = new kakao.maps.InfoWindow({zindex: 1});


      kakao.maps.event.addListener(this.map, 'click', (mouseEvent) => {


        var clickPosition = mouseEvent.latLng;


        this.marker.setPosition(clickPosition);

        this.searchDetailAddrFromCoords(clickPosition, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            console.log(result)
            console.log(clickPosition)
            //var detailAddr = (result[0].road_address==undefined) ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            var detailAddr = '';
            if (result[0].road_address != undefined) {
              detailAddr += '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>'
            }
            detailAddr += '<div >지번 주소 : ' + result[0].address.address_name + '</div>';

            var content = '<div class="bAddr">' + detailAddr + '</div>';

            // var infowindow = new kakao.maps.InfoWindow({
            //   position : clickPosition,
            //   content : finalAddr
            // });

            infowindow.setContent(content);
            this.infowindow = infowindow

            infowindow.open(this.map, this.marker);

            if(this.selectedTag!=''){
              const info = this.selectedTag.childNodes[1];
              info.textContent=result[0].address.address_name
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
    apply() {
      this.dateArr = []
      this.buttonClicked = true
      this.startDateC = new Date(this.startDate)
      this.endDateC = new Date(this.endDate)
      if (this.startDateC > this.endDateC) {
        alert("잘못된 날짜 설정 입니다");
        return;
      }

      var tempDate = this.startDateC
      for (var i = 0; tempDate <= this.endDateC; i++) {
        this.dateArr[i] = this.dateFormat(tempDate)//tempDate.format("yyyy-MM-dd")
        tempDate.setDate(tempDate.getDate() + 1)
      }
      //var tags = []
      //for
    },
    selecting(tag)  {
        var check = this.sameCheck(tag)
        if(check) this.selectedTag = tag;
        console.log(tag);
        return check
    },
    sameCheck(tag){
      if(this.selectedTag != tag) return true;
      else return false
    },

    dateFormat(date) {
      let month = date.getMonth() + 1;
      let day = date.getDate();
      let hour = date.getHours();
      let minute = date.getMinutes();
      let second = date.getSeconds();

      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;
      hour = hour >= 10 ? hour : '0' + hour;
      minute = minute >= 10 ? minute : '0' + minute;
      second = second >= 10 ? second : '0' + second;

      return date.getFullYear() + '-' + month + '-' + day;
    }
  }
}
</script>
<style>
.bAddr {
  padding: 5px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  border-radius: 2px;
}
</style>


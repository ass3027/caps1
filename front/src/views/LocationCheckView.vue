<template>
  <div>
    <h1>Location Check {{ ord_id }}</h1>
    <div id="map" style="width:100%;height:350px;"></div>
    <p>{{ latitude }}</p>
    <p>{{ longitude }}</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LocationCheckview',
  props:['ord_id'],
  data() {
    return {
      duser_id: null,
      latitude: null,
      longitude: null,
      map: null,
      marker: null,
    }
  },
  mounted() {
    this.findDuserIdByOrdId()
    let locationGet = setInterval(this.getLocation, 3000)

    if(window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }

    setInterval(this.setMarker, 3000)

  },
  methods: {

    findDuserIdByOrdId() {
      const sendform = new FormData();

      axios({
        method  : 'get',
        url     : '/api/duserid/ordid?ord_id=' + this.ord_id ,
        headers: {
          'Content-Type' : 'application/json'
        }
      }).then(res => {
        this.duser_id = res.data
        // console.log("duser_id: " + this.duser_id)
      })


    },
    getLocation() {

      axios({
        method : 'get',
        url    : '/api/location/check',
        params :  {'duser_id': this.duser_id},
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((res) => {
          console.log("[getLocation()] res.data: " + JSON.stringify(res.data))
          console.log("[getLocation()] Latitude: " + res.data.LATITUDE)
          this.latitude = res.data.LATITUDE + ""
          console.log("[getLocation()] Longitude: " + res.data.LONGITUDE)
          this.longitude = res.data.LONGITUDE + ""
        })

    },
    initMap() {
      const mapContainer = document.getElementById("map");
      const mapOptions = {
        center: new kakao.maps.LatLng(35.89926019, 128.62150819),
        level: 5,
      };

      this.map = new kakao.maps.Map(mapContainer, mapOptions)
    },
    setMarker() {
      var lat = this.latitude
      var lon = this.longitude

      var map = this.map

      console.log("[setMarker] Latitude: " + lat)
      console.log("[setMarker] Longitude: " + lon)

      const regExp = /^[\-0-9\.]+$/;

      if( !(lat === '' || lon === ''
        || !regExp.test(lat) || !regExp.test(lon)
        || lat.split('.').length > 2 || lon.split('.').length > 2
        || +lat > 90 || +lat < -90
        || +lon > 180 || +lon < -180) ){
          if(this.marker == null) this.marker = new kakao.maps.Marker();
          else this.marker.setMap(null);
        if(map != null) {
            this.marker.setPosition(new kakao.maps.LatLng(lat, lon));

            this.marker.setMap(map);
          }else return;
      }else {
        if(this.marker != null)
          this.marker.setMap(null);
          return;
      };

      // this.setMapCenter()

    },
    setMapCenter() {
      const mapContainer = document.getElementById("map");
      const mapOptions = {
        center: new kakao.maps.LatLng(this.latitude, this.longitude),
        level: 5,
      };

      this.map = new kakao.maps.Map(mapContainer, mapOptions)
    }
  }
}

</script>

<style scoped>

</style>

<template>
  <div>
    <h1>Location update {{ duser_id }}</h1>
    <div
      id="map"
      style="width:100%;height:350px;"
    />
    <p>{{ latitude }}</p>
    <p>{{ longitude }}</p>
    <p>
      <button @click="setCenter(33.450580, 126.574942)">
        aa
      </button>
    </p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LocationUpdate',
  props:['duser_id'],
  data() {
    return {
      latitude: null,
      longitude: null,
      container: null,
    }
  },
  created() {




  },
  mounted() {

    if(window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      /* global kakao */
      script.async = true;
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }

    setInterval(this.getLocation, 3000)


  },
  methods: {
    locationUpdate(){
      axios.post('/api/location/update', {
        'user_id': this.duser_id,
        'latitude': this.latitude,
        'longitude': this.longitude,
      })
    },
    initMap() {
      this.container = document.getElementById("map");

      const options = {
        center: new window.kakao.maps.LatLng(35.89926019, 128.62150819),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options)
    },
    getLocation() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude.toString()
        this.longitude = position.coords.longitude.toString()

        this.setMarker()

        this.locationUpdate()
      })
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

    },
    setCenter(lat, lng){
      console.log('setCenter', lat, lng)

      const moveLatLon = new kakao.maps.LatLng(lat, lng);

      var map = this.map
      map.setCenter(lat, lng)
    }


  }
}

</script>

<style scoped>

</style>

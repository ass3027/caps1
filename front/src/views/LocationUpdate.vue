<template>
  <div>
    <h1>Location Check {{ duser_id }}</h1>
    <div
      id="map"
      style="width:100%;height:350px;"
    />
    <p>{{ latitude }}</p>
    <p>{{ longitude }}</p>
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
    }
  },
  mounted() {

    var locationGet = setInterval(this.getLocation, 3000)


  },
  methods: {
    locationUpdate(){

      axios.post('/api/location/update', {
        'user_id': this.duser_id,
        'latitude': this.latitude,
        'longitude': this.longitude,
      })
    },
    getLocation() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude.toString()
        this.longitude = position.coords.longitude.toString()

        this.locationUpdate()
      })

    }


  }
}

</script>

<style scoped>

</style>

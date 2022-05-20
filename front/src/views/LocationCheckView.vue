<template>
  <div>
    <h1>Location Check {{ ord_id }}</h1>
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
  name: 'LocationCheckview',
  props:['ord_id'],
  data() {
    return {
      duser_id: null,
      latitude: null,
      longitude: null,
    }
  },
  mounted() {
    console.log("[mounted]ord_id" + this.ord_id)
    this.findDuserIdByOrdId()
    let get = setInterval(this.getLocation, 3000)

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
        console.log("res.data: " + res.data)
        this.duser_id = res.data
        console.log("duser_id: " + this.duser_id)
      })


    },
    getLocation() {

      console.log("---getLocation()---")
      console.log(this.duser_id)
      axios({
        method : 'get',
        url    : '/api/location/check',
        params :  {'duser_id': this.duser_id},
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((res) => {
          console.log(res.data)
          console.log("[getLocation()]" + res.data.latitude)
          this.latitude = res.data.latitude
          console.log("[getLocation()]" + res.data.longitude)
          this.longitude = res.data.longitude
        })

      console.log("---getLocation()---")
    },
  }
}

</script>

<style scoped>

</style>

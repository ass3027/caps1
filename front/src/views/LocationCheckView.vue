<template>
  <div>
    <h1>Location Check {{this.ord_id}}</h1>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LocationCheckview',
  data() {
    return {
      ord_id: null,
      duser_id: null,
      latitude: null,
      longitude: null,
    }
  },
  mounted() {
    this.ord_id = this.$route.params.ord_id
    console.log("ord_id: " + this.ord_id)
    this.findDuserIdByOrdId()
    this.getLocation()
  },
  methods: {

    findDuserIdByOrdId(ord_id) {
      const sendform = new FormData();
      console.log("ord_id: " + this.ord_id)

      sendform.append('ord_id', this.ord_id)

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

      console.log("getLocation()")
      axios({
        method : 'get',
        url    : '/api/location/check?user_id=' + this.duser_id,
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((res) => {
          console.log(res.data)
        })
    },
  }
}

</script>

<style scoped>

</style>

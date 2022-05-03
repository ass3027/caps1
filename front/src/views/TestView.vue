<template>
  <div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'TestView',
  data() {
    return {
      accessStatus:false,
      connection: null,
      currentPosition: null,
    }
  },
  methods: {
    getList: function () {

      console.log("hi")
      axios({
        method : 'get',
        url    : '/api/test',
        headers: {
          'Content-Type': 'application/json',
        },
        data   : '',
      })
        .then((res) => {
          res.data.forEach(function (i){
            console.log(i.supl_id.supl_name)
          })
        })
    },
    getLocation: function () {
      if (navigator.geolocation) { // GPS를 지원하면
        navigator.geolocation.getCurrentPosition(function(position) {
          var currentPosition = position.coords.latitude + ' ' + position.coords.longitude;
          console.log(currentPosition);

          var sendform = new FormData()
          sendform.append('latitude', position.coords.latitude.toString())
          sendform.append('longitude', position.coords.longitude.toString())


          axios({
            method : 'post',
            url    : '/api/location',
            headers: {
              'Content-Type': 'application/json',
            },
            data   : sendform,
          })

          return currentPosition;

        }, function(error) {
          console.error(error);
        }, {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
        });
      } else {
        alert('GPS를 지원하지 않습니다');
      }

    }
  },
  mounted() {
    this.getLocation()
    this.currentPosition = setInterval(this.getLocation, 500)
  }
}

</script>

<style scoped>

</style>

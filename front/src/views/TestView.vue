<template>
  <div style="width: 1050px">
    <ReviewCreateView :type="'pay_id'" :id="'132'"/>


  </div>
</template>

<script>
import axios from "axios";
import ReviewCreateView from "@/views/ReviewCreateView";

export default {

  name: 'TestView',
  components: {
    ReviewCreateView,
  },

  data() {
    return {
      userId: this.$store.state.user.userId,
      user: Object
    }
  },
  method: {
    scrollEvents() {
      this.scroll = window.scrollY
    }
  },
  created() {
    axios({
      method: 'GET',
      url: '/api/user/find',
      params: {
        'user_id': this.userId
      }
    }).then(res => {
      this.user = res.data
    })
  },
  mounted() {
    console.log("[current_user_id]" + this.userId)

    // setInterval(function () {
    //   console.log(window.scrollY)
    // }, 300)

  },

}

</script>

<style>

</style>

<template>


  <div style="width: 1050px">

    <!--    <DuserOrdersComponent/>-->
    <!--      <OrdersComponent :user_id="user_id" />-->
    <ProductReviewView :id="'100'" :type="'product'"/>
  </div>
</template>

<script>
// import OrdersComponent from "@/components/OrdersComponent";
import DuserOrdersComponent from "@/components/order/DuserOrdersComponent";
import axios from "axios";
import ProductReviewView from "@/views/ReviewView";
// import OrdersComponent from "@/components/OrdersComponent";



export default {

  name: 'TestView',
  components: {
    ProductReviewView
    // OrdersComponent,
    // DuserOrdersComponent
    // OrdersComponent

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

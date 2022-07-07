<template>
  <div style="width: 1050px">
    <DuserOrdersComponent/>
    <!--          <OrdersComponent :user_id="user_id" />-->
    <!--    <ReviewView :id="'100'" :type="'all'"/>-->

    <!--    <ReviewCreateView :id="'um'" :type="'guide'"/>-->
    <!--    <ReviewCreateView />-->


    <v-card>
      <v-card-actions style="margin-bottom: 10px">
        <v-rating
          v-model="rev_rating"
          background-color="gray"
          color="yellow accent-4"
          dense
          half-increments
          size="30"
        />
      </v-card-actions>

      <v-row class="mx-auto">
        <v-col cols="12">
          <v-textarea
            v-model="rev_content"
            placeholder="리뷰는 최대 1,000자까지 등록 가능합니다."
          />
        </v-col>
        <v-spacer />
        <v-col>
          <input
            ref="refImage"
            type="file"
            placeholder="photo"
            @change="imageSet($event)"
          >

          <div
            id="pictures"
            style="width: 150px; height: 150px"
          />
        </v-col>
      </v-row>

      <v-card-actions>
        <v-spacer />
        <v-btn
          width="150px"
          @click="onSubmit"
        >
          리뷰 등록
        </v-btn>
      </v-card-actions>
    </v-card>
    <input v-model="rev_photo">
  </div>
</template>

<script>
import axios from "axios";
import DuserOrdersComponent from "@/components/order/DuserOrdersComponent";

export default {

  name: 'TestView',
  components: {


    // ReviewView
    // OrdersComponent,
    DuserOrdersComponent


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

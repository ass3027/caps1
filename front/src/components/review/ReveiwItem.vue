<template>
  <li
      class="ma-0 py-5 pr-5"
    style="width: 100%; height: 300px"
  >
    <div class="review-lists">
      <div class="review-user">

        <div>{{user.user_name}}</div>
      </div>
      <v-list-item-content>
        <v-row>
          <v-col
            cols="12"
            class="mb-0 pb-0"
          >
            <v-rating
              v-model="rev_rating"
              background-color="purple lighten-3"
              color="purple"
              readonly
              small
            />
          </v-col>

          <p class="text-body  red--text">
            {{ reg_date }}
          </p>



        </v-row>
        <v-list-item-subtitle>
          {{ review.rev_content }}
        </v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="150"
        color="grey"
      />
    </div>
  </li>
</template>

<script>

import axios from "axios";

export default {
  name:'ReviewItem',
  props: {
    review:{
      type: Object
    }
  },
  data() {
    return {
      rev_rating: this.review.rev_rating,
      user: null,
    }
  },
  computed: {
    reg_date: function() {
      return this.review.reg_date.substring(0,10);
    }
  },
  mounted() {
    axios({
      method: 'GET',
      url:'http://localhost:8080/api/user/find',
      params: {
        user_id: this.review.user_id
      }

    })
      .then(res => {
        console.log("[user/find]" + res.data)
        this.user = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    console.log(this.review.rev_content)
  }
}

</script>

<template>
  <div>
    <h3>Review:</h3>
    <ul>
      <li
        v-for="(review, index) in reviews"
        :key="index"
      >
        {{ review.name }} gave this {{ review.rating }} stars
        <br>
        "{{ review.review }}"
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name:'ReviewList',
  props: {
    reviews:{
      type:Array,
      required:true
    }
  },
  data() {
    return {
      reivews: [],

    }
  },created() {
    console.log('created')
    axios('/api/allReview')
      .then(res => {
        console.log(res.data)
      })
  }

}
</script>
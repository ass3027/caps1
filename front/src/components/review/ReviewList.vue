<template>
  <v-container class="px-5">
    <div>
      <h3>Review {{ reviews.length }}</h3>
      <!--/.title-->
      <ul
        v-for="(review, index) in reviews"
        :key="index"
        class="pl-0"
        style="list-style: none"
      >
        <ReviewItem
          :review="review"
          @deleteReview="deleteReview"
          @review-updated="updateReview"
        />
      </ul>
    </div>
  </v-container>
</template>

<script>
import ReviewItem from "@/components/review/ReveiwItem";

export default {


  name:'ReviewList',
  components: {
    ReviewItem
  },
  props: {
    reviews:{
      type: [Array, Object],
    }
  },
  data() {
    return {


    }
  },
  created() {

  },
  methods: {
    deleteReview() {
      this.$emit('deleteReview')
    },
    updateReview() {
      this.$emit('review-updated')
    }
  }

}
</script>

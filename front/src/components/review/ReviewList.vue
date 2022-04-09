<template>
  <v-container>
    <ul>
      <h3>Review {{ reviews.length }}</h3>

      <div
        v-for="(review, index) in reviews"
        :key="index"
      >
        <ReviewCard
          :review="review"
          @deleteReview="deleteReview"
          @review-updated="updateReview"
        />
      </div>
    </ul>
  </v-container>
</template>

<script>
import ReviewCard from "@/components/review/ReveiwItem";

export default {


  name:'ReviewList',
  components: {
    ReviewCard
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

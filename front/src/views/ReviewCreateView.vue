<template>
  <div>
    <span>{{ id }}</span>
    <span>{{ type }}</span>

    <review-form
      :id="id"
      :type="type"
    />
  </div>
</template>

<script>
import ReviewForm from "@/components/review/ReviewForm";

export default {
  name: 'ReviewCreateView',
  components: {
    ReviewForm,
  },
  props: {
    type: String,
    id: String,
  },
  data() {
    return {

    }
  },
  mounted() {
  },


}
</script>


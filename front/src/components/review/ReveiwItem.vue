<template>
  <v-card
    class="ma-0 pa-0"
    outlined
    style="width: 100%; height: 300px"
  >
    <v-list-item
      class="pa-0"
    >
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
    </v-list-item>
  </v-card>
</template>

<script>

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
    }
  },
  computed: {
    reg_date: function() {
      return this.review.reg_date.substring(0,10);
    }
  },
  mounted() {

    console.log(this.review.rev_content)
  }
}

</script>

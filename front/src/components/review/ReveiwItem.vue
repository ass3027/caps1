<template>
  <v-container>
    <v-card
      class="mx-auto"
      outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <v-list-item-title class="text-h5 mb-1">
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


              <v-col class="ml-2 pt-0">
                <p class="text-body-2 red--text">
                  {{ reg_date }}
                </p>
              </v-col>
            </v-row>
          </v-list-item-title>
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
  </v-container>
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

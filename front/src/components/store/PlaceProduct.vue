<template>
  <v-card
    class="mx-auto pa-5"
    max-width="1296px"
    height="275px"
  >

    <div style="float: left; margin-right: 10px">
      <img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" style="width: 500px; height: 226px"/>
    </div>
    <div>
    <v-card-title style="width: inherit; margin: 0px">
      Top western road trips
    </v-card-title>

    <v-card-subtitle style="display: inline-block">
      1,000 miles of wonder
    </v-card-subtitle>

    <v-card-actions>
      <v-btn
        color="orange lighten-2"
        text
      >
        Explore
      </v-btn>

      <v-spacer></v-spacer>

      <v-btn
        icon
        @click="show = !show"
      >
        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>
    </div>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>
        <v-card-text>
          I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script>
export default {
  name: "PlaceProduct",
  data: () => ({
    show: false,
  }),
}
</script>

<style scoped>

</style>

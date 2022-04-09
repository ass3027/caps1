<template>
  <v-container
    fluid
    class="main"
  >
    <div class="body">
      <TravelHeader :category="category"/>
      <div class="body-form">
        <TravelLeftbar />
        <TravelMain />
      </div>
    </div>
  </v-container>
</template>

<script>

import TravelHeader from '@/components/travel/TravelHeader.vue';
import TravelLeftbar from '@/components/travel/TravelLeftbar.vue';
import TravelMain from '@/components/travel/TravelMain.vue';

export default {
  name: "HotelView",
  components: {
    TravelHeader,
    TravelLeftbar,
    TravelMain,
  },
  data(){
    return{
      category:'HOTEL'
    }
  }
}
</script>

<style scoped lang="scss">
.main {
  min-height: 100vh;
  padding: 0px;
}

.body-form {
  display: flex;
  height: 70vh;
}

.body {
  padding: 0px 3% 0 3%;
}
</style>

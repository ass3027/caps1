<template>
  <v-container
    fluid
    class="main"
  >
    <div class="body">
      <StoreHeader :category="category"/>
      <div class="body-form">
        <StoreLeftbar/>
        <StoreMain/>
      </div>
    </div>
  </v-container>
</template>

<script>

import StoreHeader from '@/components/store/StoreHeader.vue';
import StoreLeftbar from '@/components/store/StoreLeftbar.vue';
import StoreMain from '@/components/store/StoreMain.vue';

export default {
  name: "HotelView",
  components: {
    StoreHeader,
    StoreLeftbar,
    StoreMain,
  },
  data() {
    return {
      category: 'HOTEL'
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

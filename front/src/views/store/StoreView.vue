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
  name: "StoreView",
  components: {
    StoreHeader,
    StoreLeftbar,
    StoreMain,
  },
  props: ['value'],
  data() {
    return {
      category: this.$route.params.value
    }
  },
  created() {
    // this.category = this.$route.params.value
    // console.log(this.category)
  },
  // watch: {
  //   '$route.params.value'(now) {
  //     console.log('a')
  //     this.category = now
  //   }
  // }
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

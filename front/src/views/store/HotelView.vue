<template>
  <v-container
    fluid
    class="main"
  >
    <div class="body">
      <PlaceHeader :category="category" />
      <div class="body-form">
        <PlaceLeft :option="option" />
        <PlaceMain />
      </div>
    </div>
  </v-container>
</template>
<script>

import PlaceLeft from "@/components/store/PlaceLeft";
import PlaceMain from "@/components/store/PlaceMain";
import PlaceHeader from "@/components/store/PlaceHeader";

export default {
  name: "HotelView",
  components: {
    PlaceHeader,
    PlaceMain,
    PlaceLeft
  },
  data() {
    return {
      category: 'HOTEL',
      option: [
        {title: "pool", value: "1"},
        {title: "spa", value: "2"},
        {title: "restaurant", value: "3"},
        {title: "golf", value: "3"}
      ]
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

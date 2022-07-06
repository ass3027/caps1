<template>
  <v-data-table
    :headers="headers"
    :items="lists"
    :items-per-page="5"
    class="elevation-1"
  />
</template>

<script>
import axios from "axios";

export default {
  name: "GuideReserveInfo.vue",
  data() {
    return {
      lists: [],
      headers: [
        {text: '예약한 날짜', value: 'gdate'},
        {text: '시작시간', value: 'st_time'},
        {text: '종료시간', value: 'end_time'},
        {text: '소요시간', value: 'require_time'},
        {text: '장소명', value: 'title'}
      ]
    }
  },
  mounted() {
    this.importGuide()
  },
  methods: {
    importGuide() {
      axios({
        method: 'get',
        url: '/api/gTime',
        params: {
          'id': this.$store.state.user.userId
        }
      })
        .then((res) => {
          this.lists = res.data;
          console.log('sb', res.data)
        })
    }
  }
}
</script>

<style scoped>

</style>

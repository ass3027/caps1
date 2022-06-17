<template>
  <div class="card">
    <v-row
      cols="0"
      md="0"
    >
      <v-card
        v-for="(item,index) in lists"
        :key="index"
        class="mx-auto"
        width="344"
        outlined
        justify="start"
      >
        <v-list-item three-line>
          <v-list-item-content>
            <div class="text-overline mb-4">
              상품아이디:{{ item.gitem_id }}
              <v-btn
                @click="view(item)"
              >
                시간 등록
              </v-btn>
            </div>
            <div>
              시작날짜:{{ item.st_date }}
              <br>
              종료날짜:{{ item.end_date }}
              <br>
              요구시간:{{ item.require_time }}
            </div>
            <v-list-item-title class="text-h5 mb-1">
              ID:{{ item.user_id }}
            </v-list-item-title>
            <v-list-item-subtitle>
              소개:{{ item.introduce }} <h4>자세히보기</h4>
            </v-list-item-subtitle>
<script>

export default {
  name: "GuideProduct.vue",

  data(){
    return{
      lists:[]
    }
  },
  mounted() {
    this.importGitem()
  },

  methods:{
    view(item){
      this.$router.push("/GuideProductTime/" + item.gitem_id)
    },

    importGitem(){
      axios({
        method:'get',
        url   :'/api/gitem',
      })
      .then((res)=>{
        this.lists = res.data;
      })
    }
  }
}
</script>

<style>
.card {
  position: relative;
  display: flex;
}

</style>

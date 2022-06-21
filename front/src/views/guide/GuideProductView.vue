<template>
  <v-card
    class="mx-auto my-12"
    max-width="374"
  >
    <v-card-title>USERID:{{ lists.user_id }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >

        <div class="grey--text ms-4">
          ID:{{lists.gitem_id}}
        </div>
      </v-row>

      <div class="my-4 text-subtitle-1">
        장소:{{ lists.title }}
      </div>

      <div>소개:{{lists.introduce}}</div>
    </v-card-text>

    <v-divider class="mx-4" />

    <v-card-title>예약시간</v-card-title>

    <v-card-text

    >
      <v-chip-group
        v-for="(item,index) in items"
        :key="index"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip
          v-if="item.book_whether==1"
          @click="num(item.time_num)"
        >
          {{item.st_time}} ~ {{item.end_time}}
        </v-chip>

      </v-chip-group>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="reserve()"

      >
        Reserve
      </v-btn>
    </v-card-actions>
  </v-card>


</template>

<script>
import axios from "axios";

export default {
  name: "GuideProductView.vue",
  props:['gitem_id'],
  data(){
    return{
      lists:'',
      items:[],
      selectednum:'',
    }
  },
  mounted() {
    this.importGuide()
    this.timeGitem()
  },
  methods:{
    importGuide(){
      axios({
        method:'get',
        url:`/api/gitemview/` + this.gitem_id,
      })
      .then((res)=>{
        this.lists = res.data;
        console.log(res.data + '상품상세보기')
      })
    },
    reserve(){
      var sendform2 = {book_id:this.book_Id,
      time_num:this.selectednum,
      user_id:this.$store.state.user.userId}
      axios({
        method:'put',
        url:'/api/gitemTimeUpdate/',
        params:{
          'id': this.selectednum
        }
      })
      .then(()=>{
        console.log("O")
        this.timeGitem()
      })
      console.log(sendform2)
      axios({
        method:'post',
        url:'/api/gitemReserve',
        data:sendform2,
      })

    },
    num(selectednum){
      this.selectednum = selectednum
      console.log(this.selectednum)
    },


    timeGitem(){
      axios({
        method:'get',
        url:'/api/gtime/' + this.gitem_id,
      })
      .then((res)=>{
        this.items = res.data;
        console.log('시간상세보기', res.data)
      })

    }
  }
}
</script>

<style scoped>

</style>

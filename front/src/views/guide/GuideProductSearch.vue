<template>
  <div class="container">

    <div class="item_list">
      <div class="card"
           v-for="(item,index) in lists"
           :key="index"

      >
        <div class="img">

          <v-img
            class="white--text align-end"
            height="200px"
            :src="item.firstimage2"
          >
            <div style="opacity: 0.5;
               position: relative; left: 80px; bottom: 100px">
              <v-btn
                style="height: 25px"
                v-if="item.user_id==$store.state.user.userId"
                @click="view(item)"
              >
                시간등록
              </v-btn>
              <v-btn
                style="height: 25px"
                v-if="item.user_id==$store.state.user.userId"
                @click="deleteItem(item.gitem_id)"
              >
                삭제
              </v-btn>
            </div>
            <h2 style="color: black">
              <br> {{item.user_id}}
            </h2>

          </v-img>
        </div>

        <div class="text-overline mb-4"
             style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis; line-height: 3rem"
        >

          {{ item.gname}}<br>
          {{ item.title }}<br>
          {{ item.gitem_price }}원

          <v-btn
            style="left: 175px; width: 90px; height: 30px"
            @click="views(item)"
          >
            자세히보기
          </v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "GuideProductSearch.vue",
  props:['keyword'],

  data(){
    return{
      lists:[],
    }
  },
  mounted() {
    this.importGitem()
  },
  methods:{
    view(item){
      this.$router.push("/GuideProductTime/" + item.gitem_id)
    },
    views(item){
      this.$router.push("/GuideProduct/" + item.gitem_id)
    },

    importGitem(){
      axios({
        method:'get',
        url   :'/api/gitem/search/' + this.keyword,
      })
      .then((res)=>{
        this.lists = res.data;
      })
    }
  }
}
</script>

<style scoped>

.container{
  max-width: 1100px;
  margin: auto;
}
.item_list{
  display: inline-flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.item_list .card {
  border: 1px solid #eeeeee;
  border-radius: 5px;
  height: 350px;
  width:325px;
  padding: 5px;
  margin-bottom: 10px;
}
.item_list .card img{
  height: 200px;
  width: 250px;
}
</style>

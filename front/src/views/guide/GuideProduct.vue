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
              <div v-bind:gid="item.gitem_id">
              상품아이디:{{ item.gitem_id }}
              </div>
              <v-btn
                v-if="item.user_id==$store.state.user.userId"
                @click="view(item)"
              >
                시간등록
              </v-btn>
              <v-btn
                v-if="item.user_id==$store.state.user.userId"
                @click="deleteItem(item.gitem_id)"
              >
                삭제
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
              장소:{{item.title}}
            </v-list-item-title>
            <v-list-item-subtitle>
              소개:{{ item.introduce }}
            </v-list-item-subtitle>
            <v-btn
              @click="views(item)"
            >
              자세히보기
            </v-btn>
          </v-list-item-content>
        </v-list-item>
      </v-card>
    </v-row>
    <div>
      <input
        v-model="keyword"
        name="keyword"
        type="text"
        style="border: #1e90cc solid"
        class="search"
      >
    </div>
    <v-btn
      class="btn2"
      @click="onsubmit()"
    >
      Search
    </v-btn>
  </div>
</template>
<script>

import axios from "axios";

export default {
  name: "GuideProduct.vue",

  data(){
    return{
      lists:[],
      keyword:'',

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
    onsubmit(){
      console.log(this.keyword)
      this.$router.push("/GuideProduct/Search/" + this.keyword)

    },

    deleteItem(id){
      axios({
        method:'delete',
        url: '/api/dgitemOne',
        headers:{
          'Content-Type': 'multipart/form-data',
        },
        params:{
          'id' : id
        }
      })
      .then(()=>{
        console.log("oo");
        this.importGitem()
      })
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

<style scoped>
.card {
  position: relative;
  display: flex;
}

</style>

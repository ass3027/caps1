<template>
  <div>
    <v-row
      cols="0"
      md="0"
    >
      <v-card
        v-for="(item,index) in lists"
        :key="index"
        class="mx-auto"
        width="400"
        style="margin: 20px;"
        outlined
        justify="start"
      >
        <v-img
          class="white--text align-end"
          height="200px"
          :src="item.firstimage2"
        >
          <h2 style="color: black">
            {{ item.user_name }}
            <br> {{ item.user_id }}
          </h2>
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
        </v-img>
        <v-list-item three-line>
          <v-list-item-content>
            <div class="text-overline mb-4">
              <v-card-title :gid="item.gitem_id">
                상품이름:{{ item.gname }}<br>
                장소:{{ item.title }}<br>
                가격:{{ item.gitem_price }}
              </v-card-title>
            </div>
            <v-card-text>
              시작날짜:{{ item.st_date }}
              <br>
              종료날짜:{{ item.end_date }}
              <br>
              요구시간:{{ item.require_time }}
            </v-card-text>

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

</style>

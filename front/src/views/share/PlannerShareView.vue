<template>
  <v-container style="padding-top: 60px">
    <v-row
      justify="center"
    >
      <v-col cols="2" >

        <v-card class="pa-5">

        <v-row>
          <h4>필터</h4>
        </v-row>

        <v-row>
          <input type="text" v-model="searchContents" placeholder="내용검색" style="border:1px solid;width:100%">
        </v-row>


        <v-row class="mt-10" style="height: 300px;overflow: auto">
            #태그
          <v-radio-group v-model="selected">
            <v-radio
              v-for="(set,i) in removeDuplicates"
              :key="i+`s`"
              :label="`#`+set.share_place"
              :value="set.share_place"
            ></v-radio>
          </v-radio-group>
        </v-row>
        <v-row>
          <v-btn @click="initializing" text>필터 초기화</v-btn>
        </v-row>
          </v-card>

      </v-col>
      <v-col
        cols="8"
      >
        <v-row
          style=""
        >
          <v-col cols="8" class="">
            <h2>플래너 공유</h2>
          </v-col>
          <v-col cols="4">
            <button
              style="margin-top: 20px"
              @click="loginCheck"
            >
              글 작성하기
            </button>

          </v-col>
        </v-row>
        <v-row>
          <v-col
            v-for="(item,index) in paginatedData"
            :key="index"
            cols="5"
            style="padding: 0; margin:10px;cursor: pointer"
            @click="$router.push({name:'shareDetails', params:{id:item.share_id}})"
          >
            <planner-share-card :share-set="item" />
          </v-col>
        </v-row>
        <v-row >
          <v-col cols="10">
            <v-pagination
              v-model="pageNum"
              :length="pageCount"
            >
            </v-pagination>

          </v-col>

        </v-row>
      </v-col>
      <v-col cols="1">

      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import axios from 'axios';
import PlannerShareCard from "@/components/recommend/PlannerShareCard";


export default {
  name: "PlannerShareView",
  components: { PlannerShareCard},
  data() {
    return {
      sets: [],
      originTableSets: [],
      pageNum: 1,
      pageSize:4,
      selected: '',
      searchContents:''

    }
  },
  mounted() {
    axios.get('/api/getSharePosts')
      .then((res) => {
        //날짜변환
        let now = new Date()
        let today = new Date(now.toLocaleDateString());
        res.data.forEach(i => {
          var thisDate = new Date(i.share_created)
          if (today > thisDate) {
            i.share_created = i.share_created.substring(0, 10)
          } else {
            i.share_created = thisDate.toString().substring(16, 21)
          }
        })
        this.originTableSets = res.data;
        console.log(this.originTableSets)
      })
  },
  methods: {
    loginCheck() {
      if (this.$store.state.user.userId) {
        this.$router.push({name: 'shareWrite'})
      } else {
        alert("로그인해주세요")
      }
    },
    initializing(){
      this.selected=''
      this.searchContents=''
    }
  },
  computed: {
    removeDuplicates(){
      return this.originTableSets.reduce(function(acc, current) {
        if (acc.findIndex(({ share_place }) => share_place === current.share_place) === -1) {
          acc.push(current);
        }
        return acc;
      }, []);

    },
    tableSets(){
      console.log(this.originTableSets)
      const tagResult = this.originTableSets.filter(share=>{
        if (share.share_place.indexOf(this.selected) != -1) return true
        }
      )

      return tagResult.filter(share=>{
          if (share.share_contents.indexOf(this.searchContents) != -1) return true
        }
      )


    },
    pageCount () {
      let listLeng = this.tableSets.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = (this.pageNum-1) * this.pageSize,
        end = start + this.pageSize;
      return this.tableSets.slice(start, end);
    },

  }

}
</script>

<style scoped>

button {
  border: 1px solid black;
  background-color: white;
  color: black;
  display: inline-block;
  width: 120px;
  vertical-align: top;
  height: 44px;
  border-radius: 3px;
  font-weight: 700;
  font-size: 14px;
  line-height: 40px;
  text-align: center;
  outline: none;
}

</style>

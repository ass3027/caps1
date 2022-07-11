<template>
  <v-container>
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        관광지를 검색해보세요
      </v-card-title>
    </v-card>


    <v-card class="align-center">
      <v-row>
        <v-col>
          <v-select
            v-model="searchCon"
            :style="{width:'150px',marginLeft:'270px'}"
            label="검색조건"
            :items="items"
            item-text="text"
            :value="lists.areaCode"
          />
        </v-col>
        <v-col>
          <v-text-field
            v-model="keyword"
            dense
            outlined
            label="검색키워드"
            full-width
            :style="{marginTop:'10px'}"
            @keyup.enter="placeTitle"
          />
        </v-col>
        <v-col>
          <v-btn
            color="primary"
            :style="{width:'100px', marginRight:'30px', marginTop:'10px'}"
            @click="placeTitle"
          >
            검색
          </v-btn>
        </v-col>
      </v-row>


      <v-sheet class="pa-3">
        <v-row class="align-center">
          <v-skeleton-loader
            v-for="(data,index) in calData"
            :key="index"
            class="mx-auto"
            max-width="300"
            type="card"
            cols="auto"
          >
            <v-card
              width="400px"
              height="300px"
              cols="auto"
              class="ma-3"
            >
              <v-row justify="center">
                <img
                  :src="data.firstimage2"
                  width="200px"
                  height="150px"
                  class="ma-2"
                  alt=""
                >
              </v-row>
              <v-row>
                <v-card-subtitle>
                  장소명: {{ data.title }}<br>
                  주소명:{{ data.addr1 }}<br>
                  전화번호:{{ data.tel }}
                </v-card-subtitle>
                <v-col>
                  <v-card-actions style="position: absolute; bottom: 0; right: 0">
                    <v-spacer />
                    <v-btn
                      depressed
                      color="primary"
                      @click="goDetail(data)"
                    >
                      상세보기
                    </v-btn>
                  </v-card-actions>
                </v-col>
              </v-row>
            </v-card>
          </v-skeleton-loader>
        </v-row>
        <div class="text-center">
          <v-pagination
            v-model="curPageNum"
            :length="numOfPages"
            class="my-4"
            :total-visible="10"
          >
            <!--        v-model : 현재 활성화(보여지고있는)되어있는 Page의 번호.
                          :length : v-bind:length의 약어로 length 속성은 총 page의 사이즈를 나타냅니다.-->
          </v-pagination>
        </div>
      </v-sheet>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      keyword: '',
      searchData: [],
      dataPerPage: 10,
      curPageNum: 1,
      dialog: false,
      lists: [],
      searchCon: '12',
      items: [
        {text: '관광지', value: '12'},
        {text: '문화시설', value: '14'},
        {text: '축제/공연/행사', value: '15'},
        {text: '여행코스', value: '25'},
        {text: '레포츠', value: '28'},
        {text: '숙박', value: '32'},
        {text: '쇼핑', value: '38'},
        {text: '음식', value: '39'},
      ],
    }
  },
  computed: {
    startOffset() {
      return ((this.curPageNum - 1) * this.dataPerPage);
    },
    endOffset() {
      return (this.startOffset + this.dataPerPage);
    },
    numOfPages() {
      return Math.ceil(this.lists.length / this.dataPerPage);
    },
    calData() {
      return this.lists.slice(this.startOffset, this.endOffset);
    },
    // calData1() {
    //   this.searchData = this.lists.filter((data) => {
    //     return data.subject.toLowerCase().includes(this.search.toLowerCase())
    //   }).slice(0);
    //   return this.searchData.slice(this.startOffset, this.endOffset)
    // }
  },
  mounted() {

  },

  methods: {
    a() {
      while (this.items.length !== 0) {
        this.items.pop()
      }
    }
    ,
    ChoiceLodging: function (lodging) {
      this.dialog = false
      this.$emit('childEvent', lodging)
    }
    ,
    closeDialog() {
      this.dialog = false;
    }
    ,
    placeTitle() {
        axios({
          method: "get",
          url: `/api/placeA01/${this.searchCon}`,
          params:{keyword:this.keyword}
        })
          .then((res) => {
            this.lists = res.data;
            console.log('키워드응답' + res);
            this.curPageNum = 1
          })
    },
    goDetail(data) {
      this.$router.push({name:'tourDetail',params:{pl_id:data.pl_id,contentTypeId:this.searchCon}})
    }
  }
  ,

}
</script>

<style scoped>

.yame {
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

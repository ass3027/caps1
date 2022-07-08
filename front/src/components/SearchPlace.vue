<template>
  <div>
    <v-dialog v-model="dialog">
      <template #activator="{ on, attrs }">
        <v-btn
          color="red lighten-2"
          dark
          v-bind="attrs"
          style="width:95px;"
          v-on="on"
        >
          장소검색
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          장소를 검색하세요
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
                    장소명: {{ data.title }} ({{ data.zipcode }})<br>
                    주소명:{{ data.addr1 }}
                  </v-card-subtitle>
                  <v-col>
                    <v-card-actions style="position: absolute; bottom: 0; right: 0">
                      <v-spacer />
                      <v-btn
                        depressed
                        color="primary"
                        @click="ChoiceLodging(data)"
                      >
                        숙소선택
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
    </v-dialog>
  </div>
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
      searchCon: '1',
      items: [
        {text: '서울', value: '1'},
        {text: '인천', value: '2'},
        {text: '대전', value: '3'},
        {text: '대구', value: '4'},
        {text: '광주', value: '5'},
        {text: '부산', value: '6'},
        {text: '울산', value: '7'},
        {text: '세종특별자치시', value: '8'},
        {text: '경기도', value: '31'},
        {text: '강원도', value: '32'},
        {text: '충청북도', value: '33'},
        {text: '충청남도', value: '34'},
        {text: '경상북도', value: '35'},
        {text: '경상남도', value: '36'},
        {text: '전라북도', value: '37'},
        {text: '전라남도', value: '38'},
        {text: '제주도', value: '39'},
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
      if (this.keyword === '') {
        axios({
          method: "get",
          url: `/api/place/${this.searchCon}/B02`
        })
          .then((res) => {
            this.lists = res.data;
            console.log('검색응답' + res);
          })
      } else {
        axios({
          method: "get",
          url: `/api/place/${this.searchCon}/B02/${this.keyword}`
        })
          .then((res) => {
            this.lists = res.data;
            console.log('키워드응답' + res);
            this.curPageNum = 1
          })
      }
    },
    // placeCount(){
    //   axios({
    //     method:'get',
    //     url:`/api/place/${this.searchCon}/B02`
    //
    //   })
    // }
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

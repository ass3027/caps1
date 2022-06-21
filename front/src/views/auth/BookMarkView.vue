<template>
  <div style="width: 1050px; padding-top: 65px">

    <MyPageHeader></MyPageHeader>
    <div style="width: 820px; float: right;">
      <div class="head_aticle">
        <h1 class="tit">즐겨찾기</h1>
      </div>

      {{ test }}
      <div class="search_date" style="border-top: 2px solid #333">
        <a id="type_select" class="btn_layer" @click="select_type">{{ selected }}</a>
        <ul id="type_display" class="layer_search" style="display: none">
          <li><a id="select_all" @click="select_all" class="on">모두보기</a></li>
          <li><a id="select_keeper" @click="select_keeper">키퍼</a></li>
          <li><a id="select_hotel" @click="select_hotel">숙소</a></li>
          <li><a id="select_tour" @click="select_tour">관광지</a></li>
        </ul>
      </div>
      <div class="list_bookmark">
        배열 길이: {{ current_bookmark_type.length }}
        <div v-for="(bookmark, index) in current_bookmark_type" :key="index">
          {{ bookmark }} <br>
          <br>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPageHeader from "@/components/store/MyPageHeader";
import axios from "axios";
export default {
  components:{
    MyPageHeader
  },
  data(){
    return{
      pl_id:'',
      addr1:'',
      title:'',
      tel:'',
      user_id:'',
      firstimage:'',
      postArray:[],
      current_bookmark_type: [],
      all: [],
      keeper: [],
      hotel: [],
      tour: [],
      test: null,
      selected: '모두보기',
    }
  },
  mounted() {
    axios.get("/api/bookmark/BookMark")
    .then((res)=>{
      console.log(res.data)

      this.all = res.data.all
      this.keeper = res.data.keeper
      this.hotel = res.data.hotel

      this.current_bookmark_type = this.all

      console.log('test', this.current_bookmark_type[0])

    })

  },
  methods:{
    url(){ //사이트이동하기
      this.$router.push("/")
    },
    select_type(){

      var type = document.getElementById('type_select')
      var display = document.getElementById('type_display')

      if(type.classList.contains('on')){
        type.classList.remove('on')
        display.style.display = 'none'
      } else {
        type.classList.add('on')
        display.style.display = 'block'
      }
    },

    select_all() {
      console.log('1')
      var all = document.getElementById('select_all')
      var keeper = document.getElementById('select_keeper')
      var hotel = document.getElementById('select_hotel')
      var tour = document.getElementById('select_tour')

      all.classList.add('on')
      keeper.classList.remove('on')
      hotel.classList.remove('on')
      tour.classList.remove('on')

      this.selected = '모두보기'
      this.current_bookmark_type = this.all

      this.select_type()
    },
    select_keeper() {
      console.log('2')
      var all = document.getElementById('select_all')
      var keeper = document.getElementById('select_keeper')
      var hotel = document.getElementById('select_hotel')
      var tour = document.getElementById('select_tour')

      all.classList.remove('on')
      keeper.classList.add('on')
      hotel.classList.remove('on')
      tour.classList.remove('on')

      this.selected = '키퍼'
      this.current_bookmark_type = this.keeper

      this.select_type()
    },
    select_hotel() {
      console.log('3')
      var all = document.getElementById('select_all')
      var keeper = document.getElementById('select_keeper')
      var hotel = document.getElementById('select_hotel')
      var tour = document.getElementById('select_tour')

      all.classList.remove('on')
      keeper.classList.remove('on')
      hotel.classList.add('on')
      tour.classList.remove('on')

      this.selected = '호텔'
      this.current_bookmark_type = this.hotel

      this.select_type()
    },
    select_tour() {
      console.log('4')
      var all = document.getElementById('select_all')
      var keeper = document.getElementById('select_keeper')
      var hotel = document.getElementById('select_hotel')
      var tour = document.getElementById('select_tour')

      all.classList.remove('on')
      keeper.classList.remove('on')
      hotel.classList.remove('on')
      tour.classList.add('on')

      this.selected = '관광지'
      this.current_bookmark_type = this.tour

      this.select_type()
    },

  },
  computed: {

  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}

ul {
  list-style-type: none;
  padding: 0;
}
li{

}
.search_date {
  position: relative;
  z-index:1
}

.search_date .btn_layer {
  overflow: hidden;
  position: absolute;
  right: 0;
  top: -74px;
  width: 157px;
  height: 44px;
  padding: 11px 0 0 15px;
  border: 1px solid #ddd;
  font-size: 14px;
  color: rgb(102, 102, 102);
  line-height: 18px;
  letter-spacing:-.5px
}

.search_date .btn_layer:after {
  content: "";
  position: absolute;
  left: 128px;
  top: 18px;
  width: 12px;
  height: 8px;
  background: url(https://res.kurly.com/pc/ico/2008/ico_down_12x8.svg) no-repeat 0 0;
  background-size:12px 8px
}

.search_date .btn_layer.on:after {
  transform: rotate(-180deg);
  -webkit-transform: rotate(-180deg);
  -moz-transform: rotate(-180deg);
  -ms-transform: rotate(-180deg);
  -o-transform:rotate(-180deg)
}

.search_date .layer_search {
  display: none;
  position: absolute;
  right: 0;
  top: -30px;
  width: 157px;
  border: 1px solid #dddfe1;
  border-top:0
}

.search_date .layer_search li {
  background-color: #fff;
  border-top:1px solid #dddfe1
}

.search_date .layer_search li:first-child {
  border-top:0
}

.search_date .layer_search a {
  display: block;
  overflow: hidden;
  height: 32px;
  padding: 7px 0 0 8px;
  font-size: 12px;
  color: #666;
  line-height: 18px;
  letter-spacing:-.2px;
}

.search_date .layer_search a.on {
  background-color:#f7f7f7
}
.search_date {
  position: relative;
  z-index: 1;
}

.tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -.5px;
}

.head_aticle {
  padding: 5px 0 34px;
}

</style>

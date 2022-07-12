<template>
  <div class="resume-view">
    <div id="Contents">
      <div class="resumeWrap">
        <div class="resumeType1">
          <div class="resumeType1-inner">
            <div id="ResumeBaseInfo" class="resumeView">
              <h2 class="hide">
                상품 등록
              </h2>
              <v-text-field
                v-model="lodging.pl_id"
                name="guideIntro"
                type="text"
                class="intro"
                placeholder="장소번호"/>
              <SearchPlaceTour
              style="margin-left: 630px; margin-top:-95px"
              @childEvent="getEmitData"/>
              <v-text-field
                v-model="gname"
                name="guideIntro"
                type="text"
                class="intro"
                placeholder="상품이름">
              </v-text-field>

              <v-text-field
                v-model="introduce"
                name="guideIntro"
                type="text"
                class="intro"
                placeholder="소개">
              </v-text-field>

              <v-text-field
                v-model="st_date"
                name="guideIntro"
                type="date"
                class="intro"
                data-placeholder="시작날짜"
                required
                aria-required="true">
              </v-text-field>

              <v-text-field
                v-model="end_date"
                name="guideIntro"
                type="date"
                class="intro"
                data-placeholder="종료날짜"
                required
                aria-required="true">
              </v-text-field>

              <v-text-field
                v-model="require_time"
                name="guideIntro"
                type="text"
                class="intro"
                placeholder="요구시간">
              </v-text-field>

              <v-text-field
                v-model="gitem_price"
                name="guideIntro"
                type="text"
                class="intro"
                placeholder="상품가격">
              </v-text-field>

              <v-btn
                class="btn"
                style="float: right"
                width="20%"
                @click="onsubmit()"
                type="button"
              >
                JOIN
              </v-btn>

            </div>
            </div>
            <div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
<script>


import axios from "axios";
import SearchPlaceTour from "@/components/search/SearchPlaceTour";

export default {
  name: 'GuideProductReg',
  components: {
    SearchPlaceTour
  },
  props:[],
  data(){
    return{
      lodging:'',
      user_id:'',
      pl_id:'',
      introduce:'',
      st_date:'',
      require_time:'',
      end_date:'',
      gitem_price:'',
      gname:'',
    }
  },
  methods:{
    getEmitData: function (lodging) {
      this.lodging = lodging
      console.log("받은데이터" + lodging)
    },
    onsubmit(){
      if (this.$store.state.user.userId===''){
        alert("로그인후 이용해주세요")
      }
      var sendform= new FormData();

      sendform.append('user_id', this.$store.state.user.userId);
      sendform.append('pl_id', this.lodging.pl_id);
      sendform.append('gname', this.gname);
      sendform.append('introduce', this.introduce);
      sendform.append('st_date', this.st_date);
      sendform.append('require_time', this.require_time);
      sendform.append('end_date', this.end_date);
      sendform.append('gitem_price', this.gitem_price);





      axios({
        method: 'post',
        url: '/api/gijoin',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        console.log(res)
        if (res.data === '') {
          alert('상품등록');

        }else{
          alert('등록실패');
        }

      });

    }
  }
}

</script>
<style>

</style>

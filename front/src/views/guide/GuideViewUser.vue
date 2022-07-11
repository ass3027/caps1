<template>
  <div class="resume-view">
    <div id="Contents">
      <div class="resumeWrap">
        <div class="resumeType1">
          <div class="resumeType1-inner">
            <div id="ResumeBaseInfo" class="resumeView">
              <h2 class="hide">가이드 정보</h2>
              <div class="photoArea">
                     <span class="photo">
                       <v-img
                       :src="img(lists.pic_name)"
                       ></v-img>
                     </span>
              </div>

              <ul class="infoList">
                <li class="name"><strong></strong> <em></em> {{lists.user_name}} {{lists.gender}}<br></li>
                <li class="address"><span class="info-title2">주소:{{lists.user_area}}</span><span class="star"></span></li>
                <li class="contact">
                  <span class="info-title2">연락처:{{lists.user_phone}}</span>
                </li>
                <li class="mail"><span class="info-title2">{{lists.user_birth}}</span><span class="star"></span></li>
              </ul>
            </div>


            <div id="ResumePR" class="resumeView">
              <h2>가이드 소개</h2>
              {{lists.guser_intro}}

            </div>


          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name:'GuideViewUser',
  props:['user_id'],

  data(){
    return {
      lists:'',
      selection:0,
      loading:"dd"
    }
  },
  mounted() {
    this.importGuide()

  },

  methods:{
    reserve(){
    },
    img(fileName){
      return "/api/photo/" + fileName
    },

    importGuide() {
      axios({
        method:'get',
        url : `/api/guide/`+ this.user_id,
      })
        .then((res)=>{
          this.lists = res.data;
          console.log(res.data + "상세보기")
        })
    }
  }


}
</script>

<style>

li{
  list-style: none;
}


#ResumeBaseInfo {
  z-index:1;
  position:relative;
  width:738px;
}

#ResumeBaseInfo::after {
  content:" ";
  clear:both;
  display:block
}

#ResumeBaseInfo .photoArea {
  float:left;
  width:138px;
  margin-right:36px
}

#ResumeBaseInfo .photoArea .photo {
  position:relative;
  display:inline-block;
  width:112px;
  height:132px;
  border:1px solid #ddd}

#ResumeBaseInfo .photoArea .photo img {
  width:112px;
  height:132px
}

#ResumeBaseInfo .infoList {
  float:right;
  width:564px
}

#ResumeBaseInfo .infoList li:first-child {
  padding-left:0;
  margin-bottom:15px;
  border-bottom:1px solid #ebebeb
}

#ResumeBaseInfo .infoList li {
  position:relative;
  min-height:32px;
  padding:0 0 0 90px;
  line-height:32px;
  font-size:15px;color:#222
}

#ResumeBaseInfo .infoList li .info-title2 {
  position:absolute;
  left:0;
  top:0;
  font-size:15px;
  font-weight:bold;color:#999
}

#ResumeBaseInfo .infoList li.name {
  padding-top:0;
  font-size:16px;
  color:#666
}

#ResumeBaseInfo .infoList li.name strong {
  margin-right:6px;
  font-size:24px;
  color:#222
}

#ResumeBaseInfo .infoList li.name em {
  color:#666
}

#ResumeBaseInfo .infoList li.name a {
  position:relative;
  top:11px;
  display:inline-block;
  margin-left:10px;
  vertical-align:top
}

#ResumeBaseInfo .infoList li.contact {
  z-index:1;
  position:relative;
  letter-spacing:0
}

#ResumeBaseInfo .infoList li.contact strong {
  color:#888
}

#ResumeBaseInfo .infoList li.mail {
  letter-spacing:0;
  word-break:break-all
}

.resumeView{
  position: relative;
  width: 738px;
  margin: 40px auto 0;
  word-break: break-all;
}
.resumeType1 h2{
  padding-bottom: 10px;
  font-size: 22px;
  font-weight: bold;
  border-bottom: 2px solid #222;
}





</style>

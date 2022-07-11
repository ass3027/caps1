<template>
  <div class="resume-view">
    <div id="Contents">
      <div class="resumeWrap">
        <div class="resumeType1">
          <div class="resumeType1-inner">
            <div id="ResumeBaseInfo" class="resumeView">
              <h2 class="hide">기본정보</h2>
              <div class="photoArea">
                     <span class="photo">
                        <a href="#ResumeBaseInfo" class="image" style="position:static;left:0;bottom:0;display:block;width:auto;height:auto;padding:0;border:0 none;background:none">

                        </a>
                     </span>
              </div>

              <ul class="infoList">
                <li class="name"><strong></strong> <em></em> · (  ) <br></li>
                <li class="address"><span class="info-title2">주소</span><span class="star"></span></li>
                <li class="contact">
                  <span class="info-title2">연락처s</span>
                </li>
                <li class="mail"><span class="info-title2">이메일</span><span class="star"></span></li>
              </ul>
            </div>


            <div id="ResumePR" class="resumeView">
              <h2>자기소개서</h2>
              <div class="ResumeOpenBox"><span class="lockIcon"></span><p class="first"></p>asd</div>
            </div>


          </div>

        </div>
      </div>
    </div>
  </div>
</template>

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
.resumeType1 h2 .desc{
  margin-left: 10px;
  font-size: 16px;
  font-weight: normal;
}




</style>

<script>
import axios from "axios";
import ReviewCreateView from "@/views/ReviewCreateView";

export default {

  name: 'TestView',
  components: {
    // ReviewCreateView,
  },

  data() {
    return {
      userId: this.$store.state.user.userId,
      user: Object
    }
  },
  method: {
    scrollEvents() {
      this.scroll = window.scrollY
    }
  },
  created() {
    axios({
      method: 'GET',
      url: '/api/user/find',
      params: {
        'user_id': this.userId
      }
    }).then(res => {
      this.user = res.data
    })
  },
  mounted() {
    console.log("[current_user_id]" + this.userId)

    // setInterval(function () {
    //   console.log(window.scrollY)
    // }, 300)

  },

}

</script>

<style>

</style>

<template>
  <div>
    <div :class="{'contentbackground':true}">
      <!--컴포넌트 전체영역-->
      <img
        class="background_img"
        alt=""
      ><!--백그라운드 이미지 src="../assets/background.png"-->
      <div class="sign_box">
        <!--전체 로그인 화면-->
        <div class="sign_title">
          <!--로그인 타이틀-->
          <span>LOGIN</span>
        </div>
        <div class="sign_inner_box">
          <!--로그인 타이틀 아래, 기능부의 전체를 감싸는 검은박스-->
          <div class="id_pw_form">
            <!--ID와 패스워드 입력부-->
            <div class="insert_id">
              <!--ID와 인풋태그-->
              <span class="id_label">ID</span> <input
                class="id_input_box"
                type="textarea"
              >
            </div>
            <div class="insert_pw">
              <!--PW와 인풋태그-->
              <span class="pw_label">PW</span> <input
                class="pw_input_box"
                type="password"
              >
            </div>
          </div> <!--ID와 패스워드 입력부 끝-->
          <div class="logins">
            <!--소셜로그인 버튼들-->
            <div class="kakao_yellow">
              <!--카카오 소셜 로그인 박스-->
              <span class="kakao_label">KAKAO</span> 
            </div>
            <div class="google_red">
              <!--구글 소셜 로그인 박스-->
              <span class="google_label">GOOGLE</span> 
            </div>
            <div class="login_blue">
              <!--플랫폼 직접 로그인 박스-->
              <span class="login_label">로그인</span> 
            </div>
          </div> <!--소셜로그인 버튼들 끝-->
          <div class="logins_deco">
            <!--로그인 버튼 아래에 꾸며주는 블럭 -->
            <div class="logins_deco_social">
              <span class="social_label">social</span>
            </div>
            <div class="logins_deco_platform">
              <span class="platform_label">platform</span>
            </div>
          </div> <!--로그인 버튼 아래에 꾸며주는 블럭 끝 -->
          <div class="signupNpasswd">
            <!-- 최하단 회원가입 및 패스워드 찾기 -->
            <span class="signup_button">회원가입</span>
            <span class="where_my_account">로그인에 문제가 있나요?</span>
          </div> <!-- 최하단 회원가입 및 패스워드 찾기 끝 -->
          <div class="partion_block">
            <!-- 로그인 및 광고영역간 공간분리 -->
          </div>
          <div class="adver_section">
            <!--광고영역-->
            <img
              class="adver_img"
              alt=""
            > <!--광고영역의 이미지 src="../assets/adver.png"-->
            <div class="adver_info">
              <span>123</span>
            </div> <!--광고대상의 정보박스-->
          </div> 
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'vsnS                                                                                                                                                                                                                                                                      ign',

  components: {
  },
}
  
</script>
<style>
.contentbackground {
  background:#353535;
  width: 100vw;
  height: 100vh;
  z-index: -100;
  overflow: auto;
}
.sign_box{
  position:absolute;
  width: 70%;
  height: 70%;
  padding-top: 3vh;
  padding-bottom: 3vh;
  padding-left: 3vh;
  padding-right: 3vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.background_img {
  position:absolute;
  top: 50%;
  left: 50%;
  object-fit: cover;
  width: 100vw;
  height: 100vh;
  transform: translate(-50%, -50%);
}
.sign_title{
  position:absolute;
  top: 0%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 3em;
  font-weight: 700;
  color:white;
  z-index: 1;
}
.sign_inner_box{
  position:absolute;
  background: #2e2e2e;
  width: 980px;
  height: 450px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -40%);
  padding-top: 50px;
  padding-left: 50px;
  padding-right: 50px;
  padding-bottom: 50px;
  border-radius: 25px;
}
.insert_id{
  position:absolute;
  top: 100px;
  font-size: 2em;
}
.id_label{
  position:absolute;
  top: 1px;
  color: white;
  letter-spacing: 25px
}
.id_input_box{
  position:absolute;
  left:70px;
  top:-5px;
  margin-left: 10px;
  width: 450px;
  height: 50px;
  background: #5e5e5e;
  border-radius: 25px;
  padding-left: 10px;
  color: white;
}
.insert_pw{
  position:absolute;
  top: 170px;
  font-size: 2em;
  margin-top: 10px;
}
.pw_input_box{
  position:absolute;
  left:70px;
  top:-5px;
  padding-left: 10px;
  margin-left: 10px;
  margin-top: 10px;
  width: 450px;
  height: 50px;
  background: #5e5e5e;
  border-radius: 25px;
  color:white;
}
.pw_label{
  position: absolute;
  top: 10px;
  color: white;
  letter-spacing: 5px
}
/* .logins{
  display: table;
} */
.kakao_yellow{
  position: absolute;
  background: #ffcd00;
  left:130px;
  top: 300px;
  width: 160px;
  height: 45px;
  transform: translate(-50%, -50%);
  border-radius: 20px;
  text-align: center;
  display: table;
}
.kakao_label{
  display: table-cell;
  vertical-align:middle;
  font-size: 1.2em;
}
.google_red{
  position: absolute;
  background: #ea4335;
  left: 318px;
  top: 300px;
  width: 160px;
  height: 45px;
  transform: translate(-50%, -50%);
  border-radius: 20px;
  text-align: center;
  display: table;
}
.google_label{
  display: table-cell;
  vertical-align:middle;
  color: white;
  font-size: 1.2em;
}
.login_blue{
  position: absolute;
  background: rgb(0, 89, 255);
  left: 500px;
  top: 300px;
  width: 160px;
  height: 45px;
  transform: translate(-50%, -50%);
  border-radius: 20px;
  text-align: center;
  display: table;
  font-size: 1.2em;
}
.logins_deco_social{
  position: absolute;
  background: rgb(0, 89, 255);
  left: 500px;
  top: 340px;
  width: 160px;
  transform: translate(-50%, -50%);
  border-radius: 20px;
  text-align: center;
  display: table;
  font-size: 0.8em;
  color: white;
}
.logins_deco_platform{
  position: absolute;
  background: linear-gradient(90deg, #ffcd00, #ea4335);
  left: 225px;
  top: 340px;
  width: 350px;
  transform: translate(-50%, -50%);
  border-radius: 20px;
  text-align: center;
  display: table;
  font-size: 0.8em;
  color: white;
}
.login_label{
  display: table-cell;
  vertical-align:middle;
  color: white;
}
.signup_button{
  position: absolute;
  left: 80px;
  top: 410px;
  transform: translate(-50%, -50%);
  color: white;
  text-decoration: underline;
  font-size: 1.2em;
}
.where_my_account
{
  position: absolute;
  left: 230px;
  top: 410px;
  transform: translate(-50%, -50%);
  color: white;
  text-decoration: underline;
  font-size: 1.2em;
}

.partion_block{
  position: absolute;
  left: 650px;
  top: 225px;
  transform: translate(-50%, -50%);
  width: 5px;
  height: 370px;
  border-radius: 25px;
  background: #5e5e5e;
}
.adver_section{
  position: absolute;
  left: 815px;
  top: 225px;
  transform: translate(-50%, -50%);
  width: 250px;
  height: 350px;
  border-radius: 25px;
  overflow: hidden;
  border: none;
}
.adver_info{
  position: absolute;
  top: 230px;
  width: 100%;
  height: 140px;
  background: black;
  opacity: 0.8;
  z-index: 1;
}
.adver_img{
  position: absolute;
  top: 0px;
  width: 100%;
  height: 350px;
  object-fit: cover;
  z-index: -1;
}



</style>


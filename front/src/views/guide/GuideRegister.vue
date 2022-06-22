<template>
  <v-container style="width: 70%; ">
    <v-row>
      <v-col align="center">
        <v-card style="width: 60%;margin: 50px;padding: 50px">
          <h2>가이드 등록</h2>
          <h1>{{ $store.state.user.userId }}</h1>
          <v-text-field
            v-model="guide_license"
            name="guideLicense"
            type="text"
            class="license"
            placeholder="자격증"
            style="width: 60%; margin: 10px"
          />

          <v-btn
            class="btn"
            style="width: 60%; margin: 10px"
            @click="onsubmit()"
          >
            JOIN
          </v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>


import axios from "axios";

export default {
  name: 'GuideRegister',
  components: {},
  data() {
    return {
      user_id: '',
      guide_license: '',
    }
  },
  methods: {
    onsubmit() {
      if (this.$store.state.user.userId === '') {
        alert("로그인후 이용해주세요")
      }
      var sendform = new FormData();

      sendform.append('user_id', this.$store.state.user.userId);
      sendform.append('guide_license', this.guide_license);


      axios({
        method: 'post',
        url: '/api/gjoin',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        if (res.data == 'ok') {
          alert('ok');
          window.location.href = '/';
        } else {
          alert('가입 성공')
        }
      });

    }
  }
}

</script>
<style scoped>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family: sans-serif;

}


.joinGuide {

  width: 400px;
  height: 40px;
  padding: 30px;
  background-color: #FFFFFF;
  text-align: center;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 15px;
  position: relative;
}

.joinForm h2 {
  text-align: center;
  margin: 30px;
}

.textForm {
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}


.license {
  width: 100%;
  border: none;
  outline: none;
  color: #636e72;
  font-size: 16px;
  height: 25px;
  background: none;
}


.btn {
  position: relative;
  margin-bottom: 40px;
  height: 40px;
  background: linear-gradient(125deg, #81ecec, #6c5ce7, #81ecec);
  background-size: 200%;
  color: white;
  font-weight: bold;
  border: none;
  cursor: pointer;
  transition: 0.4s;
  display: inline;
}

.btn:hover {
  background-position: right;
}
</style>

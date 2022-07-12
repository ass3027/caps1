<template>
  <div>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Myeongjo&family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    <div class="card">
      <div class="cardInfo">
        <div
          class="cardImg"
          @click="show=!show"
        >
          <img
            :src="'/api/photo/' + product.pic_name"
            style="width: 80%"
          >
        </div>
        <div class="cardText">
          <div class="cardTitle">
           <span style="font-size: 20px; font-weight: 900; color: black;">{{ product.pd_name }}</span>
          </div>
          <br>
          <div class="cardPrice">
            <span class="priceScore" style="font-size: 20px; font-weight: 350; color: black;">가격 {{ product.pd_price }}원</span>
          </div>
          <br>
          <hr>
          <br>
          <div class="detail">
            <div class="">
              <v-dialog
                v-model="dialog"
                width="800"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text
                    color="red lighten-2"
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    객실 이용 안내
                  </v-text>
                </template>

                <v-card>
                  <v-card-title class="text-h5 grey lighten-2">
                    객실 이용 안내
                  </v-card-title>

                  <v-card-text style="padding: 100px">
                    <h6>객실 기본 정보 : {{ product.room_human }}</h6>
                    <br>
                    <hr>
                    <br>
                    <h6>객실 침대 정보 : {{ product.bed_info }}</h6>
                    <br>
                    <hr>
                    <br>
                    <h6>객실 용품 : {{ product.pd_info }}</h6>
                  </v-card-text>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="primary"
                      text
                      @click="dialog = false"
                    >
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </div>
          <br>
          <div class="cardPlace">
          </div>
          <br>
          <v-btn class="예약" @click="go">
            예약하기
          </v-btn>
        </div>
      </div>
      <div
        v-show="show"
        class="cardMoreInfo"
      >
        <div
          v-for="(image, index) in productImage"
          :key="index"
          class="imgGaurd"
        >
          <img
            :src="'/api/photo/' + image.pic_name"
            style="width: 80%"
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PlaceProduct",
  props: ['product'],
  data: () => ({
    show: false,
    productImage: [],
    dialog: false,
  }),

  created() {
    console.log(this.product.pd_id)
    axios({
      method: 'GET',
      url: '/api/productImage',
      params: {'pd_id': this.product.pd_id}
    })
      .then((res) => {
        this.productImage = res.data
        console.log(this.productImage)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    go() {
      this.$router.push(`/productBook/${this.product.pd_id}`)
    }
  },

}
</script>

<style scoped>

* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family: sans-serif;
}

.card {
  width: 100%;
  /*background: #5F5F5F;*/
  border: 1px solid rgba(0, 0, 0, 0.08);
}

.cardInfo {
  display: flex;
  justify-content: space-between;
  margin: 10px;
  /*background: #81ecec;*/
}

.cardImg {
  display: block;
  width: 40%;
  /*background: red;*/
  margin: 5px;
}

.cardImg img {
  max-width: 100%;
  height: auto;
}

.cardText {
  width: 45%;
  display: block;
  align-self: auto;
  margin: auto;
  /*background: tomato;*/
}

.cardMoreInfo {
  display: flex;
  justify-content: center;
  width: 100%;
  /*background: blue;*/
}

.cardMoreInfo img {
  margin: 10px;
}

.imgGaurd {
  margin: 10px;
  display: flex;
  justify-content: center;
  height: auto;

  background: rgba(0, 0, 0, 0.1);
}

.예약 {
  width: 80%;
}

.theme--light.v-btn.v-btn--has-bg {
  background: #139DF2;
}


</style>

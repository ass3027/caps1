<template>
  <div>
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
            {{ product.pd_name }}
          </div>
          <br>
          <div class="cardPrice">
            가격 <span class="priceScore">{{ product.pd_price }}원</span>
          </div>
          <br>
          <div class="cardPlace">
            <hr>
          </div>
          <br>
          <v-btn @click="go">
            예약
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
            style="width: 50%"
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
    go(){
      this.$router.push({name:'productBook',params:{product: this.product}})
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
  width: 45%;
  /*background: red;*/
  float: left;
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
</style>

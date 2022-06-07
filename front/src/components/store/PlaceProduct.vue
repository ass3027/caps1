<template>
  <div>
    <div class="card">
      <div class="cardInfo">
        <div class="cardImg" @click="show=!show">
          <img src="https://cdn.vuetifyjs.com/images/cards/cooking.png">
        </div>
        <div class="cardText">
          <div class="cardTitle">title</div>
          <div class="cardPrice">price<span class="priceScore">110,000</span></div>
          <br>
          <div class="cardPlace">ETC</div>
          <button>예약</button>
        </div>
      </div>
      <div v-show="show" class="cardMoreInfo">
        <div class="imgGaurd">
          <img src="https://cdn.vuetifyjs.com/images/cards/cooking.png">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlaceProduct",
  data: () => ({
    show: false,
  }),
}
</script>

<style scoped>
*{
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family: sans-serif;
}
.card{
  width: 100%;
  /*background: #5F5F5F;*/
  border: 1px solid rgba(0,0,0,0.08);
}
.cardInfo{
  display: flex;
  justify-content: space-between;
  margin: 10px;
  /*background: #81ecec;*/
}
.cardImg{
  display: block;
  width: 45%;
  /*background: red;*/
  float: left;
}
.cardImg img{
  max-width: 100%;
  height: auto;
}
.cardText{
  width: 45%;
  display: block;
  align-self: auto;
  /*background: tomato;*/
}
.cardMoreInfo{
  display: flex;
  justify-content: center;
  width: 100%;
  /*background: blue;*/
}
.cardMoreInfo img{
  margin: 10px;
}
.imgGaurd{
  margin: 10px;
  display: flex;
  justify-content: center;
  height: auto;

  background: rgba(0,0,0,0.1);

}
</style>

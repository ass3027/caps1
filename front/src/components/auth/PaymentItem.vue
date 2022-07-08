<template>
  <div>
    <div class="date">{{ paytime }}</div>
    <div class="payment_goods">
      <div class="name"><span>[<span v-if="type === 'guide'">가이드</span><span v-if="type === 'hotel'">호텔</span>] {{ place_info !== null ? place_info.title : 'a' }}</span></div>
      <div class="payment_info">
        <div class="thumb">
          <img :src="place_info !== null ? place_info.firstimage : 'a'">
        </div>
        <div class="desc">
          <dl>
            <dt>결제번호</dt>
            <dd>{{ payment.pay_id }}</dd>
          </dl>
          <dl>
            <dt>결제금액</dt>
            <dd style="float: left">{{ payment.pay_price }}</dd>
          </dl>
        </div>

        <button @click="reviewWrite" style="float: right;border: 1px solid #dddfe1;font-size: 14px;padding: 4px">후기 작성 ></button>

      </div>
      <div class="payment_status"></div>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  components: {},
  props: {
    payment: null,
    type: null,
  },
  data() {
    return {
      gitem_info: null,
      place_info: null,
    }
  },
  computed: {
    paytime() {
      var paytime = this.payment.pay_time

      if(paytime === undefined) return '값 없음'

      paytime = paytime.replaceAll('-', '.')
      paytime = paytime.replaceAll('T', ' ')
      paytime = paytime.slice(0, -2)
      paytime = paytime.replace(/(\d\d:\d\d:)/gi, `($1)`)
      paytime = paytime.replace(':', '시 ')
      paytime = paytime.replace(':', '분')


      console.log('paytime', paytime)

      return paytime
    }
  },
  mounted() {

    axios.get('/api/payment/placeInfo/' + this.type +'/' + this.payment.pay_id).then(res => {
      console.log('place_info', res.data)
      this.place_info = res.data
    })


  },
  methods: {
    reviewWrite() {
      this.$router.push({
        name: "ReviewCreateView",
        params:{type:'호텔 상품', id: this.payment.pay_id}
      })
    }
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

li {

}

.search_date {
  position: relative;
  z-index: 1
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
  letter-spacing: -.5px
}

.search_date .btn_layer:after {
  content: "";
  position: absolute;
  left: 128px;
  top: 18px;
  width: 12px;
  height: 8px;
  background: url(https://res.kurly.com/pc/ico/2008/ico_down_12x8.svg) no-repeat 0 0;
  background-size: 12px 8px
}

.search_date .btn_layer.on:after {
  transform: rotate(-180deg);
  -webkit-transform: rotate(-180deg);
  -moz-transform: rotate(-180deg);
  -ms-transform: rotate(-180deg);
  -o-transform: rotate(-180deg)
}

.search_date .layer_search {
  display: none;
  position: absolute;
  right: 0;
  top: -30px;
  width: 157px;
  border: 1px solid #dddfe1;
  border-top: 0
}

.search_date .layer_search li {
  background-color: #fff;
  border-top: 1px solid #dddfe1
}

.search_date .layer_search li:first-child {
  border-top: 0
}

.search_date .layer_search a {
  display: block;
  overflow: hidden;
  height: 32px;
  padding: 7px 0 0 8px;
  font-size: 12px;
  color: #666;
  line-height: 18px;
  letter-spacing: -.2px;
}

.search_date .layer_search a.on {
  background-color: #f7f7f7
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

.payment_info {
  overflow: hidden;
  padding: 14px 0 20px;

}
.payment_info dt {
  float: left;

  padding-right: 15px;
  font-size: 12px;
  color: #000;
  line-height: 20px;
}

.payment_info dd {
  font-size: 14px;
  line-height: 20px;
  font-weight: 700;
  color: #000;
}

.thumb {
  float: left;
  width: 60px;
  margin-right: 20px;
}

.thumb img {
  width: 60px;
  height: auto;
}

.desc {
  overflow: hidden;
  padding-top: 1px;
}

.name {
  padding: 20px 0 13px;
  border-bottom: 1px solid #dddfe1;
}

.payment_goods {
  position: relative;
  margin-top: 10px;
  padding: 0 20px;
  border: 1px solid #dddfe1;
}

.date {
  padding-top: 20px;
  font-size: 16px;
  line-height: 24px;
  font-weight: 700;
  color: #666;
}

dl {
  padding-top: 3px;
}
</style>

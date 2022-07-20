<template>
  <v-container>
    <table
      style="display:table; border: 2px solid black; height: 200px; padding: 20px 15px; width: 600px; margin: 0 auto; background-color: white"
    >
      <div style="margin-top: 10px">
        <h1 style="display: inline; font-size: large; margin-left: 5px">
          내장소 상세보기
        </h1>
        <div style="display: inline; float: right; font-size: large; margin-right: 5px">
          장소번호 #{{ this.placeDetailItemData.pl_id }}
        </div>
      </div>
      <img
        :src="placeDetailItemData.firstimage"
        style="width:100%;height:350px;"
      />

      <table id="order_detail">
        <thead scope="row">
        <tr/>
        </thead>
        <tbody>
        <tr>
          <th>숙소이름</th>
          <td>{{ this.placeDetailItemData.title }}</td>
        </tr>
        <tr>
          <th>주소</th>
          <td>{{ this.placeDetailItemData.addr1 }}</td>
        </tr>
        <tr>
          <th>사업자번호</th>
          <td>{{ this.placeDetailItemData.store_id }}</td>
        </tr>

        <!--        <tr>-->
        <!--          <th>나로부터</th>-->
        <!--          <td>{{ degree_user_start }}Km</td>-->
        <!--        </tr>-->
        <tr>
          <th>장소정보</th>
          <td>
          </td>
          <v-btn @click="productAdd()">상품등록</v-btn>
        </tr>
        </tbody>
      </table>

    </table>
    <div v-if="this.productItemAddCon===`상품등록`">
      <v-col>
        <h2>상품을 등록하세요</h2>
      </v-col>
      <v-row>
        <ProductItem style="margin-left:auto; margin-right: auto" :plId="plId"></ProductItem>

      </v-row>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import ProductItem from "@/views/store/ProductItem";

export default {
  name: "placeDetail",
  components: {
    ProductItem
  },
  props: {
    place: {type: Object}
  },
  data() {
    return {
      plId: this.place.pl_id,
      placeDetailItemData: '',
      productItemAddCon: null,
      productData: [],
      productList: [],
    }
  },
  mounted() {
    this.placeDetail()
  },

  methods: {
    placeDetail() {
      console.log(this.pl_id)
      axios.get('/api/placeDetail/' + this.place.pl_id)
        .then((res) => {
          this.placeDetailItemData = res.data;
          console.log("placeDetail에 있는놈")
          console.log(this.placeDetailItemData)
        })
    },

    //장소 상품 등록
    productAdd() {
      this.productItemAddCon = `상품등록`;
    },

  }
}
</script>

<style scoped>

.btn_type2 {
  display: block;
  overflow: hidden;
  width: 100%;
  height: 54px;
  border-radius: 3px;
  text-align: center;
  border: 1px solid black;
  margin-top: 10px;
  background-color: #1e90cc;
}

#order_detail > tbody > tr > td {
  width: 80%;
}

#order_detail > tbody > tr > th {
  width: 20%;
}

body {
  padding: 1.5em;
  background: #f5f5f5
}

td div {
  display: inline-block;
  width: 50%;
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;


}


td, th {
  padding: 1em .5em;
  vertical-align: middle;
}

td {
  border-bottom: 1px solid rgba(0, 0, 0, .1);
  background: #fff;
}

a {
  color: #73685d;
}

@media all and (max-width: 768px) {

  table, thead, tbody, th, td, tr {
    display: block;
  }

  th {
    text-align: right;
  }

  table {
    position: relative;
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0, 0, 0, .2);
  }

  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }

  tr {
    display: inline-block;
    vertical-align: top;
  }

  th {
    border-bottom: 1px solid #a39485;
  }

  td {
    border-bottom: 1px solid #e5e5e5;
  }


}

#order_info {
  margin-top: 15px;
  border: 1px solid black;

}

.order_time_info {
  font-size: larger;
}

.short_addr {
  font-size: x-large;
}

#orderItem > div {
  float: left;
  width: 25%;
  text-align: center;
}

.order_item_info {
  float: left;
  width: 26.6%;
}

#gnb {
  display: flex;
  justify-content: center;
}

.gnb_stop {
  z-index: 300;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 67px;
  background-color: white;
}

.login-box {
  display: block;
  /*justify-content: flex-end;*/
  float: right;
}

.menu-Bar {
  display: flex;
  justify-content: center;

}

/*.text-center {*/
/*  margin-top: 20px;*/
/*  display: flex;*/
/*  justify-content: center;*/
/*}*/

/*.hotel-list-form {*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/

/*.select-box {*/
/*  padding: 0 2%;*/
/*}*/

/*.select-size {*/
/*  width: 50%;*/
/*}*/

/*.divider-padding {*/
/*  margin-top: 10px;*/
/*}*/
</style>

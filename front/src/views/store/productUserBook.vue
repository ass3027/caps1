<template>
  <v-app>
    <v-col>
      <template>
        <h3>예약조회</h3>
        <v-data-table
          :headers="bookHeaders"
          :items="bookList"
          :items-per-page="5"
        />
      </template>
    </v-col>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "ProductUserBook",
  data(){
    return{
      bookList : [],
      bookHeaders: [
        {text: '예약시설', align:'start', sortable: false, value: 'title'},
        {text: '예약상품', value: 'pd_name' },
        {text: '체크인 시간', value: 'st_date'},
        {text: '체크아웃 시간', value: 'end_date'},
        {text: '예약자', value: 'user_id'},
      ]
    }
  },
  mounted() {
    axios({
      method:'GET',
      url:'/api/productUserBook',
      params:{
        'id':this.$store.state.user.userId
      }
    })
      .then((res)=>{
        this.bookList = res.data;
        console.log(res.data)
      })
  }
}
</script>

<style scoped>

</style>

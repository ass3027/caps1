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
  name: "productBookView",
  data(){
    return{
      bookList : [],
      bookHeaders: [
        {text: '예약번호', align:'start', sortable: false, value: 'book_id'},
        {text: '예약시간', value: 'book_time'},
        {text: '예약금액', value: 'book_price'},
        {text: '예약자', value: 'user_id'},
      ]
    }
  },
  mounted() {
    axios.get("/api/productBook/" + this.$store.state.user.userId)
      .then((res) => {
        this.bookList = res.data;
        console.log(this.bookList)
      })
  }
}
</script>

<style scoped>

</style>

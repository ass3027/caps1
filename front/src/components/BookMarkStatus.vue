<template>
  <div>
  <span
    v-if="bookMarkStatus==0"
    style="float:left;cursor:pointer"
    @click="insertBookMark"
  >
    <v-icon
      color="red"
    >
      mdi-heart-plus-outline
    </v-icon>
    찜하기
  </span>
  <span
    v-else
    style="float:left;cursor:pointer"
    @click="deleteBookMark"
  >
    <v-icon
      color="red"
    >
      mdi-heart-plus
    </v-icon>
    취소하기
  </span>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BookMarkStatus",
  components: {},
  props: ['pl_id'],
  data() {
    return {
      bookMarkStatus: ''
    }
  },
  computed: {},
  mounted() {
    this.getBookMarkStatus()
  },
  methods: {
    getBookMarkStatus(){
      axios.get('/api/bookmark/getBookMarkStatus', {params: {user_id: this.$store.state.user.userId, pl_id: this.pl_id}})
        .then((res) => {
          this.bookMarkStatus = res.data
        })
    },
    insertBookMark(){
      if(this.$store.state.user.userId=='anonymousUser'||this.$store.state.user.userId==''){
        alert("로그인을 해주세요")
        return
      }
      axios.post('/api/bookmark/insertBookMark', {},{params: {user_id: this.$store.state.user.userId, pl_id: this.pl_id}})
        .then(() => {
          this.getBookMarkStatus()
        })
    },
    deleteBookMark(){
      axios.delete('/api/bookmark/deleteBookMark', {params: {user_id: this.$store.state.user.userId, pl_id: this.pl_id}})
        .then(() => {
          this.getBookMarkStatus()
        })
    },

  },

}
</script>

<style scoped>

</style>

<template>
  <div>
  
    <MyPageHeader></MyPageHeader>
    <div id="card">
    
      <template>
        <v-card>
          <v-toolbar
            flat
            color="primary"
            dark
          >
            <v-toolbar-title>즐겨찾기</v-toolbar-title>
          </v-toolbar>
          <v-tabs vertical>
            <v-tab>
            
              <v-icon left >mdi-bookmark</v-icon>
              모두보기
            </v-tab>
            <v-tab >키퍼</v-tab>
            <v-tab >숙소</v-tab>
            <v-tab>관광지</v-tab>

            <v-tab-item
              v-for="(posts,index) in postArray"
              :key="index"
            >
              <v-card flat>
                <v-col
                  v-for="(post, index) in posts"
                  :key="index" style="display:flex;float:left; width:400px"
                >
                  <v-card
                    @click="url(post.pl.id)"
                    style="width: 400px; height: 450px"
                    >
                    <v-img
                      class="white--text align-end"
                      height="200px"
                      v-bind:src="post.firstimage"
                    />
                    <v-card-subtitle class="text--primary">
                      <p>{{post.addr1}}</p>
                      <p>{{post.title}}</p>
                      <p>{{post.tel}}</p>
                      <p>{{post.user_id}}</p>
                    </v-card-subtitle>
                    <v-btn
                      color="orange"
                      text
                      @click="url"
                    >사이트 이동하기</v-btn>
                  </v-card>
                </v-col>
              </v-card>
            </v-tab-item>



          </v-tabs>
        </v-card>
      </template>
    </div>
  </div>
  
</template>

<script>
import MyPageHeader from "@/components/store/MyPageHeader";
import axios from "axios";
export default {
  components:{
    MyPageHeader
  },
  data(){
    return{
      pl_id:'',
      addr1:'',
      title:'',
      tel:'',
      user_id:'',
      firstimage:'',
      postArray:[] //배열
    }
  },
  mounted() {
    axios.get("/api/bookmark/BookMark")
    .then((res)=>{
      console.log(res.data.all)
      this.postArray.push(res.data);
    })

    // this.all()
    // this.keeper()
    // this.hotel()
  },
  methods:{
    // all(){
    //   axios.get('/api/bookmark/BookMark')
    //     .then((res)=>{
    //       console.log(this.postArray) //pp
    //       this.postArray.push(res.data);
    //       console.log(this.posts)
    //
    //     })
    // },
    url(){ //사이트이동하기
      this.$router.push("/")
    },
    // keeper(){
    // // this.$router.push("bookmark/keeper")
    // //   console.log(this.posts.user_id)
    //   axios.get("/api/bookmark/keeper",{
    //   })
    //     .then((res)=>{
    //       console.log(res)
    //       this.postArray.push(res.data);
    //     })
    // },
    // hotel(){
    //   // this.$router.push("/")
    //   axios.get("/api/bookmark/hotel")
    //   .then((res)=>{
    //     console.log(res)
    //     this.postArray.push(res.data);
    //     console.log(res.data)
    //   })
    // }
  }
}
</script>

<style scoped>
#card {
  margin: 50px;


}

</style>

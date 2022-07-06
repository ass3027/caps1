<template>
  <div>
    <MyPageHeader/>
    <!--  <BookMarkView></BookMarkView>-->
    <template>
      <v-card>
        <v-toolbar
          color="primary"
          dark
          flat
        >
          <v-toolbar-title>즐겨찾기</v-toolbar-title>
        </v-toolbar>
        <v-tabs vertical>
          <v-tab>
            <v-icon
              left
              type="button"
              @click="all"
            >
              mdi-bookmark
            </v-icon>
            모두보기
          </v-tab>
          <v-tab
            type="button"
            @click="keeper"
          >
            키퍼
          </v-tab>
          <v-tab
            type="button"
            @click="hotel"
          >
            숙소
          </v-tab>
          <v-tab>관광지</v-tab>

          <!--        <v-tab-item>-->
          <!--          <v-card flat>-->
          <!--            <v-col-->
          <!--              v-for="(post, index) in posts"-->
          <!--              :key="index" style="display:flex;float:left; width:400px"-->
          <!--            >-->
          <!--              <v-card-->
          <!--                @click="url(post.pl.id)"-->
          <!--                style="width: 400px; height: 450px"-->
          <!--              >-->
          <!--                <v-img-->
          <!--                  class="white&#45;&#45;text align-end"-->
          <!--                  height="200px"-->
          <!--                  v-bind:src="post.firstimage"-->
          <!--                />-->
          <!--                <v-card-subtitle class="text&#45;&#45;primary">-->
          <!--                  <p>{{post.addr1}}</p>-->
          <!--                  <p>{{post.title}}</p>-->
          <!--                  <p>{{post.tel}}</p>-->
          <!--                  <p>{{post.user_id}}</p>-->
          <!--                </v-card-subtitle>-->
          <!--                <v-btn-->
          <!--                  color="orange"-->
          <!--                  text-->
          <!--                  @click="url"-->
          <!--                >사이트 이동하기</v-btn>-->
          <!--              </v-card>-->
          <!--            </v-col>-->
          <!--          </v-card>-->
          <!--        </v-tab-item>-->


          <v-tab-item>
            <v-card flat>
              <v-col
                v-for="(post, index) in posts"
                :key="index"
                style="display: flex;float: left; width: 400px"
              >
                <v-card
                  style="width: 400px; height: 450px"
                  @click="url(post.pl.id)"
                >
                  <v-img
                    :src="post.firstimage"
                    class="white--text align-end"
                    height="200px"
                  />
                  <v-card-subtitle class="text--primary">
                    <p>{{ post.addr1 }}</p>
                    <p>{{ post.title }}</p>
                    <p>{{ post.tel }}</p>
                    <p>{{ post.user_id }}</p>
                  </v-card-subtitle>
                  <v-btn
                    color="orange"
                    text
                    @click="url"
                  >
                    사이트 이동하기
                  </v-btn>
                </v-card>
              </v-col>
            </v-card>
          </v-tab-item>
        </v-tabs>
      </v-card>
    </template>
  </div>
</template>

<script>
import MyPageHeader from "@/components/store/MyPageHeader";
import axios from "axios";

export default {
  components: {
    MyPageHeader,
    // BookMarkView
  },
  data() {
    return {
      pl_id: "",
      addr1: "",
      title: '',
      tel: '',
      user_id: '',
      firstimage: '',
      posts: 'pp', //객체
    }
  },
  // mounted() {
  //   axios.get('/api/bookmark/BookMark', {
  //     params:{
  //       user_id:this.$store.state.user.userId
  //     }
  //   })
  //     .then((res)=>{
  //       console.log(this.post) //pp
  //       this.posts = res.data;
  //       console.log(this.posts)
  //     })
  // },
  methods: {
    all() {
      this.$router.push("/bookmark")
    },
    url() {
      this.$router.push("/")
    },
    keeper() {
      this.$router.push("/bookmark/keeper").catch(() => {
      });
      axios.get("/api/bookmark/keeper", {
        params: {
          user_id: this.$store.state.user.user_id
        }
      })
        .then((res) => {
          console.log(res)
          this.posts = res.data;
        })
    },
    hotel() {
      // axios.get("/api/bookmark/hotel", {
      //   params: {
      //     user_id: this.$store.state.user.user_id
      //   }
      // })
      //   .then((res) => {
      //     console.log(res.data)
      //     this.$router.push("/")
      //   })
      // }
    }
  }
}
</script>

<style scoped>

</style>

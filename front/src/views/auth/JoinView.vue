<template>
  <v-app
    id="app"
    style="padding-top: 35px"
  >
    <div class="member-Join">
      <p class="text-center">
        회원가입
      </p>
    </div>

    <v-row
      justify="space-around"
      style="padding-top: 35px"
    >
      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
      >
        <div>
          <v-img src="@/image/user(2).png" />
        </div>
        <div class="btn_content">
          <v-btn
            class="btn_user"
            color="white"
            @click="$router.push({name:'userJoin'})"
          >
            일반회원가입
            <!--              @click="$router.push({name:'userJoin'})"-->
          </v-btn>
        </div>
      </v-col>
      <!--        <v-divider vartical />-->
      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
      >
        <div>
          <v-img src="@/image/delivery-man.png" />
        </div>
        <div class="btn_content">
          <v-btn
            class="btn_user"
            color="white"
            @click="$router.push({name:'DeliveryJoin'})"
          >
            운송원 회원가입
          </v-btn>
        </div>
      </v-col>

      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
      >
        <div>
          <v-img src="@/image/open-box.png" />
        </div>
        <div class="btn_content">
          <v-btn
            class="btn_user"
            color="white"
            @click="$router.push({name:'KeeperJoin'})"
          >
            키퍼 회원가입
          </v-btn>
        </div>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
export default {
  name: "JoinView.vue",
  methods:{
    userJoin(){
      this.$router.push("/userJoin")
    }

  }
}

</script>


<style scoped>
.member-Join{
  margin: 10px;
  font-size: 40px;
}
.btn_content{
  margin: 10px;
  text-align: center;

}
</style>

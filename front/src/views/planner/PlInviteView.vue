<template>
  <v-container style="width: 70%;">
    <PlannerHeader />
    <v-row />
    <v-row style="height:100%;text-align: center">
      <v-col
        col="5"
        style="width:40%"
      >
        <v-card style="margin-bottom: 20px">
          <h2>그룹생성자</h2>
          <v-simple-table>
            <thead>
              <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>전화번호</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th>asdf1234</th>
                <th>이세진</th>
                <th>010-4065-8016</th>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
        <v-card
          style="margin-bottom: 30px;"
          class="text-center"
        >
          <h2>현재 멤버</h2>
          <v-spacer />
          <v-divider />
          <v-simple-table>
            <thead>
              <tr>
                <th>아이디</th>
                <th>이름</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(data,key) in plan_user_list"
                :key="key"
              >
                <td>
                  {{ data.user_id }}
                </td>
                <td>
                  {{ data.plan_id }}
                </td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
        <v-divider />
        <v-card>
          <h2>초대 보류</h2>
          <v-simple-table>
            <thead>
              <tr>
                <th class="text-center">
                  아이디
                </th>
                <th class="text-center">
                  이름
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(data,index) in invite_list_plan"
                :key="index"
              >
                <td>
                  {{ data.user_id }}
                </td>
                <td>
                  {{ data.plan_id }}
                </td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-col>
      <v-divider vertical />
      <v-col>
        <v-card style="height:120px;padding-bottom: 10px;margin-bottom: 40px">
          <h2>멤버 초대</h2>
          <div
            style="width: 60%;height:60px;float: left;text-align: center;margin-left: 10px">
            <v-text-field
              v-model="input_id"
              placeholder="회원ID"
              md="4"
            />
          </div>
          <div style="width: 30%;height:60px;float: right;margin-right: 20px;padding-top: 4px">
            <v-btn
              color="blue"
              @click="invite"
              @submit.prevent
            >
              초대
            </v-btn>
          </div>


        </v-card>

        <v-divider />

        <v-card>
          <h2>초대 목록</h2>
          <v-divider />

          <h4>내가 초대된</h4>
          <v-simple-table>
            <thead>
              <tr>
                <th>아이디</th>
                <th>이름</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(data,index) in invite_list_user"
                :key="index"
              >
                <td>
                  {{ data.user_id }}
                </td>
                <td>
                  {{ data.plan_id }}
                </td>
                <td>
                  <v-btn
                    class="button"
                    @click="accept(data)"
                  >
                    수락
                  </v-btn>
                </td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import PlannerHeader from "@/components/planner/PlannerHeader";

export default {
  name: "PlInviteView",
  components: {PlannerHeader},
  data() {
    return {
      plan_user_list: ['ㅁ', 'ㄴ', 'ㅇ', 'ㄹ'],
      input_id: '',
      invite_list_user: [],
      invite_list_plan: [],

      plan_list: []
    }
  },
  computed: {
    user_id() {
      return this.$store.state.user.userId
    },
    plan_id() {
      return this.$store.state.user.planId
    },
  },
  mounted() {
    this.updateInvite()
    this.loadAffiliated()
  },
  methods: {
    reload() {
      this.updateInvite()
      this.loadAffiliated()
    },
    loadAffiliated() {
      axios({
        url: `/api/planner/affiliating?plan_id=${this.plan_id}`,
        method: 'get'
      })
        .then((res) => {
          this.plan_user_list = res.data;
        })
        .catch((err) => {
          console.log(err);
        })
    },
    invite() {
      axios({
        url: `/api/planner/invite`,
        method: 'post',
        data: {
          user_id: this.input_id,
          plan_id: this.plan_id,
        }
      })
        .then((res) => {
          console.log(res.data)
          if (res.data === true) {
            this.updateInvite()
            console.log('success')
            alert('success')
          } else {
            console.log('fail ')
            alert('fail')
          }

        })
    },
    updateInvite() {
      axios({
        url: `/api/planner/inviteListPlan?id=${this.plan_id}`,
        method: 'get'
      })
        .then((res) => {
          this.invite_list_plan = res.data
        })
        .catch((err) => {
          console.log(err);
        });

      axios({
        url: `/api/planner/inviteListUser?id=${this.id}`,
        method: 'get'
      })
        .then((res) => {
          this.invite_list_user = res.data
        })
        .catch((err) => {
          console.log(err);
        })
    },
    accept(data) {
      axios({
        url: '/api/planner/affiliating',
        method: 'post',
        data: data
      })
        .then(() => {
          alert("success")
          location.reload();
        })
        .catch((err) => {
          console.log(err);
        })
    },
  }

}
</script>

<style scoped>


.button {
  border: 2px solid;
  border-radius: 12px;
  color: blue;
  padding: 10px 20px;
  display: inline-block;
  text-align: center;
  text-decoration: none;
  font-size: 18px;
}
.v-sheet.v-card:not(.v-sheet--outlined){
  box-shadow: 0px 0px 20px 4px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%)
}

</style>

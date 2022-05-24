<template>
  <div style="padding-left: 15px">
    <PlannerHeader />
    <v-row>
      <v-col
        cols="12"
        sm="3"
        md="4"
      >
        <v-card>
          <h2>현재 멤버</h2>
          <v-spacer />
          <v-divider />
          <ul
            v-for="(data,key) in plan_user_list"
            :key="key"
          >
            <li>id : {{ data.user_id }} / 플래너 아이디 : {{ data.plan_id }}</li>
          </ul>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-divider />
    </v-row>
    <v-row>
      <v-col
        sm="4"
      >
        <v-card style="padding-bottom: 4px">
          <h2>멤버 초대</h2>
          회원ID
          <v-text-field
            v-model="input_id"
            placeholder="내용을 입력해주세요"
            md="4"
          />
          <button
            class="button"
            @click="invite"
          >
            초대
          </button>
        </v-card>
      </v-col>
      <v-divider vertical />
      <v-col>
        <v-card>
          <h2>내가 초대한</h2>
          <v-simple-table
            dark
          >
            <thead>
              <tr>
                <th>아이디</th>
                <th>이름</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(data,index) in invite_list_plan"
                :key="index"
              >
                <th class="text-center">
                  {{ data.user_id }}
                </th>
                <th class="text-center">
                  {{ data.plan_id }}
                </th>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-divider />
    </v-row>

    <v-row>
      <v-col>
        <v-card>
          <h2>초대 현황</h2>
          <v-divider />

          <h4>내가 초대된</h4>
          <v-simple-table
            dark
          >
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
                <th class="text-center">
                  {{ data.user_id }}
                </th>
                <th class="text-center">
                  {{ data.plan_id }}
                </th>
                <th>
                  <v-btn
                    class="button"
                    @click="accept(data)"
                  >
                    수락
                  </v-btn>
                </th>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import PlannerHeader from "@/components/PlannerHeader";

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
      console.log("axios 어떻게 쓰더라?? 개망ㅋㅋ")
      axios({
        url: `/api/planner/invite`,
        method: 'post',
        data: {
          user_id: this.input_id,
          plan_id: this.plan_id,
        }
      })
        .then((res) => {
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
</style>

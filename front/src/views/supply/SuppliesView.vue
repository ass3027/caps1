<template>
  <v-container
  >
    <planner-header></planner-header>

<!--  제목과 남은할일,완료된수  -->
    <v-row
      justify="center"
    >
      <v-col cols="4" class="">
        <h2 class="text-h4 success--text pl-4">
          할 일:&nbsp;
          <v-fade-transition leave-absolute>
        <span :key="`tasks-${tasks.length}`">
          {{ tasks.length }}
        </span>
          </v-fade-transition>
        </h2>

      </v-col>
      <v-col
        cols="4"
      >
        <v-row
          align="center"
        >
          <v-divider vertical></v-divider>

          <v-col>
            남은 할일 수: {{ remainingTasks }}
          </v-col>
          <v-divider vertical></v-divider>

          <v-col>
            완료된 수: {{ completedTasks }}

          </v-col>

          <v-spacer></v-spacer>

          <v-progress-circular
            :value="progress"
            class="mr-2"
          ></v-progress-circular>
        </v-row>
      </v-col>
    </v-row>

<!--  준비물리스트  -->
    <v-row>
      <v-divider class="mt-4"></v-divider>
    </v-row>
    <v-row
      justify="center"
    >
      <v-col
        cols="4"
      >
        <v-card v-if="tasks.length > 0">
          <v-slide-y-transition
            class="py-0"
            group
            tag="v-list"
          >
            <template v-for="(task, i) in tasks">
                <v-divider
                  v-if="i !== 0"
                  :key="`${i}-divider`"
                ></v-divider>
              <v-list-item :key="`${i}-${task.name}`">
                <v-row justify="start">
                  <v-col cols="1">
                    <v-list-item-action>
                      <v-checkbox
                        v-model="task.done"
                        :color="task.done && 'grey' || 'primary'"
                      >
                        <template v-slot:label>
                          <div
                            :class="task.done && 'grey--text' || 'primary--text'"
                            class="ml-4"
                            v-text="task.name"
                          ></div>
                        </template>
                      </v-checkbox>

                    </v-list-item-action>

                  </v-col>

                </v-row>
                <v-row justify="start">
                  <v-col cols="4">
                    {{ task.quantity }}개
                  </v-col>
                  <v-col cols="2">
                    <v-btn
                      fab
                      text
                      small
                      color="green"
                      @click="plusQuantity(task)"
                    >
                      +1
                    </v-btn>
                  </v-col>
                  <v-col cols="2">
                    <v-btn
                      fab
                      text
                      small
                      color="red"
                      @click="minusQuantity(task)"
                    >
                      -1
                    </v-btn>
                  </v-col>
                  <v-col cols="6">
                    <v-btn @click="deleteOne(task)">
                      제거
                    </v-btn>
                  </v-col>

                </v-row>

                <v-spacer></v-spacer>

                <v-scroll-x-transition>
                  <v-icon
                    v-if="task.done"
                    color="success"
                  >
                    mdi-check
                  </v-icon>
                </v-scroll-x-transition>
              </v-list-item>
            </template>
          </v-slide-y-transition>
        </v-card>
      </v-col>
<!--  준비물 입력 칸  -->
      <v-col
        cols="4"
      >
        <v-text-field
          v-model="inputItem"
          label="할일을 적어주세요"
          solo
          @keydown.enter="inputList"
          height="200px"
        >
          <template v-slot:append>
            <v-fade-transition>
              <v-icon
                v-if="inputItem"
                @click="inputList"
              >
                mdi-plus-circle
              </v-icon>
            </v-fade-transition>
          </template>
        </v-text-field>
<!--    여행지추천 준비물세트    -->
        <v-card  v-for="(set,i) in sets" :key="i">
          <v-list>
            <v-list-group>

              <template v-slot:activator>
                <v-list-item-content>
                  <v-list-item-title>{{ set[0].pl_name }} Set</v-list-item-title>
                </v-list-item-content>
              </template>

              <v-list-item
                v-for="(supl, i) in set"
                :key="i"
                link
              >
                <v-list-item-title v-text="supl.supl_name"></v-list-item-title>

<!--                <v-list-item-icon>-->
<!--                  <v-icon v-text="icon"></v-icon>-->
<!--                </v-list-item-icon>-->
              </v-list-item>

            </v-list-group>
          </v-list>
        </v-card>
      </v-col>
    </v-row>

<!--  예전거  -->
    <v-btn @click="deleteAll" color="error">
      완료한 준비물 제거
    </v-btn>
    <v-btn v-if="$store.state.user.planId!=0" @click="$router.push({path:'/supplies/sets'})" color="primary">
      준비물 가져오기 페이지로
    </v-btn>

  </v-container>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'
import PlannerHeader from "@/components/PlannerHeader";

export default {
  name: 'SuppliesView',
  components: {
    PlannerHeader,
  },
  data() {
    return {

      inputItem: "",
      todoList: [],
      supplies: [],
      items: ['수건', '휴대폰 충전기', '여권'],
      values: ['수건'],
      value: null,

      tasks: [
        {
          done: false,
          text: 'Foobar',
        },
        {
          done: false,
          text: 'Fizzbuzz',
        },
      ],
      newTask: null,
      sets:[
        [
          {pl_id:0,pl_name:"기본",supl_id:"2",supl_name:"휴대폰 충전기"},
          {pl_id:0,pl_name:"기본",supl_id:"58",supl_name:"물"},
          {pl_id:0,pl_name:"기본",supl_id:"60",supl_name:"마스크"},
          {pl_id:0,pl_name:"기본",supl_id:"43",supl_name:"스킨/로션"},
          {pl_id:0,pl_name:"기본",supl_id:"23",supl_name:"물티슈"},
          {pl_id:0,pl_name:"기본",supl_id:"14",supl_name:"신용카드"},
          {pl_id:0,pl_name:"기본",supl_id:"14",supl_name:"신용카드"},
          {pl_id:0,pl_name:"기본",supl_id:"15",supl_name:"보조 배터리"},
          {pl_id:0,pl_name:"기본",supl_id:"4",supl_name:"선크림"},
        ]
      ],

    }
  },
  computed: {
    completedTasks() {
      return this.tasks.filter(task => task.done).length
    },
    progress() {
      return this.completedTasks / this.tasks.length * 100
    },
    remainingTasks() {
      return this.tasks.length - this.completedTasks
    },
  },
  mounted() {

    this.getMyList()
    this.getList()
    this.getSets()
  },
  methods: {
    getSets() {
      axios.get('/api/getSets/'+this.$store.state.user.planId)
        .then((res)=>{
          var outerArray = [
            [
              {pl_id:0,pl_name:"기본",supl_id:"2",supl_name:"휴대폰 충전기"},
              {pl_id:0,pl_name:"기본",supl_id:"58",supl_name:"물"},
              {pl_id:0,pl_name:"기본",supl_id:"60",supl_name:"마스크"},
              {pl_id:0,pl_name:"기본",supl_id:"43",supl_name:"스킨/로션"},
              {pl_id:0,pl_name:"기본",supl_id:"23",supl_name:"물티슈"},
              {pl_id:0,pl_name:"기본",supl_id:"14",supl_name:"신용카드"},
              {pl_id:0,pl_name:"기본",supl_id:"14",supl_name:"신용카드"},
              {pl_id:0,pl_name:"기본",supl_id:"15",supl_name:"보조 배터리"},
              {pl_id:0,pl_name:"기본",supl_id:"4",supl_name:"선크림"},
            ]
          ];
          var innerArray = [];
          var id = res.data[0].pl_id;
          res.data.forEach((i)=>{

            if(i.pl_id!=id) {

              outerArray.push(innerArray);
              innerArray = [];
              id = i.pl_id;
            }
            innerArray.push(i);

          })
          outerArray.push(innerArray);
          this.sets=outerArray;
        })

    },
    create() {
      this.tasks.push({
        done: false,
        text: this.newTask,
      })

      this.newTask = null
    },
    getList() {
      axios.get('/api/getSupl').then((res) => {
        var supplies = []

        res.data.forEach(function (i) {
          supplies.push(i.supl_name)
        })
        this.supplies = supplies;
        this.items = supplies;
      })

    },
    getMyList() {
      axios({
        method: 'get',
        url: '/api/getMySupl/' + this.$store.state.user.planId,
      })
        .then((res) => {
          res.data.map((i)=>{
            if(i.status == 0) return i.done=false
            else return i.done=true
          })
          this.tasks = res.data;
          console.log(res.data)
        })

    },
    inputList(e) {
      e.preventDefault();
      if (this.inputItem == "") {
        alert("준비물을 입력해주세요");
        return;
      }
      var data2 = {
        plan_id: this.$store.state.user.planId, supl_id: {
          supl_id: "3"
          , supl_name: this.inputItem
        }
      }
      axios({
        method: 'post',
        url: '/api/inputSupl',
        headers: {
          'Content-Type': 'application/json',
        },
        data: JSON.stringify(data2),
      })
        .then(() => {
          this.getMyList();
          this.inputItem = ''
        })
    },
    deleteAll() {

      var newDoneList = this.tasks.filter( (i)=>{
        return i.done==true
      })
      axios({
        method: 'delete',
        url: '/api/delSuplAll',
        headers: {
          'Content-Type': 'application/json',
        },

        data: JSON.stringify(newDoneList),
      })
        .then(() => {
          this.getMyList();
        })
    },
    deleteOne(task) {
      axios({
        method: 'delete',
        url: '/api/delSuplOne',
        headers: {
          'Content-Type': 'application/json',
        },

        data: {plan_supl_id: task.plan_supl_id},
      })
        .then(() => {
          this.getMyList();
        })
    },
    plusQuantity(item) {
      axios({
        method: 'put',
        url: '/api/updateQuantity',
        headers: {
          'Content-Type': 'application/json',
        },

        data: JSON.stringify({plan_supl_id: item.plan_supl_id, quantity: item.quantity + 1}),
      })
        .then(() => {
          this.getMyList();
        })
    },
    minusQuantity(item) {
      if (item.quantity == 1) return;
      axios({
        method: 'put',
        url: '/api/updateQuantity',
        headers: {
          'Content-Type': 'application/json',
        },

        data: JSON.stringify({plan_supl_id: item.plan_supl_id, quantity: item.quantity - 1}),
      })
        .then(() => {
          this.getMyList();
        })
    }
  }
}
</script>
<style scoped>
del {
  text-decoration: line-through;
}
</style>

<template>
  <v-container
  >
    <planner-header></planner-header>
    <!--  제목과 남은할일,완료된수  -->
    <v-row
      justify="start"

    >

      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-col cols="5" class="">

        <h2 class="text-h4 success--text pl-4">
          준비물:&nbsp;
          <v-fade-transition>
        <span :key="`tasks-${tasks.length}`">
          {{ tasks.length }}
        </span>
          </v-fade-transition>
        </h2>

      </v-col>
      <v-col
        cols="5"
      >
        <v-row
          align="center"
        >
          <v-divider vertical></v-divider>

          <v-col cols="4">
            <p class="primary--text">남은 준비물 수: {{ remainingTasks }}</p>
          </v-col>
          <v-divider vertical></v-divider>

          <v-col cols="4">
            <p class="grey--text">완료된 준비물 수: {{ completedTasks }}</p>

          </v-col>

          완료율
          <v-progress-circular
            :value="progress"
            class="mr-2"
          ></v-progress-circular>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-divider class="mt-4"></v-divider>
    </v-row>
    <!--  준비물리스트  -->
    <v-row
      justify="center"
    >
      <v-col cols="1"></v-col>
      <!--      <v-btn @click="alignList">hi</v-btn>-->

      <v-col
        cols="5"
      >

        <v-card v-if="tasks.length > 0">
          <template v-for="(task, i) in tasks">
            <v-divider
              v-if="i !== 0"
              :key="`${i}-divider`"
            ></v-divider>
            <v-list-item :key="`${i}-${task.name}`">
              <v-row justify="space-between">
                <v-col cols="6">
                  <v-list-item-action>
                    <v-checkbox
                      @click="changeDone(task)"
                      v-model="task.done"
                      :color="task.done && 'grey' || 'primary'"
                    >
                      <template v-slot:label>
                        <div
                          :class="task.done && 'grey--text'&&'del' || 'primary--text'"
                          class="ml-4"
                          v-text="task.name"
                        ></div>
                      </template>
                    </v-checkbox>

                  </v-list-item-action>

                </v-col>

                <v-col cols="6">
                  {{ task.quantity }}개
                  <v-btn
                    fab
                    text
                    small
                    color="green"
                    @click="plusQuantity(task)"
                  >
                    +1
                  </v-btn>
                  <v-btn
                    fab
                    text
                    small
                    color="red"
                    @click="minusQuantity(task)"
                  >
                    -1
                  </v-btn>
                  <v-icon color="orange" @click="editStart(task,i)">
                    mdi-square-edit-outline
                  </v-icon>
                  <v-icon color="error" @click="deleteOne(task,i)">
                    mdi-trash-can-outline
                  </v-icon>

                  <v-scroll-x-transition>
                    <v-icon
                      v-if="task.done"
                      color="success"
                    >
                      mdi-check
                    </v-icon>
                  </v-scroll-x-transition>


                </v-col>
              </v-row>

            </v-list-item>
          </template>
        </v-card>
      </v-col>
      <!--  준비물 입력 칸  -->
      <v-col
        cols="4"
      >
        <v-text-field
          v-model="inputItem"
          label="준비물을 적어주세요"
          solo
          @keydown.enter="inputList"
          height="200px"
          class="mb-4"
          :class="{'editMode':editMode,}"

        >
          <template v-slot:append>
            <v-fade-transition>
              <v-icon
                v-if="inputItem&&!editMode"
                @click="inputList"
              >
                mdi-plus-circle
              </v-icon>
            </v-fade-transition>

            <v-icon
              v-if="editMode"
              @click="editOne"
              color="green"
            >
              mdi-check-circle-outline
            </v-icon>

            <v-icon
              v-if="editMode"
              @click="editCancel"
              color="red"
            >
              mdi-close-circle-outline
            </v-icon>


          </template>
        </v-text-field>

        <!--    완료한 준비물 제거 버튼    -->
        <v-row justify="end">
          <v-dialog
            v-model="dialogDel"
            persistent
            max-width="290"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="mb-8 mr-10"
                color="error"
                dark
                v-bind="attrs"
                v-on="on"
              >
                완료한 준비물 제거
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h5">
                완료한 준비물 제거
              </v-card-title>
              <v-card-text>
                정말로 제거하시겠습니까?
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="error"
                  text
                  @click="deleteAll"
                >
                  제거하기
                </v-btn>
                <v-btn
                  color="green darken-1"
                  text
                  @click="dialogDel = false"
                >
                  아니요
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

        </v-row>

        <!--    여행지추천 준비물세트    -->
        <v-card v-for="(set,i) in sets" :key="i">
          <v-list>
            <v-list-group>
              <template v-slot:activator>
                <v-list-item-content>

                  <v-list-item-title>{{ set[0].pl_name }} Set</v-list-item-title>
                </v-list-item-content>
                <v-dialog
                  v-model="dialogAdd"
                  persistent
                  max-width="290"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      모두 추가하기
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title class="text-h5">
                      준비물 세트 추가
                    </v-card-title>
                    <v-card-text>
                      준비물 세트를 추가하시겠습니까?
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        color="green darken-1"
                        text
                        @click="addSet(set)"
                      >
                        예
                      </v-btn>
                      <v-btn
                        color="gray"
                        text
                        @click="dialogAdd = false"
                      >
                        아니요
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </template>

              <v-list-item
                v-for="(supl, i) in set"
                :key="i"
                link
              >
                <v-list-item-title v-text="supl.supl_name"></v-list-item-title>
                <v-btn @click="addSupl(supl)">추가</v-btn>
              </v-list-item>

            </v-list-group>
          </v-list>
        </v-card>
      </v-col>
    </v-row>


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
      editMode: false,
      selectedListFID: '',
      selectedListBID: '',
      dialogAdd: false,
      dialogDel: false,

      tasks: [],
      newTask: null,
      sets: [
        [
          {pl_id: 0, pl_name: "기본", supl_id: "2", supl_name: "휴대폰 충전기"},
          {pl_id: 0, pl_name: "기본", supl_id: "58", supl_name: "물"},
          {pl_id: 0, pl_name: "기본", supl_id: "60", supl_name: "마스크"},
          {pl_id: 0, pl_name: "기본", supl_id: "43", supl_name: "스킨/로션"},
          {pl_id: 0, pl_name: "기본", supl_id: "23", supl_name: "물티슈"},
          {pl_id: 0, pl_name: "기본", supl_id: "14", supl_name: "신용카드"},
          {pl_id: 0, pl_name: "기본", supl_id: "14", supl_name: "신용카드"},
          {pl_id: 0, pl_name: "기본", supl_id: "15", supl_name: "보조 배터리"},
          {pl_id: 0, pl_name: "기본", supl_id: "4", supl_name: "선크림"},
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
    changeDone(task) {
      axios.put('/api/doneSupl', task, {
        params: {
          done: task.done
        }
      })
        .then(() => {
        })
        .catch((err) => {
          alert(err)
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
          res.data.map((i) => {
            if (i.status == 0) return i.done = false
            else return i.done = true
          })
          this.tasks = res.data;
          console.log(res.data)
        })

    },
    inputList(e) {
      e.preventDefault();
      if (this.editMode) return
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
        data: data2,
      })
        .then(() => {
          this.getMyList();
          this.inputItem = ''
        })
    },
    deleteAll() {
      this.dialogDel = false
      var newDoneList = this.tasks.filter((i) => {
        return i.done == true
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
    deleteOne(task, i) {
      axios({
        method: 'delete',
        url: '/api/delSuplOne',
        headers: {
          'Content-Type': 'application/json',
        },

        data: {plan_supl_id: task.plan_supl_id},
      })
        .then(() => {
          this.tasks.splice(i, 1)
          // this.getMyList();
        })
    },
    editStart(task, i) {
      this.editMode = true;
      this.selectedListFID = i;
      this.selectedListBID = task.plan_supl_id;
      this.inputItem = task.name;
    },
    editOne() {
      axios.put('/api/updateSuplOne', {}, {
        params: {
          plan_supl_id: this.selectedListBID,
          name: this.inputItem
        }
      })

        .then(() => {
          this.tasks[this.selectedListFID].name = this.inputItem;
          this.editMode = false;
          this.selectedListBID = null
          this.selectedListFID = null
          this.inputItem = ''
          alert("수정완료")
        })
        .catch((err) => {
          alert(err)
        })
    },
    editCancel() {
      this.editMode = false;
      this.selectedListBID = null
      this.selectedListFID = null
      this.inputItem = ''
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
    },
    //추천 준비물 관련 메소드
    getSets(e) {

      axios.get('/api/getSets/' + this.$store.state.user.planId)
        .then((res) => {
          var outerArray = [
            [
              {pl_id: 0, pl_name: "기본", supl_id: "2", supl_name: "휴대폰 충전기"},
              {pl_id: 0, pl_name: "기본", supl_id: "58", supl_name: "물"},
              {pl_id: 0, pl_name: "기본", supl_id: "60", supl_name: "마스크"},
              {pl_id: 0, pl_name: "기본", supl_id: "43", supl_name: "스킨/로션"},
              {pl_id: 0, pl_name: "기본", supl_id: "23", supl_name: "물티슈"},
              {pl_id: 0, pl_name: "기본", supl_id: "14", supl_name: "신용카드"},
              {pl_id: 0, pl_name: "기본", supl_id: "14", supl_name: "신용카드"},
              {pl_id: 0, pl_name: "기본", supl_id: "15", supl_name: "보조 배터리"},
              {pl_id: 0, pl_name: "기본", supl_id: "4", supl_name: "선크림"},
            ]
          ];
          var innerArray = [];
          var id = res.data[0].pl_id;
          res.data.forEach((i) => {

            if (i.pl_id != id) {

              outerArray.push(innerArray);
              innerArray = [];
              id = i.pl_id;
            }
            innerArray.push(i);

          })
          outerArray.push(innerArray);
          this.sets = outerArray;
        })

    },
    addSupl(supl) {
      var data2 = {
        plan_id: this.$store.state.user.planId, supl_id: {
          supl_id: "3"
          , supl_name: supl.supl_name
        }
      }
      axios({
        method: 'post',
        url: '/api/inputSupl',
        headers: {
          'Content-Type': 'application/json',
        },
        data: data2,
      })
        .then(() => {
          this.getMyList();
        })
    },
    addSet(set) {
      this.dialogAdd = false
      axios.post("/api/sendList", set, {
        params: {
          plan_id: this.$store.state.user.planId
        }
      }).then(() => {
        this.getMyList()
      })

    },
    alignList() {
      alert("최신순정렬")
      this.tasks.sort(function (a, b) {
        if (a.plan_supl_id < b.plan_supl_id) {
          return 1;
        }
        if (a.plan_supl_id > b.plan_supl_id) {
          return -1;
        }
        // a must be equal to b
        return 0;
      });
    }
  }
}
</script>
<style>
.del {
  text-decoration: line-through;
}

.editMode {
  border-color: orange;
  border-style: solid;
}

/*.theme--light.v-text-field--solo > .v-input__control > .v-input__slot {*/
/*  border-color: orange;*/
/*  border-style: solid;*/
/*}*/

</style>

<template>
  <v-container>
    <PlannerHeader />
    <h2>내준비물</h2>

    <div>
      <v-autocomplete
        v-model="values"
        :items="items"
        outlined
        dense
        chips
        small-chips
        label="준비물을 입력해보세요"
        multiple
      />
      <v-btn @click="inputAll">
        선택한 준비물들 한꺼번에 넣기
      </v-btn>
    </div>


    <p><br><br></p>
    <h3>직접입력하기</h3>
    <div>
      <form @submit.prevent="inputList">
        <input
          v-model="inputItem"
          type="text"
          name="city"
          list="suplName"
        >
        <datalist id="suplName">
          <option
            v-for="item in supplies"
            :key="item"
            :value="item"
          />
        </datalist>
        <button>입력</button>
      </form>
    </div>

    <hr>

    <ol>
      <li
        v-for="(item, index) in todoList"
        :key="index"
      >
        {{ item.todo }} {{ item.quantity }}개
        <v-btn
          fab
          text
          small
          color="green"
          @click="plusQuantity(item)"
        >
          +1
        </v-btn>
        <v-btn
          fab
          text
          small
          color="red"
          @click="minusQuantity(item)"
        >
          -1
        </v-btn>
        <v-btn @click="makeDone(item.id,item.done)">
          완료
        </v-btn>
        <v-btn @click="deleteOne(item)">
          제거
        </v-btn>
      </li>
    </ol>
    <hr>
    <ol>
      <li
        v-for="(item, index) in doneList"
        :key="index"
      >
        <del>{{ item.todo }}</del>
        <v-btn @click="makeDone(item.id,item.done)">
          되돌리기
        </v-btn>
        <v-btn @click="deleteOne(item)">
          제거
        </v-btn>
      </li>
    </ol>
    <v-btn
      color="error"
      @click="deleteAll"
    >
      완료한 준비물 제거
    </v-btn>
    <v-btn
      v-if="$store.state.user.planId!=0"
      color="primary"
      @click="$router.push({path:'/supplies/sets'})"
    >
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
      doneList: [],
      supplies: [],
      items: ['수건', '휴대폰 충전기', '여권'],
      values: ['수건'],
      value: null,

    }
  },
  mounted() {
    console.log("첫로딩데이터")

    this.getMyList()
    this.getList()
  },
  methods: {
    test() {
      console.log("test")
    },
    getList() {
      axios.get('/api/getSupl').then((res)=>{
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
        url: '/api/getMySupl/'+this.$store.state.user.planId,
      })
        .then((res) => {
          console.log("내리스트가져오기");
          console.log(res.data);
          var todo = [];
          var done = [];

          res.data.forEach((i) => {
            if (i.supl_id.supl_id == 0) {
              i.supl_id.supl_name = i.name;
            }
            if (i.status == 0) {
              todo.push({id: i.plan_supl_id, done: false, todo: i.supl_id.supl_name, quantity: i.quantity})
            } else {
              done.push({id: i.plan_supl_id, done: true, todo: i.supl_id.supl_name, quantity: i.quantity})
            }
          })
          this.todoList = todo;
          this.doneList = done;

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
      console.log("넣을데이터")
      console.log(data2)
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
          console.log("삽입완료 다시불러옴")
          this.inputItem = ''
        })
    },
    inputAll() {
      if (this.values == "") {
        alert("준비물을 입력해주세요");
        return;
      }
      this.values.forEach((i)=>{
        var data2 = {
          plan_id: this.$store.state.user.planId, supl_id: {
            supl_id: "3"
            , supl_name: i
          }
        }
        console.log("넣을데이터")
        console.log(data2)
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
            console.log("삽입완료 다시불러옴")
            this.values = ''
          })
      })

    },
    makeDone(id, done) {

      axios({
        method: 'put',
        url: '/api/doneSupl',
        headers: {
          'Content-Type': 'application/json',
        },

        data: JSON.stringify({plan_supl_id: id, status: done}),
      })
        .then(() => {
          this.getMyList();
          console.log("상태수정완료 다시불러옴")
        })
    },
    deleteAll() {
      var newDoneList = []
      this.doneList.forEach(function (i) {
        newDoneList.push({plan_supl_id: i.id})
      })
      console.log(newDoneList)
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
          console.log("삭제완료 다시불러옴")
        })
    },
    deleteOne(item) {
      console.log(item.id)
      axios({
        method: 'delete',
        url: '/api/delSuplOne',
        headers: {
          'Content-Type': 'application/json',
        },

        data: {plan_supl_id:item.id},
      })
        .then(() => {
          this.getMyList();
          console.log("삭제완료 다시불러옴")
        })
    },
    plusQuantity(item) {

      console.log(item)
      axios({
        method: 'put',
        url: '/api/updateQuantity',
        headers: {
          'Content-Type': 'application/json',
        },

        data: JSON.stringify({plan_supl_id: item.id, quantity: item.quantity + 1}),
      })
        .then(() => {
          this.getMyList();
          console.log("quantity수정완료 다시불러옴")
        })
    },
    minusQuantity(item) {
      console.log(item)
      if (item.quantity == 1) return;
      axios({
        method: 'put',
        url: '/api/updateQuantity',
        headers: {
          'Content-Type': 'application/json',
        },

        data: JSON.stringify({plan_supl_id: item.id, quantity: item.quantity - 1}),
      })
        .then(() => {
          this.getMyList();
          console.log("quantity수정완료 다시불러옴")
        })
    }
  }
}
</script>
<style scoped>
v-layout{
  border:1px solid;
}
del{
  text-decoration:line-through;
}
</style>

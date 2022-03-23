<template>
  <v-container>

    <h2>내준비물</h2>


    <form v-on:submit.prevent="inputList">
      <input type="text" v-model="inputItem" name="city" list="suplName">
      <datalist id="suplName">
        <option v-for="item in supplies" :key="item" :value="item"></option>
      </datalist>
      <button>입력</button>

    </form>

    <hr>

    <ol>
      <li v-for="(item, index) in todoList" :key="index">
        {{ item.todo }} {{ item.quantity }}개
        <v-btn v-on:click="makeDone(item.id,item.done)">완료</v-btn>
        <v-btn fab text small color="green" @click="plusQuantity(item)">+1</v-btn>
        <v-btn fab text small color="red" @click="minusQuantity(item)">-1</v-btn>
      </li>
    </ol>
    <hr>
    <ol>
      <li v-for="(item, index) in doneList" :key="index">
        <del>{{ item.todo }}</del>
        <v-btn v-on:click="makeDone(item.id,item.done)">취소</v-btn>
      </li>
    </ol>
    <v-btn v-on:click="allDelete">완료한 리스트 제거</v-btn>


  </v-container>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'

export default {
  name: 'SuppliesView',
  data() {
    return {
      inputItem: "",
      todoList: [],
      doneList: [],
      supplies: []
    }
  },
  mounted() {
    console.log("첫로딩데이터")
    this.getMyList()
    this.getList()
  },
  methods: {
    getList() {
      axios({
        method: 'get',
        url: '/api/getSupl',
        headers: {
          'Content-Type': 'application/json',
        },
        data: '',
      })
          .then((res) => {
            console.log("준비물테이블에서가져오기");
            console.log(res.data);
            var supplies = []

            res.data.forEach(function (i) {
              supplies.push(i.supl_name)
            })
            this.supplies = supplies;
          })

    },
    getMyList() {
      axios({
        method: 'get',
        url: '/api/getMySupl',
        headers: {
          'Content-Type': 'application/json',
        },
        data: '',
      })
          .then((res) => {
            console.log("내리스트가져오기");
            console.log(res.data);
            var todo = [];
            var done = [];

            res.data.forEach(function (i) {
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
      // if (this.inputItem !== "") {
      //   this.todoList.push({done: false, todo: this.inputItem});
      //   this.inputItem = "";
      // }
      var data2 = {
        plan_id: "1", supl_id: {
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
    allDelete() {
      var newDoneList = []
      this.doneList.forEach(function (i) {
        newDoneList.push({plan_supl_id: i.id})
      })
      console.log(newDoneList)
      axios({
        method: 'delete',
        url: '/api/delSupl',
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

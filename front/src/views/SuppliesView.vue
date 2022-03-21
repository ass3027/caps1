<template>
  <div class="home">
    <div id="app">
      <h2>내준비물</h2>
      <div class="container">
        <form v-on:submit.prevent="inputList">
          <input type="text" v-model="inputItem" name="city" list="suplName">
          <datalist  id="suplName">
            <option v-for="item in supplies" :key="item" :value="item"></option>
          </datalist>
          <button>입력</button>

        </form>
        <hr>
        <ol>
          <li v-for="(item, index) in todoList" :key="index">
            {{ item.todo }}
            <button v-on:click="deleteList(item.id)">완료</button>
          </li>
        </ol>
        <hr>
        <ol>
          <li v-for="(item, index) in doneList" :key="index">
            <del>{{ item.todo }}</del>
          </li>
        </ol>
        <button v-on:click="allDelete">All Delete</button>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'

export default {
  name: 'SuppliesView',
  data() {
    return {
      inputItem: "",
      todoList: [{done: false, todo: '테스트'}, {done: false, todo: '용'}],
      doneList: [],
      supplies:[]
    }
  },
  mounted() {
    console.log("hi")
    axios({
      method: 'get',
      url: '/api/getMySupl',
      headers: {
        'Content-Type': 'application/json',
      },
      data: '',
    })
        .then((res) => {
          console.log("첫로딩데이터");
          console.log(res.data);
          var todo = [];
          var done = [];

          res.data.forEach(function (i) {
            // console.log(i.supl_id.supl_name);
            // console.log(i.status)
            if (i.supl_id.supl_id == 0){
              i.supl_id.supl_name=i.name;
            }
            if (i.status == 0) {
              todo.push({id:i.plan_supl_id,done: false, todo: i.supl_id.supl_name})
            } else {
              done.push({id:i.plan_supl_id,done: true, todo: i.supl_id.supl_name})
            }
          })
          // console.log(todo)
          // console.log(done)
          this.todoList = todo;
          this.doneList = done;

        })
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
            // console.log(i.supl_id.supl_name);
            // console.log(i.status)
              supplies.push(i.supl_name)
          })
          // console.log(todo)
          // console.log(done)
          this.supplies = supplies;
        })
  },
  methods: {

    inputList(e) {
      e.preventDefault();
      // if (this.inputItem !== "") {
      //   this.todoList.push({done: false, todo: this.inputItem});
      //   this.inputItem = "";
      // }
      var data2 = {plan_id:"1",supl_id:{supl_id:"3"
          ,supl_name:this.inputItem}}
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
            window.location.href = "/supplies";
          })
    },
    deleteList(index) {
      this.todoList[index].done = "true";
      this.doneList.push(this.todoList[index]);
      this.todoList.splice(index, 1)
    },
    allDelete() {
      this.todoList = [];
      this.doneList = [];
    }
  }
}
</script>

<template>
  <div class="home">
    <div id="app">
      <h2>내준비물</h2>
      <div class="container">
        <form v-on:submit.prevent="inputList">
          <input type="text" v-model="inputItem">
          <button>입력</button>
        </form>
        <hr>
        <ol>
          <li  v-for="(item, index) in todoList" :key="index">
            {{item.todo}}
            <button v-on:click="deleteList(index)">완료</button>
          </li>
        </ol>
        <hr>
        <ol>
          <li v-for="item in doneList" :key="item">
            <del>{{item.todo}}</del>
          </li>
        </ol>
        <button v-on:click="allDelete">All Delete</button>
      </div>
    </div>  </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'

export default {
  name: 'SuppliesView',
  data(){
    return{
      inputItem:"",
      todoList:[{done: false, todo:'테스트'},{done: false, todo:'용'}],
      doneList:[],
    }
  },
  mounted() {
    console.log("hi")
    axios({
      method : 'get',
      url    : '/api/getSupl',
      headers: {
        'Content-Type': 'application/json',
      },
      data   : '',
    })
        .then((res) => {
          var a = [];
          res.data.forEach(function (i){
            console.log(i.supl_id.supl_name);
            console.log(this)

            a.push({done: false, todo:i.supl_id.supl_name})
          })
          console.log(this)
          console.log(a)
          this.todoList=a;

        })
  },
  methods:{

    inputList(e){
      e.preventDefault();
      if(this.inputItem !== ""){
        this.todoList.push({done: false, todo:this.inputItem});
        this.inputItem="";
      }
    },
    deleteList(index){
      this.todoList[index].done = "true";
      this.doneList.push(this.todoList[index]);
      this.todoList.splice(index,1)
    },
    allDelete(){
      this.todoList = [];
      this.doneList = [];
    }
  }
}
</script>

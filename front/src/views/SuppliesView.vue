<template>
  <div class="home">
    <div id="app">
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

export default {
  name: 'SuppliesView',
  data(){
    return{
      inputItem:"",
      todoList:[],
      doneList:[],
    }
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

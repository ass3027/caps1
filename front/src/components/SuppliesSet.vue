<template>
  <div>
    <h3>SETO</h3>
    <ol>
      <li v-for="(item, index) in todoList" :key="index">
        {{ item.todo }} {{ item.quantity }}개
        <v-btn v-on:click="makeDone(item.id,item.done)">완료</v-btn>
        <v-btn fab text small color="green" @click="plusQuantity(item)">+1</v-btn>
        <v-btn fab text small color="red" @click="minusQuantity(item)">-1</v-btn>
      </li>
    </ol>
    <hr>
    {{set}}

  </div>
</template>

<script>
// import axios from "axios";

export default {
  name: "SuppliesSet",
  data() {
    return {
      inputItem: "",
      todoList: [{todo:"test",quantity:"입니다"}],
      doneList: [],
      supplies: [],
      set:this.sets,
    }
  },
  props:['sets'],
  mounted() {
    console.log("sets "+this.sets)
    console.log("set "+this.set)

  },
  methods: {
    getList() {

    },
    getMyList() {

    },
    inputList(e) {
      e.preventDefault();
    },
    makeDone(id, done) {
      console.log(id,done)
    },
    allDelete() {
    },
    plusQuantity(item) {

      console.log(item)
    },
    minusQuantity(item) {
      console.log(item)
    }
  }
}
</script>

<style scoped>

</style>
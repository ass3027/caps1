<template>
  <v-container class="test">
    <h3>{{ sets[0].pl_name }} 관련 세트</h3>
    <hr>
    <ol>
      <li
        v-for="(item, index) in sets"
        :key="index"
      >
        <p>{{ item.supl_name }}</p>
        <!--        객체에 새로운 키값을 넣으면 갱신이 안됨 그래서 기존것을 바꿔야됨-->
        <div v-if="item.supl_id != 0">
          <v-btn
            fab
            text
            small
            color="green"
            @click="sendItem(item,index)"
          >
            가져오기
          </v-btn>
        </div>
        <div v-else>
          가져오기 완료
        </div>
      </li>
      <v-btn
        fab
        text
        small
        color="red"
        @click="handleToggle"
      >
        모두 가져오기
      </v-btn>
    </ol>

    <div
      v-show="toggle"
      class="outterMordal"
    >
      <div
        v-show="toggle"
        class="innerMordal"
      >
        <h5>확인창</h5>
        <p>모두 가져오시겠습니까?.</p>

        <v-btn @click="handleToggleWithYes">
          예
        </v-btn>
        <v-btn @click="handleToggle">
          아니오
        </v-btn>
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "SuppliesSet",
  props:['seto'],
  data() {
    return {
      inputItem: "",
      todoList: [{todo:"test",quantity:"입니다"}],
      doneList: [],
      supplies: [],
      toggle: false,
      sets: this.seto,
    }
  },
  mounted() {

  },
  methods: {
    sendItem(item) {
      axios.post("/api/sendItem",item).then(()=>{
      })
      var dummy = item["supl_id"];
      item["supl_id"] = 0;
      console.log(item)

    },
    sendList() {
      axios.post("/api/sendList",this.sets).then(()=>{
      })
    },
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
    handleToggle() {
      this.toggle = !this.toggle;
    },
    handleToggleWithYes() {
      this.sets.forEach((i)=>{
        i.supl_id=0;
      })
      this.sendList()
      this.toggle = !this.toggle;
    }

  }
}
</script>

<style scoped>
  .test{
    border: solid;
  }
  .outterMordal{
    background: rgba(0,0,0,.5);
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 1;

  }
  .innerMordal{
    position: fixed;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translateX(-50%);
    background: black;
    z-index: 2;
    padding: 15px;
    color: white;
  }
</style>

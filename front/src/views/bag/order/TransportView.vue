<template>
  <v-app id="app">
    
    <div class='Bag-order'>
      가방운송 신청서
    </div>
    
    <v-form v-model="valid">
      <v-container>
        <h3>짐 종류와 수량</h3>
        
        <div class="d-flex flex-column mb-6">
          <v-card
            v-for="(bagType,index) in bagType "
            :key="index"
            class="d-inline-flex p-2"
            outlined
            tile
          >{{ bagType.title }}
          </v-card>
        </div>
        
        <v-row
          justify="space-around">
          <v-col
            cols="12"
            md="12"
          >
            <v-text-field
              v-model="name"
              :rules="nameRules"
              :counter="10"
              label="이름"
              required
            ></v-text-field>
            
            <v-text-field
              v-model="startPoint"
              :rules="startRules"
              label="시작장소"
              required
            ></v-text-field>
            
            <v-text-field
              v-model="destination"
              :rules="destinationRules"
              label="도착장소"
              required
            ></v-text-field>
            
            <v-text-field
              v-model="checkBagTime"
              :rules="checkBagTime"
              label="맡길 시간"
              required
            ></v-text-field>
            
            <v-text-field
              v-model="pickUpTime"
              :rules="pickUpTime"
              label="찾을시간"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <div>
          <v-btn
            depressed
            color="primary"
            @click="addOrder"
          >
            작성 완료
          </v-btn>
        </div>
      </v-container>
    </v-form>
    <div id="mapcomponent"><MapComponent/></div>
    
    <router-view/>
  </v-app>

</template>

<script>

import MapComponent from "@/components/MapComponent";
import axios from "axios";
// import axios from "axios";
export default {
  components: {
    MapComponent
    
  },
  data() {
    return {
      overlay: false,
      valid: '',
      name: '',
      nameRules: [
        v => !!v || '이름을 입력해주세요.',
        v => v.length <= 10 || '이름은 11자 미만이어야 합니다.',
      ],
      
      startPoint: '',
      startRules: [],
      destination: '',
      destinationRules: [],
      checkBagTime: '',
      pickUpTime: '',
      bagType: [
        {title: '기내용 캐리어(57cm 미만) 10,000원'},
        {title: '화물용 캐리어(57cm 이상 67cm 미만) 15000원'},
        {title: '특대형 캐리어(67cm 이상 또는 20kg 이상) 20000원'},
        {title: '백팩 소형(40L 미만 그리고 10kg 미만) 10000원'},
        {title: '백팩 대형(40L 이상 또는 10kg 이상) 15000원'},
        {title: '기타물품 별도문의'}
      ],
    }
  },
  methods:{
    
    addOrder(){
      var bag = {
        ord_id:208,
        user_id:1,
        keep_start:100,
        keep_end:101
      }
      console.log(bag);
      axios
        .post('/api/addOrder',bag)
        .then((res)=>{
          console.log(res)
        })
    }
  },
  watch: {
    overlay(val) {
      val && setTimeout(() => {
        this.overlay = false
      }, 2000)
    },
  },
  
  
}

</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}
#mapcomponent{
  width: 1000px;
  height: 300px;
}
</style>
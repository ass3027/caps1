<template>
  <v-app id="app">
    <HelloWorld/>
    
    <div class='Bag-order'>
      가방운송 신청서
    </div>
    
    <v-form v-model="valid">
      <v-container>
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="name"
            :rules="nameRules"
            :counter="10"
            label="이름"
            required
          ></v-text-field>
        </v-col>
        
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="phoneNumber"
            :rules="phoneRules"
            label="휴대폰번호"
            required
          ></v-text-field>
        </v-col>
        
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="startPoint"
            :rules="startRules"
            label="시작장소"
            required
          ></v-text-field>
        </v-col>
        
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="destination"
            :rules="destinationRules"
            label="도착장소"
            required
          ></v-text-field>
        </v-col>
      </v-container>
    </v-form>
    
    <div>
      <v-btn
        depressed
        color="primary"
      >
        작성 완료
      </v-btn>
    </div>
    
    <router-view/>
  </v-app>

</template>

<script>
import HelloWorld from "@/components/HelloWorld.vue"

export default {
  components: {
    HelloWorld
  },
  data() {
    return {
      valid: '',
      name: '',
      nameRules: [
        v => !!v || '이름을 입력해주세요.',
        v => v.length <= 10 || '이름은 11자 미만이어야 합니다.',
      ],
      phoneNumber: '',
      phoneRules: [
        v => !!v || '휴대폰 번호를 입력해주세요',
        v => v.length <= 11 || '휴대폰번호는 12자 이하여야만 합니다.',
      ],
      startPoint: '',
      startRules: [],
      destination: '',
      destinationRules: [],
      
    }
  },
  // computed:{
  //   userId(){
  //
  //   }
  // }
  
}
</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}
</style>
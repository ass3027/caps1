<template>
  <v-container>
    <v-card style="width: 400px" align="center">
      <v-file-input
        v-model="file"
        placeholder="photo"
        style="width:300px"
        @change="previewFile(file)"
      />
      <img :src="preview" style="width: 200px">
      <v-text-field
        v-model="productName"
        type="text"
        placeholder="상품이름"
        label="상품이름"
        style="width:300px"
        outlined
      />
      <v-text-field
        v-model="productPrice"
        type="text"
        placeholder="상품가격"
        label="상품가격"
        style="width:300px"
        outlined
      />
      <v-text-field
        v-model="productInfo"
        type="text"
        placeholder="상품정보"
        label="상품정보"
        style="width:300px"
        outlined
      />

      <v-btn>
        수정
      </v-btn>
      <v-btn @click="save">
        저장
      </v-btn>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "productItem",
  data() {
    return {
      preview: '',
      productName: '',
      productPrice: '',
      productInfo: '',
      file: [],

    }
  },
  props: ['plId'],


  methods: {
    previewFile(file) {
      const fileData = (data) => {
        this.preview = data
        console.log('이미지주소', file)

      }
      const reader = new FileReader()
      reader.readAsDataURL(file)
      reader.addEventListener("load", function () {
        fileData(reader.result)
      }, false)
    },
    save() {
      var sendForm = new FormData();
      //userID
      sendForm.append('userId', this.$store.state.user.userId);
      //PlaceID
      sendForm.append('plId', this.plId);
      //이미지 파일
      sendForm.append('image', this.file);
      //상품 이름
      sendForm.append('pdName', this.productName);
      //상품 정보
      sendForm.append('pdInfo', this.productInfo);
      //상품 가격
      sendForm.append('pdPrice', this.productPrice);
      console.log('file', this.file)
      axios({
        method: 'POST',
        url: 'api/productAdd',
        data: sendForm,
        header: {
          'Context-Type': 'multipart/form-data'
        }
      }).then((res) => {
        console.log(res)
        alert('등록 성공');
      })
    }
  }
}
</script>

<style scoped>

</style>

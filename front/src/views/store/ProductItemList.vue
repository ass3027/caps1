<template>
  <v-container>
    <v-card style="width: 400px" align="center">
      <v-file-input
        v-model="file"
        placeholder="photo"
        style="width:300px"
        @change="previewFile(file)"
      />
      <v-img
        :src="product.pic_name"
      />
      <br>
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
      <div>
      </div>
      <v-btn @click="productEdit()">
        수정
      </v-btn>
      <v-btn @click="productDel()">
        삭제
      </v-btn>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "productItemList",
  data() {
    return {
      preview: '',
      productName: '',
      productPrice: '',
      productInfo: '',
      file: [],
    }
  },
  props: ['product'],

  created() {
    console.log(this.product)
    this.productName = this.product.pd_name
    this.productPrice = this.product.pd_price
    this.productInfo = this.product.pd_info
  },


  methods: {
    async productDel(){
      const { data } = await axios.delete('/api/productDelete', {
        params:{
          'pd_id': this.product.pd_id
        }
      })

      console.log(data)
      if (confirm("삭제하시겠습니까?") == true){
        alert('삭제되었습니다')
      }else{
        return false;
      }
      this.$router.push("/")
    },

    productEdit(){
      axios.post('/api/productEdit',{
        'pd_id': this.product.pd_id,
        'pd_name': this.productName,
        'pd_price': this.productPrice,
        'pd_info': this.productInfo
      })
        .then(() => {
          alert('수정완료')
        })
    },

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

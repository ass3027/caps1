<template>
  <div>
    <!--    <HelloWorld />-->
    <v-text-field
      v-model="store_id"
      type="text"
      placeholder="사업자번호"
    />

    <v-text-field
      v-model="user_id"
      type="text"
      placeholder="회원아이디"
    />

    <v-text-field
      v-model="pl_id"
      type="text"
      placeholder="장소번호"
    />

    <v-text-field
      v-model="store_name"
      type="text"
      placeholder="가게이름"
    />

    <v-text-field
      v-model="store_phone"
      type="text"
      placeholder="전화번호"
    />

    <v-text-field
      v-model="category"
      type="text"
      placeholder="카테고리"
    />

    <input
      ref="refImage"
      type="file"
      placeholder="photo"
      @change="imageSet()"
    >

    <div
      id="pictures"
      style="width:400px;height:400px"
    />

    <v-btn
      color="primary"
      @click="submit()"
    >
      submit
    </v-btn>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HotelAdd",
  components: {
    // HelloWorld,
  },

  data() {
    return {
      store_id: "",
      user_id: "",
      pl_id: "",
      store_phone: "",
      category: "",
      store_name: "",
      pic_name: "",
    }
  },
  methods: {
    imageSet: function () {
      var picture = document.getElementById("pictures")
      while (picture.hasChildNodes()) {
        picture.removeChild(picture.firstChild);
      }

      this.pic_name = this.$refs.refImage.files[0]
      var reader = new FileReader();
      reader.readAsDataURL(this.pic_name);

      reader.onload = function () {
        var photoFrame = document.createElement("div");
        photoFrame.style = `background : url(${reader.result}); background-size : cover;width:400px;height:400px;`;
        photoFrame.className = "photoFrame"
        document.getElementById("pictures").appendChild(photoFrame);
      }
    },
    submit: function () {
      var sendform = new FormData()

      sendform.append('store_id', this.store_id)
      sendform.append('user_id', this.user_id)
      sendform.append('pl_id', this.pl_id)
      sendform.append('store_name', this.store_name)
      sendform.append('store_phone', this.store_phone)
      sendform.append('pic_name', this.pic_name)
      sendform.append('category', this.category)

      console.log(sendform.get('store_id'))

      axios({
        method: 'post',
        url: 'api/hoteladd',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      })
        .then((res) => {
          if (res.data === true) {
            alert('등록 성공');
            this.$router.push('/hotel')
          } else {
            alert('등록 실패')
          }
        })
    }
  }
}
</script>

<style scoped>

</style>

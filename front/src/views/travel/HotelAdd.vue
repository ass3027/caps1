<template>
  <div>
    <HelloWorld />
    <input
      v-model="store_id"
      type="text"
      placeholder="사업자번호"
    >

    <input
      v-model="user_id"
      type="text"
      placeholder="회원아이디"
    >

    <input
      v-model="pl_id"
      type="text"
      placeholder="장소번호"
    >

    <input
      v-model="store_phone"
      type="text"
      placeholder="전화번호"
    >

    <input
      ref="refImage"
      type="file"
      placeholder="photo"
      @change="imageSet()"
    >

    <div id="pictures" />

    <button @click="submit()">
      submit
    </button>
  </div>
</template>

<script>
import axios from "axios";
import HelloWorld from "@/components/HelloWorld";

export default {
  name      : "HotelAdd",
  components: {
    HelloWorld,
  },

  data() {
    return {
      store_id   : "",
      user_id    : "1",
      pl_id      : "",
      store_phone: "",
      pic_name   : ""
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
        photoFrame.style = `background : url(${reader.result}); background-size : cover`;
        photoFrame.className = "photoFrame"
        document.getElementById("pictures").appendChild(photoFrame);
      }
    },
    submit  : function () {
      var sendform = new FormData()

      sendform.append('store_id', this.store_id)
      sendform.append('user_id', this.user_id)
      sendform.append('pl_id', this.pl_id)
      sendform.append('store_phone', this.store_phone)
      sendform.append('pic_name', this.pic_name)

      console.log(sendform.get('store_id'))

      axios({
        method : 'post',
        url    : 'api/hotelladd',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data   : sendform,
      })
        .then((res) => {
          if (res.data == 'ok') {
            alert("ok");
          }
        })
    }
  }
}
</script>

<style scoped>

</style>

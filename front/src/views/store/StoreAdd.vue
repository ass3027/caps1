<template>
  <div>

    <!--    <HelloWorld />-->

    <v-text-field
      v-model="pl_id"
      type="text"
      placeholder="장소번호"
    />

    <v-text-field
      v-model="store_id"
      type="text"
      placeholder="사업자번호"
    />

    <v-text-field
      v-model="user_id"
      type="text"
      placeholder="등록 유저"
    />

    <v-text-field
      v-model="addr1"
      type="text"
      placeholder="주소"
    />

    <v-text-field
      v-model="addr2"
      type="text"
      placeholder="상세 주소"
    />

    <v-text-field
      v-model="areacode"
      type="text"
      placeholder="지역코드"
    />

    <v-text-field
      v-model="cat3"
      type="text"
      placeholder="분류"
    />

    <v-container fluid>
      <v-row align="center">
        <v-col cols="6">
          <v-select
            v-model="select"
            :items="items"
            item-text="title"
            item-value="value"
            label="선택"
            return-object
          ></v-select>{{ select.value }}
        </v-col>
      </v-row>
    </v-container>



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
  name: "PlaceAdd",
  components: {
    // HelloWorld,
  },

  data() {
    return {
      pl_id : '',
      store_id: '',
      user_id: this.$store.state.user_id,
      addr1: '',
      addr2: '',
      areacode: '',
      cat1: '',
      cat2: '',
      cat3: '',
      contentid: '',
      contentypeid: '',
      pic_name: '',
      mapx: '',
      mapy: '',
      tel: '',
      sigungucode: '',
      title: '',
      zipcode:'',
      items: [
        {title: '호텔', value: 'B02010100'},
        {title: '모텔', value: 'B02010900'},
      ],
      select:'',
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

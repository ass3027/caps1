<template>
  <v-container>
    <v-card
      class="mx-auto"
    >
      <v-card-actions style="margin-bottom: 10px">
        <v-rating
          v-model="rev_rating"
          background-color="gray"
          color="yellow accent-4"
          dense
          half-increments
          size="30"
        />
      </v-card-actions>

      <v-row class="mx-auto">
        <v-col cols="9">
          <v-textarea placeholder="리뷰는 최대 1,000자까지 등록 가능합니다." />
        </v-col>
        <v-spacer />
        <v-col>
          <v-img
            v-if="alt_img_url = ''"
            src="@/image/logo.png"
            contain
            max-height="150px"
            max-width="150px"
            @click.prevent="imgClick"
          />
          <v-img
            v-else
            src="@/image/logo.png"
            contain
            max-height="150px"
            max-width="150px"
            @click.prevent="imgClick"
          />
        </v-col>
      </v-row>

      <v-card-actions>
        <v-spacer />
        <input
          id="image-input"
          type="file"
          @change="imageSet($event)"
        >
        <v-btn width="150px">
          리뷰 등록
        </v-btn>
      </v-card-actions>








      <!--        <input-->
      <!--          type="file"-->
      <!--          accept="image/*"-->
      <!--          @change="imageSet"-->
      <!--        >-->


      <!--    이미지출력 테스트-->
      <!--        <div-->
      <!--          v-if="url_string"-->
      <!--          class="upload-image"-->
      <!--          :style="`background-image:url(${url_string});`"-->
      <!--        />-->
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  name:'ReviewForm',
  props: {
    product: {
      type: Object
    }
  },
  data() {
    return {

      name          : "",
      rev_content   : "",
      rev_rating    : 5,
      image         : "",
      review_photo  : "",

      url_string    : "",

      alt_img_url   : "",
    }
  },
  methods: {

    imgClick() {
      let imageInput = document.getElementById("image-input");

      console.log("이미지 클릭")
      imageInput.click()
    },

    imageSet(e) {


      this.review_photo = e.target.files[0]
      console.log(this.review_photo)
      // this.url_string = URL.createObjectURL(e.target.files[0])
      // console.log(this.review_photo)

      // var reader = new FileReader();
      // reader.readAsDataURL(this.review_photo);
      //
      // reader.onload = function () {
      //   var photoFrame = document.createElement("div");
      //   photoFrame.style = `background : url(${reader.result}); background-size : cover;`;
      //   photoFrame.className = "photoFrame";
      //   document.getElementById("pictures").appendChild(photoFrame)
      // }


    },
    created(){
      console.log("이미지 url: " + this.alt_img_url)
    },
    onSubmit() {
      var sendForm = new FormData()



      sendForm.append('rev_content', this.rev_content)
      sendForm.append('rev_rating', this.rev_rating)
      sendForm.append('review_image', this.review_photo)

      // console.log('rev_content: ' + sendForm.get('rev_content'))
      // console.log('rating: ' + sendForm.get('rev_rating'))
      // console.log('review_image' + sendForm.get('review_image'))


      axios({
        method : 'post',
        url    : 'api/addReview',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data   : sendForm
      }).then((res) => {
        console.log(res.data())
        console.log('name: ' + this.name)
        this.name = ''
        console.log('init name: ' + this.name)
        this.rev_content = ""
        this.rev_rating = 5
        this.image = ""
        this.review_photo = ""
        this.url_string = ""

      })

    },
  }

}
</script>

<style scoped>
#image-input {
  visibility: hidden;
}

</style>


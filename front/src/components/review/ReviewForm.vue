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
        <v-col cols="12">
          <v-textarea v-model="rev_content" placeholder="리뷰는 최대 1,000자까지 등록 가능합니다." />
        </v-col>
        <v-spacer />
        <v-col>

          <input ref="refImage"
                 type="file"
                 placeholder="photo"
                 @change="imageSet($event)"
          >

          <div
            id="pictures"
            style="width: 150px; height: 150px"
          />
        </v-col>
      </v-row>

      <v-card-actions>
        <v-spacer />
        <v-btn width="150px" @click="onSubmit">
          리뷰 등록
        </v-btn>
      </v-card-actions>
    </v-card>
    <input v-model="rev_photo" >
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
      img_test: `/api/photo/`+"userImage/1648100757821img.jpg",

      name          : "",
      rev_content   : "",
      rev_rating    : 5,
      image         : "",
      rev_photo  : "",

      url_string    : "",

      alt_img_url   : "",
    }
  },
  methods: {

    imageSet(e) {


      // this.rev_photo = e.target.files[0]
      // console.log(this.rev_photo)

      var picture = document.getElementById('pictures')



      while(picture.hasChildNodes()) {
        picture.removeChild(picture.firstChild);
      }

      this.rev_photo = this.$refs.refImage.files[0];

      var reader = new FileReader();
      reader.readAsDataURL(this.rev_photo);

      reader.onload = function () {
        var photoFrame = document.createElement('div');
        photoFrame.style = `background : url(${reader.result}); background-size : cover;width:400px;height:400px;`;
        photoFrame.className = 'photoFrame';
        document.getElementById('pictures').appendChild(photoFrame);
      }

      // this.url_string = URL.createObjectURL(e.target.files[0])
      // console.log(this.rev_photo)

      // var reader = new FileReader();
      // reader.readAsDataURL(this.rev_photo);
      //
      // reader.onload = function () {
      //   var photoFrame = document.createElement("div");
      //   photoFrame.style = `background : url(${reader.result}); background-size : cover;`;
      //   photoFrame.className = "photoFrame";
      //   document.getElementById("pictures").appendChild(photoFrame)
      // }


    },
    created(){
    },
    onSubmit() {
      var sendForm = new FormData()

      console.log("리뷰 사진")
      console.log(this.rev_photo)



      sendForm.append('rev_content', this.rev_content)
      sendForm.append('rev_rating', this.rev_rating)
      sendForm.append('rev_photo', this.rev_photo)


      // console.log('rev_content: ' + srnsendForm.get('rev_content'))
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
        this.rev_photo = ""
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


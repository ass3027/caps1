<template>
  <!--  <v-container>-->
  <!--    <h1>{{ book_id }}</h1>-->
  <!--    <v-card-->

  <!--    >-->
  <!--      <v-card-actions style="margin-bottom: 10px">-->
  <!--        <v-rating-->
  <!--          v-model="rev_rating"-->
  <!--          background-color="gray"-->
  <!--          color="yellow accent-4"-->
  <!--          dense-->
  <!--          half-increments-->
  <!--          size="30"-->
  <!--        />-->
  <!--      </v-card-actions>-->

  <!--      <v-row class="mx-auto">-->
  <!--        <v-col cols="12">-->
  <!--          <v-textarea-->
  <!--            v-model="rev_content"-->
  <!--            placeholder="리뷰는 최대 1,000자까지 등록 가능합니다."-->
  <!--          />-->
  <!--        </v-col>-->
  <!--        <v-spacer />-->
  <!--        <v-col>-->
  <!--          <input-->
  <!--            ref="refImage"-->
  <!--            type="file"-->
  <!--            placeholder="photo"-->
  <!--            @change="imageSet($event)"-->
  <!--          >-->

  <!--          <div-->
  <!--            id="pictures"-->
  <!--            style="width: 150px; height: 150px"-->
  <!--          />-->
  <!--        </v-col>-->
  <!--      </v-row>-->

  <!--      <v-card-actions>-->
  <!--        <v-spacer />-->
  <!--        <v-btn-->
  <!--          width="150px"-->
  <!--          @click="onSubmit"-->
  <!--        >-->
  <!--          리뷰 등록-->
  <!--        </v-btn>-->
  <!--      </v-card-actions>-->
  <!--    </v-card>-->
  <!--    <input v-model="rev_photo">-->
  <!--  </v-container>-->

  <div>
    <div>
      <h2>후기 작성</h2>
    </div>
    <form
      action=""
      enctype="multipart/form-data"
    >
      <input
        type="hidden"
        name="mode"
      >
      <input
        type="hidden"
        name="goodsno"
      >
      <input
        type="hidden"
        name="ordno"
      >
      <input
        type="hidden"
        name="package_goodsno"
      >
      <input
        type="hidden"
        name="sno"
      >
      <input
        type="hidden"
        name="referer"
      >
      <input
        type="hidden"
        name="name"
      >

      <div class="write_board">
        <div style="padding: 20px 15px; border-top: 2px solid #5f0080; ">
          <div style="float: left; width: 80px; margin-right: 25px; ">
            <img
              style="width: 80px"
              src="http://img-cf.kurly.com/shop/data/goods/1622609196762s0.jpg"
            >
          </div>
          <div
            class="desc"
            style="vertical-align: middle; line-height: 1; display: table; height: 104px"
          >
            <div
              class="inner_desc"
              style="display: table-cell; vertical-align: middle; height: 104px;"
            >
              <div style="height: 28px">
                [ardo] 냉동 완두콩 400g
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name:'ReviewForm',
  props: {
    book_id: {
      type: String
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
        photoFrame.style = `background : url(${reader.result}); background-size : cover;width:150px;height:150px;`;
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
      console.log("!!!!!!!!:"+this.book_id)
    },
    onSubmit() {
      var sendForm = new FormData()

      console.log("리뷰 사진")
      console.log(this.rev_photo)

      console.log("예약 아이디 "+ this.book_id)



      sendForm.append('rev_content', this.rev_content)
      sendForm.append('rev_rating', this.rev_rating)
      sendForm.append('rev_photo', this.rev_photo)
      sendForm.append('book_id', ""+this.book_id)


      // console.log('rev_content: ' + srnsendForm.get('rev_content'))
      // console.log('rating: ' + sendForm.get('rev_rating'))
      // console.log('review_image' + sendForm.get('review_image'))

      axios({
        method : 'post',
        url    : 'http://localhost:8080/api/addReview',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data   : sendForm
      }).then((res) => {
        console.log(res.data)
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


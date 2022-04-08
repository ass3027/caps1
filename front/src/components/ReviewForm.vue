<template>
  <form @submit.prevent="onSubmit">
    <h3>Leave a review</h3>

    <label for="name">Name:</label>

    <!--    회원 값을 가져와야함-->
    <input
      id="name"
      v-model="name"
    >

    <label for="review">Review:</label>
    <textarea
      id="review"
      v-model="rev_content"
    />

    <label for="rating">Rating:</label>
    <select
      id="rating"
      v-model.number="rev_rating"
      @change="changeRating(this)"
    >
      <option>5</option>
      <option>4</option>
      <option>3</option>
      <option>2</option>
      <option>1</option>
    </select>


    <input
      type="file"
      accept="image/*"
      @change="imageSet"
    >
    <div id="pictures">
      Image:
    </div>

    <!--    이미지출력 테스트-->
    <div
      v-if="url_string"
      class="upload-image"
      :style="`background-image:url(${url_string});`"
    />
    <input
      class="button"
      type="submit"
      value="Submit"
    >
  </form>
</template>

<script>
import axios from 'axios';

export default {
  name:'ReviewForm',
  data() {
    return {
      name          : "",
      rev_content   : "",
      rev_rating    : 5,
      image         : "",
      review_photo  : "",

      url_string    : "",
    }
  },
  methods: {

    imageSet(e) {
      e.preventDefault();

      this.review_photo = e.target.files[0]
      // this.url_string = URL.createObjectURL(e.target.files[0])

      console.log(this.review_photo)

      var reader = new FileReader();
      reader.readAsDataURL(this.review_photo);

      reader.onload = function () {
        var photoFrame = document.createElement("div");
        photoFrame.style = `background : url(${reader.result}); background-size : cover;`;
        photoFrame.className = "photoFrame";
        document.getElementById("pictures").appendChild(photoFrame)
      }


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
#review {
  border: 1px solid;
}

#name {
  border: 1px solid;
}

#rating {
  border: 1px solid;
}

.upload-image {
  background-size: cover;
}

</style>


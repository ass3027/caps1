<template>
  <div style="max-width: 1050px; margin: 0 auto; padding-top: 60px">
    <form
      id="form_review"
      name="form_review"
      style="width: 100%"
    >
      <h2 style="padding: 5px 0 34px; border-bottom: 2px solid black ">
        후기 작성
      </h2>
      <div class="write_board">
        <table
          class="tbl"
          style="border-bottom: 2px solid black"
        >
          <caption style="display:none">
            후기 작성 입력상자
          </caption>
          <colgroup>
            <col style="width:110px;">
            <col style="width:auto">
          </colgroup>
          <tbody>
            <tr>
              <th style="padding: 16px 0">
                제목
              </th>
              <td>
                <input
                  v-model="title"
                  type="text"
                  name="subject"
                  placeholder="제목을 입력해주세요."
                  class="inp "
                >
              </td>
            </tr>
            <tr>
              <th>후기작성</th>
              <td>
                <div class="field_cmt">
                  <textarea
                    id="fieldCmt"
                    v-model="rev_content"
                    name="contents"
                    cols="100"
                    rows="10"
                    placeholder="리뷰 내용을 입력해주세요."
                  />
                </div>
              </td>
            </tr>

            <tr>
              <th>사진</th>
              <td>
                <div class="field_cmt">
                  <input
                    ref="refImage"
                    type="file"
                    placeholder="photo"
                    @change="imageSet($event)"
                  >
                </div>

                <div
                  id="pictures"
                  style="width: 150px; height: 150px"
                />
              </td>
            </tr>
          </tbody>
        </table>


        <button
          id="btnSubmit"
          type="button"
          class="btn_reg btn_disabled"
          style="margin-top: 20px"
          @click="onSubmit"
        >
          등록하기
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name:'ReviewForm',
  props: {
    type: String,
    id: String,
  },
  data() {
    return {
      name          : "",
      rev_content   : "",
      image         : "",
      rev_photo  : "",
      title: '',
      url_string    : "",

      alt_img_url   : "",
    }
  },
  computed: {

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


    },
    onSubmit() {
      var review = new FormData()

      review.append('title', this.title)
      review.append('rev_content', this.rev_content)
      review.append('rev_photo', this.rev_photo)


      axios({
        method : 'post',
        url    : '/api/addReview/' + this.type + '/' + this.id,
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data   : review
      }).then((res) => {

        alert(res.data)

      })

    },
  }

}
</script>

<style scoped>
#image-input {
  visibility: hidden;
}

.field_cmt{
  padding: 8px 10px 9px;
  border: 1px solid #dddfe1;
}

th {
  border-top: 1px solid #dddfe1;
  /*background-color: #f7f7f7;*/
  font-size: 12px;
  color: #666;
}
td {
  padding: 10px 0 10px 10px;
  border-top: 1px solid #dddfe1;
}
input {
  width: 100%;
}
.inp {
  width: 100%;
  height: 34px;
  padding: 0 10px;
  border: 1px solid #dddfe1;
  font-size: 12px;
  color: #000;
  line-height: 18px;
  outline: none;
}

/*textarea {*/
/*  overflow: hidden;*/
/*  width: 100%;*/
/*  min-height: 202px;*/
/*  border: 0;*/
/*  resize: none;*/
/*  font-size: 12px;*/
/*  color: #000;*/
/*  line-height: 18px;*/
/*  outline: none;*/
/*  margin: 0;*/
/*}*/
.btn_disabled {
  display: block;
  width: 200px;
  height: 48px;
  margin: 0 auto;
  border: 1px solid black;
  background-color: white;
  font-size: 14px;
  color: black;
  line-height: 46px;
}
textarea {
  width: 947px;
  font-size: 12px;
}

</style>


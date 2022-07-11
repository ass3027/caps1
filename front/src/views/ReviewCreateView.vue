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
  name:'ReviewCreateView',
  props: {
    type: String,
    id: String,
  },
  data() {
    return {
      rev_content: '',
      title: '',
      pay_id: '',
      imageFile: null,
    }
  },
  computed: {

  },
  methods: {

    imageSet(e) {


      var picture = document.getElementById('pictures')



      while(picture.hasChildNodes()) {
        picture.removeChild(picture.firstChild);
      }

      this.imageFile = this.$refs.refImage.files[0];

      var reader = new FileReader();
      reader.readAsDataURL(this.imageFile);

      reader.onload = function () {
        var photoFrame = document.createElement('div');
        photoFrame.style = `background : url(${reader.result}); background-size : cover;width:150px;height:150px;`;
        photoFrame.className = 'photoFrame';
        document.getElementById('pictures').appendChild(photoFrame);
      }


    },
    onSubmit() {
      this.imageFile = this.$refs.refImage.files[0];
      console.log('imageFile', this.imageFile)
      var sendform = new FormData()

      sendform.append('title', this.title)
      sendform.append('rev_content', this.rev_content)
      sendform.append('pay_id', this.id)
      sendform.append('imageFile', this.imageFile)


      axios({
        method : 'post',
        url    : '/api/addReview',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform
      }).then((res) => {

        alert(res.data)

      })

    },
  }

}
</script>

<style scoped>

.field_cmt{
  padding: 8px 10px 9px;
  border: 1px solid #dddfe1;
}

textarea {
  width: 947px;
  font-size: 12px;
}

th {
  border-top: 1px solid #dddfe1;
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

</style>


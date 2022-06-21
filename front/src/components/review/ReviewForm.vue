<template>
    <div style="max-width: 1050px; margin: 0 auto; padding-top: 60px">

<!--      <v-card>-->
<!--        <v-card-actions style="margin-bottom: 10px">-->
<!--          <v-rating-->
<!--            v-model="rev_rating"-->
<!--            background-color="gray"-->
<!--            color="yellow accent-4"-->
<!--            dense-->
<!--            half-increments-->
<!--            size="30"-->
<!--          />-->
<!--        </v-card-actions>-->

<!--        <v-row class="mx-auto">-->
<!--          <v-col cols="12">-->
<!--            <v-textarea-->
<!--              v-model="rev_content"-->
<!--              placeholder="리뷰는 최대 1,000자까지 등록 가능합니다."-->
<!--            />-->
<!--          </v-col>-->
<!--          <v-spacer />-->
<!--          <v-col>-->
<!--            <input-->
<!--              ref="refImage"-->
<!--              type="file"-->
<!--              placeholder="photo"-->
<!--              @change="imageSet($event)"-->
<!--            >-->

<!--            <div-->
<!--              id="pictures"-->
<!--              style="width: 150px; height: 150px"-->
<!--            />-->
<!--          </v-col>-->
<!--        </v-row>-->

<!--        <v-card-actions>-->
<!--          <v-spacer />-->
<!--          <v-btn-->
<!--            width="150px"-->
<!--            @click="onSubmit"-->
<!--          >-->
<!--            리뷰 등록-->
<!--          </v-btn>-->
<!--        </v-card-actions>-->
<!--      </v-card>-->
<!--      <input v-model="rev_photo">-->


      <form name="form_review" id="form_review" style="width: 100%">
        <h2 style="padding: 5px 0 34px; border-bottom: 2px solid black ">후기 작성</h2>
        <div class="write_board">
          <table class="tbl" style="border-bottom: 2px solid black">
            <caption style="display:none">후기 작성 입력상자</caption>
            <colgroup>
              <col style="width:110px;">
              <col style="width:auto">
            </colgroup>
            <tbody>
            <tr>
              <th style="padding: 16px 0">제목</th>
              <td>
                <input type="text" name="subject" placeholder="제목을 입력해주세요." class="inp " v-model="title">
              </td>
            </tr>
            <tr>
              <th>후기작성</th>
              <td>
                <div class="field_cmt">
                  <textarea id="fieldCmt" name="contents" cols="100" rows="10" placeholder="리뷰 내용을 입력해주세요." v-model="rev_content"></textarea>

                </div>
              </td>
            </tr>
            </tbody>
          </table>
          <button type="button" id="btnSubmit" class="btn_reg btn_disabled" style="margin-top: 20px" @click="onSubmit">등록하기</button>
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
      img_test: `/api/photo/`+"userImage/1648100757821img.jpg",

      name          : "",
      rev_content   : "",
      rev_rating    : 5,
      image         : "",
      rev_photo  : "",
      title: '',
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


    },
    onSubmit() {
      var sendForm = new FormData()

      console.log("리뷰 사진")
      console.log('test', this.rev_content, this.title, this.type, this.id)


      var type = this.type

      if(type === 'product'){
        sendForm.append('pd_id', this.id)
      }else if(type === 'place'){
        sendForm.append('pl_id', this.id)
      }else if(type === 'guide'){
        sendForm.append('guide_id', this.id)
      }else if(type === 'gitem'){
        sendForm.append('gitem_id', this.id)
      }

      sendForm.append('title', this.title)
      sendForm.append('rev_content', this.rev_content)
      sendForm.append('user_id', this.$store.state.user.userId)




      axios({
        method : 'post',
        url    : 'http://localhost:8080/api/addReview',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data   : sendForm
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


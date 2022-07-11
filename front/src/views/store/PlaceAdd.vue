<template>
  <v-container>
    <div>
      <h1>등록유저: {{ user_id }}</h1>

      <v-text-field
        v-model="title"
        type="text"
        placeholder="시설 이름"
      />

      <v-text-field
        v-model="store_id"
        type="text"
        placeholder="사업자번호"
      />

      <v-text-field
        v-model="tel"
        type="text"
        placeholder="시설 전화번호"
      />

      <address-component @addressData="addressDate"/>


      <h2> 우편번호: {{ this.addr1.sPostcode }}</h2>
      <h2> 주소: {{ this.addr1.sAddress }} </h2>


      <v-container fluid>
        <div>
          <v-select
            v-model="searchCon"
            :style="{width:'150px',marginLeft:'10px'}"
            label="지역"
            :items="areacode"
            item-text="text"
            :value="areacode"
          />
        </div>
        {{ searchCon }}
        <v-row align="center">
          <v-col cols="6">
            <v-select
              v-model="select"
              :style="{width:'150px',marginLeft:'10px'}"
              label="숙박시설 유형선택"
              item-text="title"
              :items="items"
              item-value="value"
              return-object
            />
          </v-col>
        </v-row>
      </v-container>

      <v-file-input
       v-model="file"
        placeholder="photo"
      />

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
  </v-container>
</template>

<script>
import axios from "axios";
import AddressComponent from "@/components/AddressComponent";

export default {
  name: "PlaceAdd",
  components: {
    AddressComponent
    // HelloWorld,
  },

  data() {
    return {
      file:[],
      pl_id: '',
      store_id: '123412341',
      addr1: '',
      addr2: '',
      cat1: '',
      pic_name: '',
      mapx: '',
      mapy: '',
      tel: '0108888888',
      title: '그랜드호텔',
      zipcode: '',
      items: [
        {title: '호텔', value: 'B02010100'},
        {title: '모텔', value: 'B02010900'},
      ],
      select: 'B02010100',
      searchCon: '4',
      areacode: [
        {text: '서울', value: '1'},
        {text: '인천', value: '2'},
        {text: '대전', value: '3'},
        {text: '대구', value: '4'},
        {text: '광주', value: '5'},
        {text: '부산', value: '6'},
        {text: '울산', value: '7'},
        {text: '세종특별자치시', value: '8'},
        {text: '경기도', value: '31'},
        {text: '강원도', value: '32'},
        {text: '충청북도', value: '33'},
        {text: '충청남도', value: '34'},
        {text: '경상북도', value: '35'},
        {text: '경상남도', value: '36'},
        {text: '전라북도', value: '37'},
        {text: '전라남도', value: '38'},
        {text: '제주도', value: '39'},
      ],
    }
  },
  methods: {
    //우편번호,주소,상세주소
    addressDate: function (addData) {
      console.log(addData)
      this.addr1 = addData
    },

    // imageSet: function () {
    //   var picture = document.getElementById("pictures")
    //   while (picture.hasChildNodes()) {
    //     picture.removeChild(picture.firstChild);
    //   }
    //
    //   this.pic_name = this.$refs.refImage.files[0]
    //   console.log(this.pic_name)
    //   var reader = new FileReader();
    //   reader.readAsDataURL(this.pic_name);
    //
    //   reader.onload = function () {
    //     var photoFrame = document.createElement("div");
    //     photoFrame.style = `background : url(${reader.result}); background-size : cover;width:400px;height:400px;`;
    //     photoFrame.className = "photoFrame"
    //     document.getElementById("pictures").appendChild(photoFrame);
    //   }
    // },
    submit() {
      var sendForm = new FormData();
      //사업자 번호
      sendForm.append('store_id', this.store_id);
      //유저 아이디
      sendForm.append('user_id', this.$store.state.user.userId);
      //주소 정보
      sendForm.append('addr1', this.addr1.sAddress);
      //지역 코드
      sendForm.append('areacode', this.searchCon);

      //전화번호
      sendForm.append('tel', this.tel);
      //제목
      sendForm.append('title', this.title);
      //우편번호
      sendForm.append('zipcode', this.addr1.sPostcode);
      //이미지 파일
      sendForm.append('image', this.file);

      axios({
        method: 'POST',
        url: 'api/placeAdd',
        data: sendForm,
        header: {
          'Context-Type': 'multipart/form-data'
        }
      }).then((res) => {
        console.log(res)
        alert('등록 성공');
      })
    }
  }
}
</script>

<style scoped>

</style>

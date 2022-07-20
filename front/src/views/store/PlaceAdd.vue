<template>
  <v-container style="padding-top: 65px; width: 1050px; ">
    <div
      class="Bag-order"
      style="width: 80%; margin: 0 auto; border-bottom: 2px solid black"
    >
      <h2
        class="tit"
        style="padding-bottom: 34px"
        align="center"
      >
        장소등록
      </h2>
    </div>
    <div align="center" style="margin-top: 30px">
      <h3>{{ $store.state.user.userId }}님은 장소를 등록해주세요.</h3>

      <div style="margin-top: 30px">
        <v-text-field
          v-model="title"
          type="text"
          placeholder="시설 이름"
          label="시설이름"
          style="width:300px"
          outlined
        />

        <v-text-field
          v-model="store_id"
          type="text"
          placeholder="사업자번호"
          label="사업자번호"
          style="width:300px"
          outlined
        />

        <v-text-field
          v-model="tel"
          type="text"
          placeholder="시설 전화번호"
          label="시설 전화번호"
          style="width:300px"
          outlined
        />
      </div>

      <v-row align="center">
        <v-col style="position: relative; left: 160px;">
          <h3>주 소 검 색 : </h3>
        </v-col>
        <v-col>
          <address-component
            @addressData="addressData"
            style="left:10px"
          />
        </v-col>
      </v-row>

      <v-text-field
        label="우편번호"
        type="text"
        v-model="this.addr1.sPostcode"
        style="width:300px; margin-top: 30px"
        outlined
        readonly>
      </v-text-field>


      <v-text-field
        label="주소"
        type="text"
        v-model="this.addr1.sAddress"
        style="width:300px"
        outlined
        readonly>
      </v-text-field>

      <v-select
        v-model="searchCon"
        style="width:300px"
        label="지역"
        :items="areacode"
        item-text="text"
        :value="areacode"
      />


      <v-select
        v-model="select"
        style="width:300px"
        label="숙박시설 유형선택"
        item-text="title"
        :items="items"
        item-value="value"
        return-object
      />

      <v-file-input
        v-model="file"
        placeholder="photo"
        style="width:300px"
      />

      <v-btn
        color="primary"
        @click="submit()"
      >
        장소등록완료
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
      file: [],
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
    addressData: function (addData) {
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

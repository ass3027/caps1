<template>
  <v-container>
    <div class="address">
      <v-col>
        <v-row>
          <v-btn @click="execDaumPostcode()">우편번호 찾기</v-btn>
        </v-row>
      </v-col>
    </div>
  </v-container>
</template>

<script>

export default {
  props: {},
  data() {
    return {
      postcode: "42117",
      address: "대구 수성구 동대구로 305 (그랜드관광호텔)",
      extraAddress: "",
      detailAddress: "",
    };
  },
  mounted() {

  },
  methods: {


    async execDaumPostcode() {
      await new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.

          this.$emit('addressData', {
            sAddress: this.address,
            sPostcode: this.postcode,
          });

        },
      }).open();

    },

  },
};
</script>

<template>
  <v-container>
    <v-row justify="center" style="text-align: center">
      <v-col cols="7">
        <h1>{{tour.title}}</h1>
        <p>{{tour.addr1}}</p>
        <div>
          <span style="float:left">
            <v-icon
              color="red"
              @click="editStart(task,i)"
            >
              mdi-heart-plus
            </v-icon>
            찜하기
          </span>
          <span style="float:right">
            <v-icon
              color="orange"
              @click="editStart(task,i)"
            >
              mdi-calendar-check-outline
            </v-icon>
            일정에 추가하기

          </span>

        </div>


        <v-carousel>
          <v-carousel-item
            v-for="(image,i) in images"
            :key="i"
            :src="image.originimgurl"
            reverse-transition="fade-transition"
            transition="fade-transition"
          ></v-carousel-item>
        </v-carousel>

        <br>
        <h3>상세정보</h3>
        <v-divider></v-divider>
        <br>
        <p v-html="tour.overview"></p>
        <KakaoMap></KakaoMap>

        <v-row style="text-align: left">
          <v-col cols="6">
            <ul>
              <li>
                <strong>
                  문의 및 안내
                </strong>
                <span>
                  {{ details.infocenter }}
                </span>
              </li>
              <li>
                <strong>
                  주소
                </strong>
                <span>
                  {{tour.addr1}}
                </span>
              </li>
              <li>
                <strong>
                  휴일
                </strong>
                <span>
                  {{ details.restdate }}
                </span>
              </li>
              <li>
                <strong>
                  입장료
                </strong>
                <span>

                </span>
              </li>
            </ul>
          </v-col>
          <v-col cols="6">
            <ul>
              <li>
                <strong>
                  홈페이지
                </strong>
                <span v-html="tour.homepage">
                </span>

              </li>
              <li>
                <strong>
                  이용시간
                </strong>
                <span>
                  {{ details.usetime }}
                </span>
              </li>
              <li>
                <strong>
                  주차
                </strong>
                <span>
                  {{ details.parking }}
                </span>
              </li>
              <li>
                <strong>
                  시설이용료
                </strong>
                <span>

                </span>

              </li>
            </ul>

          </v-col>
        </v-row>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import KakaoMap from "@/components/KakaoMap";

export default {

  name: 'TourDetailView',
  components: {
    KakaoMap
  },
  props:['pl_id','contentTypeId'],
  data() {
    return {
      tour:'',
      images:[],
      details:''
    }
  },
  async mounted() {
    const mainData = await axios.get(`http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=5rTZpUCbTNQQs2rG3%2FXoLvSO%2FDTYLSBp8OgERTKIgFOKwh5LHirGiqkQ%2Begr9tI6qHEkQJWFY2wHcA36h6DU6A%3D%3D&contentId=${this.pl_id}&defaultYN=Y&addrinfoYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest&firstImageYN=Y&mapinfoYN=Y&_type=json`)
    this.tour=mainData.data.response.body.items.item

    const imageData = await axios.get(`http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=5rTZpUCbTNQQs2rG3%2FXoLvSO%2FDTYLSBp8OgERTKIgFOKwh5LHirGiqkQ%2Begr9tI6qHEkQJWFY2wHcA36h6DU6A%3D%3D&contentId=${this.pl_id}&imageYN=Y&MobileOS=ETC&MobileApp=AppTest&subImageYN=Y`)
    this.images=imageData.data.response.body.items.item
    this.images.unshift({originimgurl:this.tour.firstimage}) //unshift 배열 앞에 새로운값넣기

    const detailData = await axios.get(`http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=5rTZpUCbTNQQs2rG3%2FXoLvSO%2FDTYLSBp8OgERTKIgFOKwh5LHirGiqkQ%2Begr9tI6qHEkQJWFY2wHcA36h6DU6A%3D%3D&contentId=${this.pl_id}&contentTypeId=${this.contentTypeId}&MobileOS=ETC&MobileApp=AppTest`)
    this.details=detailData.data.response.body.items.item

    // alert(this.contentTypeId)
  },
  methods: {
  },

}

</script>

<style scoped>
</style>

<template>
  <div class="left-layout">
    <v-card class="card-size">
      <div>
        <div>날짜</div>
        <div>
          <div class="text-center">
            <v-dialog
              v-model="IsDataForm"
              width="50%"
            >
              <template #activator="{ on, attrs }">
                <v-btn
                  color="red lighten-2"
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                  {{ settingstart }} ~ {{ settingend }}
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  예약 날짜를 입력하세요.
                </v-card-title>
                <v-card-text>
                  <div class="modal-data">
                    <v-date-picker
                      v-model="startDate"
                      :min="disabletoday"
                    />
                    ~
                    <v-date-picker
                      v-model="endDate"
                      :min="disabletoday"
                    />
                  </div>
                </v-card-text>

                <v-divider />

                <v-card-actions>
                  <v-spacer />
                  <v-btn
                    color="primary"
                    text
                    @click="SetDataForm()"
                  >
                    적용
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </div>
      </div>
      <div>
        <div>상세조건</div>
        <div>
          <v-btn>초기화</v-btn>
          <v-btn @click="setting()">
            적용
          </v-btn>
        </div>
      </div>
      <div>
        {{ $store.state.asdf }}
        <div>호텔유형</div>
        <div>
          <v-checkbox
            v-for="(ratingItem, aIdx) in rating"
            :key="aIdx"
            v-model="ratingItem.type"
            hide-details
            :label="`${ratingItem.name}`"
            class="margin-0"
          />
        </div>
      </div>
      <div>
        <div>인원</div>
        <div class="flex itemcenter">
          <v-btn
            text
            icon
            @click="minusPeople()"
          >
            <v-icon>mdi-minus</v-icon>
          </v-btn>
          <div>{{ peopleCount }}</div>
          <v-btn
            text
            icon
            @click="plusPeople()"
          >
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </div>
      </div>
    </v-card>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'TravelLeftbar',
  data() {
    return {
      startDate: new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10),
      endDate: new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10),
      settingstart: '',
      settingend: '',
      IsDataForm: false,
      disabletoday: new Date().toISOString().substr(0, 10),
      rating: [
        { name: 'star5', type: false },
        { name: 'star4', type: false },
        { name: 'star3', type: false }
      ],
      peopleCount: 1
    };
  },
  computed: {
    test() {
      return 'aa';
    }
  },
  mounted() {
    console.log("hi")

  },
  created() {
    this.settingstart = this.startDate;
    this.settingend = this.endDate;
  },
  methods: {
    SetDataForm() {
      if (this.startDate <= this.endDate) {
        this.settingstart = this.startDate;
        this.settingend = this.endDate;
        this.IsDataForm = !this.IsDataForm;
      } else {
        alert('날짜를 확인하세요');
      }
    },
    plusPeople() {
      this.peopleCount = this.peopleCount + 1;
    },
    minusPeople() {
      if (this.peopleCount > 1) {
        this.peopleCount = this.peopleCount - 1;
      }
    },
    setting() {
      let ratingList = [];
      this.rating.forEach(aItem => {
        if (aItem.type) {
          ratingList.push(aItem.name);
        }
      });

      // let postData = {
      //     date: { start: this.settingstart, end: this.settingend },
      //     ratings: ratingList,
      //     people: this.peopleCount
      // };
      axios({
        method : 'get',
        url    : '/api/getHotel',
        headers: {
          'Content-Type': 'application/json'
        },
        data   : '',
      })
        .then((res) => {
          console.log(res);

          console.log(res.data);
          let a = {
            hotelInfos:
                res.data
          }
          this.$store.state.test=a;
        })
      // let a = {
      //   hotelInfos: []
      // };
      //             axios.get('test').then((res)=>{
      // let a = res.data ;
      //             })

      // this.$store.state.test = a;
    }
  }
};
</script>
<style scoped>
.left-layout {
  width: 22%;
  display: flex;
  justify-content: center;
  margin-top: 2%;
}
.card-size {
  width: 90%;
  height: 80%;
  padding: 10px;
}
.modal-data {
  display: flex;
  align-items: center;
  justify-content: center;
}
.margin-0 {
  margin: 0px;
}
</style>
<style>
.flex {
  display: flex;
}
.justifycenter {
  justify-content: center;
}
.itemcenter {
  align-items: center;
}
</style>

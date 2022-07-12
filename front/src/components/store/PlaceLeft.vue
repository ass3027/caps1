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
                  color="#139DF2"
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
                    color="#139DF2"
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
        <div style="text-align: center">
          <v-btn color="#139DF2">초기화</v-btn>
          <v-btn @click="setting()" color="#139DF2">
            적용
          </v-btn>
        </div>
      </div>
      <div>
        <div>호텔유형</div>
        <div
          v-for="(option, i) in option "
          :key="i"
        >
          <v-checkbox
            v-model="checkOptions"
            :label="option.title"
            :value="option.title"
          />
        </div>
      </div>
    </v-card>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'PlaceLeft',
  props: [ 'option', 'category'],
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
      peopleCount: 1,
      checkOptions: []
    };
  },
  computed: {},
  mounted() {
    axios({
      method: 'GET',
      url: `/api/place1/${this.category}`,
    })
      .then((res) => {
        this.$store.commit('place/PlaceUpdate', res.data)
        console.log(res.data)
      })
    console.log(this.category)
  },
  created() {
    this.settingstart = this.startDate;
    this.settingend = this.endDate;
    console.log(this.category)
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
      // plusPeople() {
      //   this.peopleCount = this.peopleCount + 1;
      // },
      // minusPeople() {
      //   if (this.peopleCount > 1) {
      //     this.peopleCount = this.peopleCount - 1;
      //   }
    },
    setting() {
      axios({
        method: 'GET',
        url: `/api/place2/${this.category}/${this.checkOptions}`
      })
      .then((res)=>{
        this.$store.commit('place/PlaceOption', res.data)
        console.log(res.data)
      })
      // alert(this.checkOptions)
      // this.$store.commit('place/optionPlace', this.checkOptions)

      console.log(this.category, this.checkOptions)

      axios({
        method: 'GET',
        url: '/api/datePic',
        params: {
          'settingstart' : this.settingstart,
          'settingend' : this.settingend
        }
      })
        .then((res)=>{
          this.$store.commit('place/PlaceBook', res.data)
          console.log(res.data)
        })
    }
  },
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
  height: 100%;
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

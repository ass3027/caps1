<template>
  <v-row>
    <v-col
      cols="4"
      sm="6"
      md="8"
    >
      <v-dialog
        ref="dialog1"
        v-model="sDateModal"
        :return-value.sync="sDate"
        persistent
        width="350px"
      >
        <template #activator="{ on }">
          <v-text-field
            v-model="sDate"
            readonly
            :label="`${label}`"
            v-on="on"
          />
        </template>

        <v-card>
          <v-date-picker
            v-model="sDate"
            scrollable
            :allowed-dates="disablePastDates"
            style="height: 570px;"
          >
            <v-spacer />
            <v-btn
              text
              color="primary"
              @click="sDateModal = false"
            >
              Cancel
            </v-btn>
            <v-btn
              text
              color="primary"
              @click="sTimeModal = true"
            >
              OK
            </v-btn>
          </v-date-picker>
        </v-card>
      </v-dialog>

      <v-dialog
        ref="dialog2"
        v-model="sTimeModal"
        :return-value.sync="sTime"
        persistent
        width="400px"
      >
        <v-time-picker
          v-if="sTimeModal"
          v-model="sTime"
          scrollable
        >
          <v-spacer />
          <v-btn
            text
            color="primary"
            @click="sTimeModal = false"
          >
            Cancel
          </v-btn>
          <v-btn
            text
            color="primary"
            @click="update()"
          >
            OK
          </v-btn>
        </v-time-picker>
      </v-dialog>
    </v-col>
  </v-row>
</template>

<script>

export default {
  name: "DateTimePicker",
  props: ['label'],
  data: () => ({
    //(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
    sDate: "",
    sDateModal: false,
    sTime: "",
    sTimeModal: false,

  }),
  methods: {
    sSet() {
      this.sDate = this.sDate + " " + this.sTime;
      this.$refs.dialog1.save(this.sDate);
      this.$refs.dialog2.save(this.sTime);
      console.log(this.sDate);
      return this.sDate;
    },
    update() {
      this.$emit("child", this.sSet())
    },
    disablePastDates(val) {
      return val >= new Date().toISOString().substr(0, 10)
    },
  },
}
</script>


<style>
.v-date-picker-table {
  height: 360px;

}
</style>

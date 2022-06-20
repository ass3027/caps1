<template>
  <v-row>
    <v-col cols="4" sm="6" md="4">
      <v-dialog
        ref="dialog1"
        v-model="sDateModal"
        :return-value.sync="sDate"
        persistent
        lazy

        full-width
        width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="sDate"
            readonly
            v-on="on"
            :label="`${label}`"
          ></v-text-field>
        </template>
        <v-date-picker v-model="sDate"  scrollable :allowed-dates="disablePastDates">
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="sDateModal = false">Cancel</v-btn>
          <v-btn text color="primary" @click="sTimeModal = true">OK</v-btn>
        </v-date-picker>
      </v-dialog>

      <v-dialog
        ref="dialog2"
        v-model="sTimeModal"
        :return-value.sync="sTime"
        persistent
        lazy
        full-width
        width="290px"

      >
        <v-time-picker
          v-if="sTimeModal"
          v-model="sTime"
          full-width
          scrollable

        >
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="sTimeModal = false">Cancel</v-btn>
          <v-btn text color="primary" @click="update()">OK</v-btn>
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
    update(){
      this.$emit("child",this.sSet())
    },
    disablePastDates(val) {
      return val >= new Date().toISOString().substr(0, 10)
    },
  },


}
</script>

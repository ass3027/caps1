<template>
  <div>
    <v-calendar
      ref="calendar"
      v-model="value"
      :weekdays="weekday"
      :type="type"
      :events="events"
      :event-overlap-mode="mode"
      :event-overlap-threshold="30"
      :event-height=17
    ></v-calendar>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CalendarPreview",
  data () {
    return {
      type: 'month',
      types: ['month', 'week', 'day', '4day'],
      mode: 'stack',
      modes: ['stack', 'column'],
      weekday: [0, 1, 2, 3, 4, 5, 6],
      weekdays: [
        { text: 'Sun - Sat', value: [0, 1, 2, 3, 4, 5, 6] },
        { text: 'Mon - Sun', value: [1, 2, 3, 4, 5, 6, 0] },
        { text: 'Mon - Fri', value: [1, 2, 3, 4, 5] },
        { text: 'Mon, Wed, Fri', value: [1, 3, 5] },
      ],
      value: '',
      events: [],
      colors: ['blue'],
      names: ['sdf'],

    }
  },
  mounted() {
    axios.get(`/api/planner/${this.$store.state.user.userId}`)
      .then((res)=>{
        for (let i = 0; i < res.data.length; i++) {
          this.events.push({
            name: res.data[i].plan_name,
            start: new Date(res.data[i].plan_start),
            end: new Date(res.data[i].plan_end),
            color: 'blue'

          })
        }

      })
  },
  methods:{

  }

}
</script>

<style scoped>
</style>

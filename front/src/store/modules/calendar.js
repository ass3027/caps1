import {EventBus} from "@/eventBus/eventBus";

export default {
  namespaced:true,
  state: {
    calendar: [],
    selectDate:'',
    selectTime:25
  },
  mutations:{
    updateCalendar(state,calendar){
      state.calendar = calendar
      console.log(state.calendar)
    },
    updateCalendarDate: function (state, mapData) {
      if (state.selectTime === 25 || state.selectDate === '') return

      let partitionedCalendar = state.calendar.date[state.selectDate]

      let scheduleData

      if (mapData !== undefined) {
        const previousData = partitionedCalendar.get(state.selectTime)

        scheduleData = {
          gitem_id: previousData.gitem_id,
          pl_id: mapData.pl_id,
          expect_expenses: previousData.expect_expenses,
          mapX: mapData.mapX,
          mapY: mapData.mapY,
          address: mapData.address
        }
      } else {
        scheduleData = {
          gitem_id: 0,
          pl_id: 0,
          expect_expenses: 0,
          mapX: 0,
          mapY: 0,
          address: ''
        }
      }


      partitionedCalendar.set(state.selectTime, scheduleData)
      const convertedMap = [...partitionedCalendar]

      state.calendar.date[state.selectDate] = new Map(convertedMap.sort((a, b) => a[0] - b[0]));

      if(mapData===undefined) state.selectTime = 30

      console.log(state.calendar)
      EventBus.$emit('updateCalendar')
      EventBus.$emit('updateDate',state.selectDate)//경로 새로고침?
    },
    updateCalendarDetail(state,schedule){
        console.log(schedule)
        state.calendar.date[state.selectDate].set(state.selectTime,schedule)
    },
    updateSelect(state,data){
      state.selectDate = data.date
      state.selectTime = data.time
    },
    deleteCalendarDate(state){
      if(state.selectTime===25 || state.selectDate==='') return
      state.calendar.date[state.selectDate].delete(state.selectTime)
      const convertedMap = [...state.calendar.date[state.selectDate]]
      state.calendar.date[state.selectDate] = new Map(convertedMap.sort((a, b) => a[0] - b[0]));
      // console.log(state.calendar.date[state.selectDate].delete(state.selectTime))
      EventBus.$emit('updateCalendar')
      EventBus.$emit('updateDate',state.selectDate)//경로 새로고침?

    },
    // reload(state){
    //   const temp = state.calendar
    //   state.calendar = undefined
    //   state.calendar = temp
    // }
  },

  // actions : {
  //   changeCalendarDate({commit},mapData) {
  //     commit('updateCalendarDate',mapData)
  //   }
  // }
}

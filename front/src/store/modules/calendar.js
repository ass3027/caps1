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
    updateCalendarDate(state,mapData){
      if(state.selectTime===25 || state.selectDate==='') return
      state.calendar.date[state.selectDate].set(state.selectTime,mapData)
      const convertedMap = [...state.calendar.date[state.selectDate]]
      state.calendar.date[state.selectDate] = new Map(convertedMap.sort((a, b) => a[0] - b[0]));

      console.log(state.calendar)
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

      console.log(state.calendar.date[state.selectDate].delete(state.selectTime))

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

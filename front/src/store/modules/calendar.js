export default {
  namespaced:true,
  state: {
    calendar: [],
    selectDate:'',
    selectTime:0
  },
  mutations:{
    updateCalendar(state,calendar){
      state.calendar = calendar
    },
    updateCalendarDate(state,mapData){
      if(state.selectTime===0,state.selectDate==='') return
      state.calendar[state.selectDate][state.selectTime] = mapData
      console.log(state.calendar)
    },
    updateSelect(state,data){
      state.selectDate = data.date
      state.selectTime = data.time
    }
  }
}

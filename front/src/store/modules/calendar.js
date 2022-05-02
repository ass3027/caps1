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
    updateSelect(state,data){
      state.selectDate = data.date
      state.selectTime = data.time
    }
  }
}

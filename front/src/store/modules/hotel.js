export default {
  namespaced:true,
  state: {
    hotelInfo: "",
  },
  mutations:{
    updateHotel(state, newHotel){
      state.hotelInfo = newHotel
    }
  }
}

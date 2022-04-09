export default {
  namespaced:true,
  state: {
    hotelInfo: "",
    hotelPictures:[]
  },
  mutations:{
    updateHotel(state, newHotel){
      state.hotelInfo = newHotel
    },
    updateHotelPictures(state,data){
      state.hotelPictures=data;
    }
  }
}

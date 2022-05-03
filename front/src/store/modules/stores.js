export default {
  namespaced:true,
  state: {
    storeInfo: [],
  },
  mutations:{
    updateStore(state, newStore){
      state.storeInfo = newStore
      console.log("updateStore")
    },

    // updateHotelPictures(state,data){
    //   console.log("updateHotelPictures")

      // state.hotelInfo.forEach(i=>{
      //   data.forEach(j=>{
      //     console.log("스토어 동등연산 비교"+i.store_id+"///"+j.store_id)
      //     console.log(i.store_id)
      //     console.log(j.store_id)
      //     console.log(i.store_id==j.store_id)
      //     if(i.store_id==j.store_id)
      //       i.pic_name=j.pic_name;
      //     console.log(i.pic_name);
      //   })
      // })
      // console.log("updateHotelPictures complete")
      // }
  }
}

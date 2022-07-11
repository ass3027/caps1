export default {
  namespaced:true,
  state: {
    Info: [],
    hotelPictures:[]
  },
  mutations: {
    update(state, newStore) {
      state.Info = new
      console.log("update")
    },

    // updateHotelPictures(state,data){
    //   console.log("updateHotelPictures")

    // state.hotelInfo.forEach(i=>{
    //   data.forEach(j=>{
    //     console.log("스토어 동등연산 비교"+i._id+"///"+j._id)
    //     console.log(i._id)
    //     console.log(j._id)
    //     console.log(i._id==j._id)
    //     if(i.store_id==j.store_id)
    //       i.pic_name=j.pic_name;
    //     console.log(i.pic_name);
    //   })
    // })
    // console.log("updateHotelPictures complete")
    // }
  }
}

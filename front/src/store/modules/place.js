export default {
  namespaced:true,
  state: {
    placeInfo: [],
  },
  mutations:{
    updatePlace(state, newPlace){
      state.placeInfo = newPlace
      console.log("Vuex Place")
    },
  }
}

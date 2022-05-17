export default {
  namespaced:true,
  state: {
    placeInfo: [],
  },
  mutations:{
    updateStore(state, newStore){
      state.placeInfo = newStore
      console.log("Vuex Place")
    },
  }
}

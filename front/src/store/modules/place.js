export default {
  namespaced:true,
  state: {
    placeInfo: [],
    placeOriginal:[],
    place:[],
    placeAera: [],
  },
  mutations:{
    PlaceUpdate(state, placeNew){
      state.place = placeNew;
    },

    PlaceAeraUpdate(state, aeraUpdate){
      state.place = aeraUpdate;
    },

    PlaceOption(state, option){
      state.place = option;
    },

    PlaceKeyword(state, keyword){
      state.place = keyword;
    },

    PlaceBook(state, Book){
      state.place = Book;
    },




    updatePlace(state, newPlace){
      state.placeOriginal = newPlace
      state.placeInfo = newPlace.reduce(function(acc, current) {
        if (acc.findIndex(({ pl_id }) => pl_id === current.pl_id) === -1) {
          acc.push(current);
        }
        return acc;
      }, []);
      console.log("Vuex Place")
    },
    optionPlace(state, checkOptions){
      console.log("checkOptions")
      checkOptions.forEach(i => console.log(i))
      let placeOriginal = state.placeOriginal

      console.log("placeOriginal")
      placeOriginal.forEach(i => console.log(i.option_id))
      //옵션체크
      let result1 = placeOriginal.filter(i => checkOptions.includes(i.option_id) )

      //중복제거
      state.placeInfo = result1.reduce(function(acc, current) {
        if (acc.findIndex(({ pl_id }) => pl_id === current.pl_id) === -1) {
          acc.push(current);
        }
        return acc;
      }, []);

      console.log(state.placeInfo)
    }
  }
}

export default {
    namespaced:true,
    state: {
        userId: "",
    },
    mutations:{
        updateUserId(state,newId){
            state.userId = newId
        }
    }
}

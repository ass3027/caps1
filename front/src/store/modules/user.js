export default {
    namespaced:true,
    state: {
        userId: "",
      planId:0,
    },
    mutations:{
        updateUserId(state,newId){
            state.userId = newId
        },
      updatePlanId(state,newPlanId){
          state.planId = newPlanId
      }
    }
}

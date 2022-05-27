import axios from 'axios'

export default {
    namespaced:true,
    state: {
        userId: 'anonymousUser',
        planId:0,
    },
    getters:{
      isLogin(state){
        return state.userId !== 'anonymousUser';

      }
    },
    mutations:{
        updateUserId(state,newId){
            state.userId = newId
        },
      updatePlanId(state,newPlanId){
          state.planId = newPlanId
          console.log(state.planId)
      }
    },
    actions:{
      setUser: function ({commit, state}, newId) {

        commit('updateUserId', newId)
        if (newId === '') {
          commit('updatePlanId', 0)
          return;
        }
        if (state.planId === 0) {
          console.log("plan Update")
          axios.get('/api/planner/' + newId)
            .then((res) => {
              let plan_id = 0
              if (res.data.length !== 0) {
                plan_id = res.data[0].plan_id
              }
              commit('updatePlanId', plan_id)
            })
            .catch((err) => {
              console.log(err)
            })
        }


      }
    }
}

import axios from 'axios'

export default {
    namespaced:true,
    state: {
        userId: '',
        planId:0,
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
      setUser({commit},newId) {

        commit('updateUserId',newId)
        if(newId==='') {
          commit('updatePlanId',0)
          return;
        }
        axios.get('/api/planner/'+newId)
          .then( (res)=>{
            console.log(res.data[0].plan_id)
            commit('updatePlanId',res.data[0].plan_id)
          })
          .catch( (err)=>{
            console.log(err)
          })


      }
    }
}

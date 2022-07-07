import axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: 'anonymousUser',
    userRole:undefined,
    planId: 0,
  },
  getters: {
    isLogin(state) {
      return state.userId !== 'anonymousUser';
    }
  },
  mutations: {
    updateUser(state, user) {
      state.userId = user.id
      state.userRole = user.role
      console.log(state)
    },
    updatePlanId(state, newPlanId) {
      state.planId = newPlanId
      console.log(state.planId)
    }
  },
  actions: {
    setUser: function ({commit, state}, user) {

      commit('updateUser', user)
      if (user.id === '') {
        commit('updatePlanId', 0)
        return;
      }
      if (state.planId === 0) {
        console.log("plan Update")
        axios.get('/api/planner/' + user.id)
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

import axios from "axios";

export default {
  state: {
    userInfo: {},
    feedId: 0,
  },
  getteres: {
    userInfo: (state) => state.userInfo,
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => (state.userInfo = userInfo),
    SET_SHOWFEEDID: (state, feedId) => (state.feedId = feedId)
  },
  actions: {
    fetchUserInfo({ commit }) {
      axios({
        method: "get",
        url: "http://localhost:8080/user/profile",
        headers: "유저토큰",
      }).then((res) => {
        commit("SET_USERINFO", res.data);
      });
    },
    setShowFeedId({ commit }) {
      commit('SET_SHOWFEEDID', feedId)
    }
  },
};

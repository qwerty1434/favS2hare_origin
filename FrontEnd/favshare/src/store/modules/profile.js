import axios from "axios";

export default {
  state: {
    userInfo: {},
    feedPops: [],
  },
  getteres: {
    userInfo: (state) => state.userInfo,
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => (state.userInfo = userInfo),
    SET_FEEDPOPS: (state, feedPops) => (state.feedPops = feedPops),
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
    fetchFeedPops({ commit }, feedId) {
      axios({
        method: "get",
        url: `http://localhost:8080/user/profile/feed/${feedId}`,
      }).then((res) => {
        commit("SET_FEEDPOPS", res.data);
      });
    },
  },
};

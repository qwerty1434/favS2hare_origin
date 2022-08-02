import axios from "axios";

export default {
  state: {
    userInfo: {},
    feedPops: [],
    followtab: 0, // 팔로워 클릭이면 0, 팔로잉 클릭이면 1
  },
  getteres: {
    userInfo: (state) => state.userInfo,
    followtab: (state) => state.followtab,
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => (state.userInfo = userInfo),
    SET_FEEDPOPS: (state, feedPops) => (state.feedPops = feedPops),
    SET_FOLLOWTAB: (state, followtab) => (state.followetab = followtab),
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
    setTabFollower({ commit }) {
      commit("SET_FOLLOWTAB", 0);
    },
    setTabFollowing({ commit }) {
      commit("SET_FOLLOWTAB", 1);
    },
  },
};

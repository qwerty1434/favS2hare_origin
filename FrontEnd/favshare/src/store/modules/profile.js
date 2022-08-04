import axios from "axios";

export default {
  state: {
    userInfo: {},
    feedPops: [],
    followtab: 0, // 팔로워 클릭이면 0, 팔로잉 클릭이면 1
    isDelete: true,
  },
  getteres: {
    userInfo: (state) => state.userInfo,
    followtab: (state) => state.followtab,
    isDelete: (state) => state.isDelete,
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => (state.userInfo = userInfo),
    SET_FEEDPOPS: (state, feedPops) => (state.feedPops = feedPops),
    SET_FOLLOWTAB: (state, followtab) => (state.followtab = followtab),
    SET_ISDELETE: (state, isDelete) => (state.isDelete = isDelete),
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
    fetchIsDelete({ commit }) {
      commit("SET_ISDELETE", true);
    },
    fetchIsNotDelete({ commit }) {
      commit("SET_ISDELETE", false);
    },
  },
};

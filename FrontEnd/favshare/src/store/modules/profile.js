import axios from "axios";

export default {
  state: {
    userInfo: {},
    feedPops: [],
    followtab: 0, // 팔로워 클릭이면 0, 팔로잉 클릭이면 1
    isDelete: true,
    deleteFeedPopsList: [],
  },
  getters: {
    userInfo: (state) => state.userInfo,
    followtab: (state) => state.followtab,
    isDelete: (state) => state.isDelete,
    deleteFeedPopsList: (state) => state.isDelete,
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => (state.userInfo = userInfo),
    SET_FEEDPOPS: (state, feedPops) => (state.feedPops = feedPops),
    SET_FOLLOWTAB: (state, followtab) => (state.followtab = followtab),
    SET_ISDELETE: (state, isDelete) => (state.isDelete = isDelete),
    ADD_FEEDPOPSLIST: (state, id) => state.deleteFeedPopsList.push(id),
    SPLICE_FEEDPOPSLIST: function (state, id) {
      const index = state.deleteFeedPopsList.indexOf(id);
      state.deleteFeedPopsList.splice(index, 1);
    },
    SPLICE_FEEDPOPS: function (state, id) {
      state.feedPops.forEach((value, index, array) => {
        console.log(array);
        if (value.id === id) {
          state.feedPops.spilce(index, 1);
        }
      });
    },
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
    fetchIsDelete({ commit }, isDelete) {
      commit("SET_ISDELETE", isDelete);
    },
    fetchIsNotDelete({ commit }, isDelete) {
      commit("SET_ISDELETE", isDelete);
    },
    spliceFeedPops({ commit }, id) {
      commit("SPLICE_FEEDPOPS", id);
    },
  },
};

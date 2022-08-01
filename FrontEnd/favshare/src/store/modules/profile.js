import axios from "axios";

export default {
  state: {
    userInfo: {},
  },
  getteres: {
    userInfo: (state) => state.userInfo,
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => (state.userInfo = userInfo),
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
  },
};

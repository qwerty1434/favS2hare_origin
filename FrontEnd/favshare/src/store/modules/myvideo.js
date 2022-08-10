import axios from "axios";

export default {
  state: {
    userIdInMyVideo: 1,
    myVideoList: [],
  },
  getters: {
    userIdInMyVideo: (state) => state.userIdInMyVideo,
    myVideoList: (state) => state.myVideoList,
  },
  mutations: {
    SET_MYYOUTUBE: (state, myVideoList) => (state.myVideoList = myVideoList),
  },
  actions: {
    getMyVideo({ commit }, { userIdInMyVideo }) {
      console.log(userIdInMyVideo);
      axios({
        method: "get",
        url: `http://localhost:8080/youtube/bookmark/${userIdInMyVideo}`,
      }).then((res) => {
        console.log(res.data);
        commit("SET_MYYOUTUBE", res.data);
      });
    },
  },
};

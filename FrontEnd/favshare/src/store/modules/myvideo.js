import axios from "axios";

export default {
  state: {
    myVideoList: [],
  },
  getters: {
    myvideo: (state) => state.myVideoList,
  },
  mutations: {
    SET_MYYOUTUBE: (state, myVideoList) => (state.myVideoList = myVideoList),
  },
  actions: {
    getMyVideo({ commit }, { userId }) {
      console.log(userId);
      axios({
        method: "get",
        url: `http://localhost:8080/youtube/bookmark/${userId}`,
      }).then((res) => {
        console.log(res);
        commit("SET_MYYOUTUBE");
      });
    },
  },
};

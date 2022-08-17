import axios from "axios";

export default {
  state: {
    // userIdInMyVideo: 1,
    myVideoList: [],
  },
  getters: {
    userIdInMyVideo: (state) => state.userIdInMyVideo,
    myVideoList: (state) => state.myVideoList,
  },
  mutations: {
    SET_MY_YOUTUBE: (state, myVideoList) => (state.myVideoList = myVideoList),
  },
  actions: {
    getMyVideo({ commit }, { userIdInMyVideo }) {
      console.log(userIdInMyVideo);
      axios({
        method: "get",
        url: `http://localhost:8080/youtube/bookmark/${userIdInMyVideo}`,
      }).then((res) => {
        commit("SET_MY_YOUTUBE", res.data);
      });
    },
    deleteMyVideo({ commit }, { userId, youtubeUrl }) {
      axios({
        method: "delete",
        url: `http://localhost:8080/youtube/bookmark`,
        data: {
          userId: userId,
          youtubeUrl: youtubeUrl,
        },
      })
        .then(() => {
          axios({
            method: "get",
            url: `http://localhost:8080/youtube/bookmark/${userId}`,
          })
            .then((res) => {
              console.log("삭제 후 " + res.data.length);
              commit("SET_MY_YOUTUBE", res.data);
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  state: {
    myVideoList: [],
  },
  getters: {
    myVideoList: (state) => state.myVideoList,
  },
  mutations: {
    SET_MY_YOUTUBE: (state, myVideoList) => (state.myVideoList = myVideoList),
  },
  actions: {
    getMyVideo({ commit }, { userId }) {
      console.log(userId);
      axios({
        method: "get",
        url: api.youtubeBookmark.youtubeBookmarkUser(userId),
      }).then((res) => {
        commit("SET_MY_YOUTUBE", res.data);
      });
    },
    deleteMyVideo({ commit }, { userId, youtubeUrl }) {
      axios({
        method: "delete",
        url: api.youtubeBookmark.youtubeBookmark(),
        data: {
          userId: userId,
          youtubeUrl: youtubeUrl,
        },
      })
        .then(() => {
          axios({
            method: "get",
            url: api.youtubeBookmark.youtubeBookmarkUser(userId),
          })
            .then((res) => {
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

import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  state: {
    searchedPopsList: [],
    searchedYoutubeList: [],
    searchedFollowingList: [],
  },
  getters: {
    searchedPopsList: (state) => state.searchedPopsList,
    searchedYoutubeList: (state) => state.searchedYoutubeList,
    searchedFollowingList: (state) => state.searchedFollowingList,
  },
  mutations: {
    SET_SEARCHEDPOPS_LIST: (state, searchedPopsList) =>
      (state.searchedPopsList = searchedPopsList),
    SET_SEARCHEDYOUTUBE_LIST: (state, searchedYoutubeList) =>
      (state.searchedYoutubeList = searchedYoutubeList),
    SET_SEARCHEDFOLLOWING_LIST: (state, searchedFollowingList) =>
      (state.searchedFollowingList = searchedFollowingList),
  },
  actions: {
    getSearchedPopsList({ commit }, { keyword, userId }) {
      console.log(keyword);
      axios({
        method: "post",
        url: api.search.search(),
        data: {
          message: keyword,
          userId: userId,
        },
      }).then((res) => {
        commit("SET_SEARCHEDPOPS_LIST", res.data.pop);
      });
    },
    getSearchedYoutubeList({ commit }, { keyword }) {
      const API_KEY = process.env.VUE_APP_API_KEY_1;
      axios
        .get(
          `https://www.googleapis.com/youtube/v3/search?part=snippet&q=${keyword}&key=${API_KEY}`
        )
        .then((res) => {
          const searchedYoutubeList = res.data.items.map(function (item) {
            return item.id.videoId;
          });
          console.log(searchedYoutubeList);
          commit("SET_SEARCHEDYOUTUBE_LIST", searchedYoutubeList);
        });
    },
    getSearchedFollowingList({ commit }, { keyword, userId }) {
      axios({
        method: "post",
        url: api.search.search(),
        data: {
          message: keyword,
          userId: userId,
        },
      }).then((res) => {
        console.log(res.data);
        commit("SET_SEARCHEDFOLLOWING_LIST", res.data.user);
      });
    },
  },
};

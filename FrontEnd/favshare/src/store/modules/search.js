import axios from "axios";

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
    getSearchedPopsList({ commit }, { keyword }) {
      console.log(keyword);
      axios({
        method: "get",
        url: `http://localhost:8080/search/${keyword}`,
      }).then((res) => {
        commit("SET_SEARCHEDPOPS_LIST", res.data.pop);
      });
    },
    getSearchedYoutubeList({ commit }, { keyword }) {
      axios({
        method: "get",
        url: `http://localhost:8080/search/${keyword}`,
      }).then((res) => {
        commit("SET_SEARCHEDYOUTUBE_LIST", res.data.youtube);
      });
    },
    getSearchedFollowingList({ commit }, { keyword }) {
      axios({
        method: "get",
        url: `http://localhost:8080/search/${keyword}`,
      }).then((res) => {
        console.log(res.data);
        commit("SET_SEARCHEDFOLLOWING_LIST", res.data.user);
      });
    },
  },
};

// import router from "@/router";
import axios from "axios";

export default {
  state: {
    homeYoutubes: [],
    newsFeedPops: [],
  },
  getters: {
    homeYoutubes: state => state.homeYoutubes,
    newsFeedPops: state => state.newsFeedPops
  },
  mutations: {
    SET_HOMEYOUTUBES: (state, homeYoutubes) => (state.homeYoutubes = homeYoutubes),
    SET_FEEDPOPS: (state, newsFeedPops) => (state.newsFeedPops = newsFeedPops),
  },
  actions: {
    // YoutubeList.vue 에서 사용
    fetchHomeYoutubes({ commit }) {
      axios({
        url: "http://localhost:8080/youtube",
        method: "get",
        // headers: '유저토큰'
      })
      .then((res) => commit("SET_HOMEYOUTUBES", res.data))
      .catch((err) => console.log(err));
    },
    // NewsFeedList.vue 에서 사용
    fetchFeedPops({ commit }) {
      axios({
        method: "get",
        url: 'http://localhost/8080/pop',
        // headers: "유저토큰"
      })
      .then((res) => commit("SET_FEEDPOPS", res.data))
      .catch((err) => console.log(err))
    }
  },
};

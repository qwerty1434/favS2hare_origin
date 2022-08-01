// import router from "@/router";
import axios from "axios";

export default {
  state: {
    homeYoutubes: [],
    newsFeedPops: [],
    videoInfo: {}, // { youtubeId: ?, videoId: ?, channelName: ?, channelProfilePic: ?, videoTitle: ? }
  },
  getters: {
    homeYoutubes: (state) => state.homeYoutubes,
    newsFeedPops: (state) => state.newsFeedPops,
    videoInfo: (state) => state.videoInfo,
  },
  mutations: {
    SET_HOMEYOUTUBES: (state, homeYoutubes) =>
      (state.homeYoutubes = homeYoutubes),
    SET_FEEDPOPS: (state, newsFeedPops) => (state.newsFeedPops = newsFeedPops),
    SET_VIDEOINFO: (state, videoInfo) => (state.videoInfo = videoInfo),
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
        url: "http://localhost/8080/pop",
        // headers: "유저토큰"
      })
        .then((res) => commit("SET_FEEDPOPS", res.data))
        .catch((err) => console.log(err));
    },
    // YoutubeView에서 YoutubeListItem에서 params로 넘겨 받은 데이터 저장
    fetchYoutubeInfo({ commit }, videoInfo) {
      commit("SET_VIDEOINFO", videoInfo);
    },
  },
};

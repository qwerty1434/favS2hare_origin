// import router from "@/router";
import axios from "axios";

export default {
  state: {
    homeYoutubes: [],
    newsFeedPops: [],
    videoInfo: {}, // { videoId: ?, channelName: ?, channelProfilePic: ?, videoTitle: ? }
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
    fetchHomeYoutubes({ commit, getters }) {
      axios({
        url: `http://localhost:8080/youtube/${getters.userId}`,
        method: "get",
      })
        .then((res) => commit("SET_HOMEYOUTUBES", res.data))
        .catch((err) => console.log(err));
    },
    // NewsFeedList.vue 에서 사용
    fetchFriendFeedPops({ commit, getters }) {
      console.log(`http://localhost/8080/pop/friend/${getters.userId}`);
      axios({
        method: "get",
        url: `http://localhost:8080/pop/friend/${getters.userId}`,
      })
        .then((res) => {
          commit("SET_FEEDPOPS", res.data);
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // YoutubeView에서 YoutubeListItem에서 params로 넘겨 받은 데이터 저장
    fetchYoutubeInfo({ commit }, videoInfo) {
      commit("SET_VIDEOINFO", videoInfo);
    },
  },
};

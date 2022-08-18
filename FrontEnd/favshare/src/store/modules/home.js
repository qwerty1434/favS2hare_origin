// import router from "@/router";
import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  state: {
    homeYoutubes: [],
    newsFeedPops: [],
    videoInfo: {}, // { videoId: ?, channelName: ?, channelProfilePic: ?, videoTitle: ? }
    isFeed: false,
    homeActiveTab: 0,
  },
  getters: {
    homeYoutubes: (state) => state.homeYoutubes,
    newsFeedPops: (state) => state.newsFeedPops,
    videoInfo: (state) => state.videoInfo,
    isFeed: (state) => state.isFeed,
    homeActiveTab: (state) => state.homeActiveTab,
  },
  mutations: {
    SET_HOMEYOUTUBES: (state, homeYoutubes) =>
      (state.homeYoutubes = homeYoutubes),
    SET_FEEDPOPS: (state, newsFeedPops) => (state.newsFeedPops = newsFeedPops),
    SET_VIDEOINFO: (state, videoInfo) => (state.videoInfo = videoInfo),
    SET_ISFEED: (state, isFeed) => (state.isFeed = isFeed),
    SET_HOMEACTIVETAB: (state, num) => (state.homeActiveTab = num),
  },
  actions: {
    // YoutubeList.vue 에서 사용
    fetchHomeYoutubes({ commit, getters }) {
      axios({
        // url: `http://13.124.112.241:8080/youtube/${getters.userId}`,
        url: api.youtube.youtube(getters.userId),
        method: "get",
      })
        .then((res) => commit("SET_HOMEYOUTUBES", res.data))
        .catch((err) => console.log(err));
    },
    // NewsFeedList.vue 에서 사용
    fetchFriendFeedPops({ commit, getters }) {
      axios({
        method: "get",
        // url: `http://13.124.112.241:8080/pop/friend/${getters.userId}`,
        url: api.pop.friend(getters.userId),
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

    setTabYoutube({ commit }) {
      commit("SET_ISFEED", false);
      commit("SET_HOMEACTIVETAB", 0);
    },
    setTabFeed({ commit }) {
      commit("SET_ISFEED", true);
      commit("SET_HOMEACTIVETAB", 1);
    },
  },
};

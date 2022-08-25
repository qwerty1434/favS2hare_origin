/* eslint-disable */
import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  state: {
    homeYoutubes: [],
    newsFeedPops: [],
    videoInfo: {},
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
    SET_HOMEYOUTUBES: (state, homeYoutubes) => (state.homeYoutubes = homeYoutubes),
    SET_FEEDPOPS: (state, newsFeedPops) => (state.newsFeedPops = newsFeedPops),
    SET_VIDEOINFO: (state, videoInfo) => (state.videoInfo = videoInfo),
    SET_ISFEED: (state, isFeed) => (state.isFeed = isFeed),
    SET_HOMEACTIVETAB: (state, num) => (state.homeActiveTab = num),
  },
  actions: {
    fetchHomeYoutubes({ commit, getters }) {
      const API_KEY = process.env.VUE_APP_API_KEY_1;
      axios({
        url: api.youtube.youtube(getters.userId),
        method: "get",
      })
        .then((res) => {
          axios
            .get(
              `https://www.googleapis.com/youtube/v3/search?part=snippet&q=${res.data}&key=${API_KEY}&maxResults=50`
            )
            .then((res) => {
              const homeYoutubes = res.data.items.map(function (item) {
                return {
                  youtubeId: item.id.videoId,
                };
              });
              commit("SET_HOMEYOUTUBES", homeYoutubes);
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => console.log(err));
    },
    fetchFriendFeedPops({ commit, getters }) {
      axios({
        method: "get",
        url: api.pop.friend(getters.userId),
      })
        .then((res) => {
          commit("SET_FEEDPOPS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
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

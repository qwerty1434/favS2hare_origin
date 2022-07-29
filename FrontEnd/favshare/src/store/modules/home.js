// import router from "@/router";
import axios from "axios";

export default {
  state: {
    homeYoutubes: [],
    homeYoutube: {},
    thumbNail: '',
    channelPic: '',
    channelName: ''
  },
  getters: {
    homeYoutubes: state => state.homeYoutubes
  },
  mutations: {
    SET_HOMEYOUTUBES: (state, homeYoutubes) => (state.homeYoutubes = homeYoutubes),
    SET_THUMBNAIL: (state, thumbNail) => (state.thumbNail = thumbNail),
    SET_CHANNELPIC: (state, channelPic) => (state.channelPic = channelPic),
    SET_CHANNELNAME: (state, channelName) => (state.channelName = channelName)
  },
  actions: {
    // YoutubeList.vue 에서 사용
    fetchHomeYoutubes({ commit }) {
      axios({
        url: "http://localhost:8080/youtube",
        method: "get",
      })
      .then((res) => commit("SET_HOMEYOUTUBES", res.data))
      .catch((err) => console.log(err));
    },
    // YoutubeListItem.vue 에서 사용
  },
};

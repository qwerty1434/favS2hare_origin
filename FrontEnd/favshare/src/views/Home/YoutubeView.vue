<template>
  <div>
    <nav-bar class="top"></nav-bar>
    <youtube-video class="youtube__view"></youtube-video>
    <v-divider class="ma-2"></v-divider>
    <related-pops-list class="ml-5 mr-5"></related-pops-list>
    <div class="bottom">
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import YoutubeVideo from "../../components/Home/YoutubeVideo.vue";
import NavBar from "../../components/NavBar.vue";
import RelatedPopsList from "@/components/Home/RelatedPopsList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

import { mapActions } from "vuex";

export default {
  components: { NavBar, YoutubeVideo, RelatedPopsList, BottomNavigationBar },
  name: "YoutubeView",
  props: {
    youtubeId: Number,
    videoInfo: Object,
  },
  methods: {
    ...mapActions(["fetchYoutubeInfo"]),
  },
  created() {
    const vuexVideoInfo = Object.assign(this.videoInfo);
    this.fetchYoutubeInfo(vuexVideoInfo);
  },
};
</script>

<style scoped>
.top {
  position: fixed;
  width: 100%;
}

.youtube__view {
  padding-top: 60px;
  padding-left: 15px;
}

.rpops {
  width: 360px;
  padding: 20px;
  overflow: scroll;
  overflow: auto;
  white-space: nowrap;
  display: flex;
}

.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>

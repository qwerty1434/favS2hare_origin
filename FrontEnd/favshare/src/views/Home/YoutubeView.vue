<template>
  <div>
    <nav-bar class="top"></nav-bar>
    <youtube-video
      class="youtube__view"
      :video-info="this.videoInfo"
    ></youtube-video>
    <related-pops-list
      :youtube-pk="this.youtubePk"
      class="rpops"
    ></related-pops-list>
  </div>
</template>

<script>
import YoutubeVideo from "../../components/Home/YoutubeVideo.vue";
import NavBar from "../../components/NavBar.vue";
import RelatedPopsList from "@/components/Home/RelatedPopsList.vue";

export default {
  components: { NavBar, YoutubeVideo, RelatedPopsList },
  name: "YoutubeView",
  props: {
    videoInfo: Object,
    youtubePk: Number,
  },
};
</script>

<style>
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
  /* 가로 스크롤 */
  overflow: auto;
  white-space: nowrap;
  display: flex;
}
</style>

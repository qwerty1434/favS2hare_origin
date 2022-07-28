<template>
  <div>
    <youtube
      :video-id="this.popsItem.youtubeUrl"
      :player-vars="playerVars"
      ref="youtube"
      :width="200"
      :height="112"
      @playing="playing"
    ></youtube>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "RelatedPopsListItem",
  props: {
    popsItem: Object,
  },
  data() {
    return {
      playerVars: {
        autoplay: 1,
        controls: 0,
      },
    };
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {
      console.log("playing");
    },
  },
};
</script>

<style></style>

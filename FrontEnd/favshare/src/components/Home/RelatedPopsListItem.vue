<template>
  <div>
    <youtube
      :video-id="this.youtubeUrl"
      :player-vars="playerVars"
      ref="youtube"
      :width="200"
      :height="112"
      @playing="playing"
      style="pointer-events: none"
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
    url: String,
  },
  data() {
    return {
      playerVars: {
        autoplay: 1,
        controls: 0,
        mute: 1,
      },
    };
  },
  methods: {
    playVideo() {
      console.log(this.url);
      this.player.playVideo();
    },
    playing() {
      console.log("playing");
    },
  },
  created() {
    this.playVideo();
  },
};
</script>

<style></style>

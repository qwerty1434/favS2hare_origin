<template>
  <div>
    <youtube
      :video-id="url"
      :player-vars="playerVars"
      ref="youtube"
      :width="200"
      :height="112"
    ></youtube>
  </div>
</template>

<script>
// @playing="playing"
// style="pointer-events: none"
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

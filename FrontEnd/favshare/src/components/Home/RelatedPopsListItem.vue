<template>
  <div>
    <youtube
      :video-id="url"
      :player-vars="playerVars"
      :ref="`pops` + this.popsItem.id"
      @ready="onPlayerReady"
      @playing="onPlaying"
      :width="200"
      :height="112"
      style="pointer-events: none; border-radius: 8px"
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
  computed: {
    player() {
      return this.$refs[`pops${this.popsItem.id}`].player;
    },
  },
  data() {
    return {
      playerVars: {
        autoplay: 1,
        controls: 0,
        mute: 1,
      },
      section: {
        start: this.popsItem.startSecond,
        end: this.popsItem.endSecond,
      },
    };
  },
  methods: {
    onPlayerReady() {
      this.player.seekTo(this.section.start);
      this.player.playVideo();
    },
    onPlaying() {
      const duration = this.section.end - this.section.start;
      setTimeout(this.restartVideoSection, duration * 1000);
    },
    restartVideoSection() {
      this.player.seekTo(this.section.start);
    },
  },
  created() {
    this.playVideo();
  },
};
</script>

<style></style>

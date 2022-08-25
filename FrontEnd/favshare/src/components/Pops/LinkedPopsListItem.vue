<template>
  <v-col cols="6" align="center">
    <v-card class="pops-container" elevation="2">
      <youtube
        :video-id="youtubeUrl"
        :player-vars="playerVars"
        :ref="'pops' + this.popsId"
        width="160"
        height="90"
        @ready="onPlayerReady"
        @playing="onPlaying"
        style="pointer-events: none; border-radius: 8px"
      ></youtube>
    </v-card>
  </v-col>
</template>

<script>
export default {
  name: "LinkedPopsListItem",
  props: ["popsId", "youtubeUrl", "startSecond", "endSecond", "view", "count"],
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      },
      section: {
        start: this.startSecond,
        end: this.endSecond,
      },
    };
  },
  computed: {
    player() {
      return this.$refs[`pops${this.popsId}`].player;
    },
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
};
</script>

<style scoped>
.pops-container {
  width: 160px;
  height: 90px;
  border-radius: 8px;
}
</style>

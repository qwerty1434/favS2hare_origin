<template>
  <div class="unselected-youtube-container" @click="onClick">
    <youtube
      :ref="popsId"
      :video-id="getYoutubeId"
      :player-vars="playerVars"
      width="160"
      height="90"
      @ready="onPlayerReady"
      @playing="onPlaying"
      @click="onClick"
      style="pointer-events: none; border-radius: 8px"
    ></youtube>
  </div>
</template>

<script>
export default {
  name: "MyPopsListItem",
  props: ["popsId", "youtubeUrl", "startSecond", "endSecond"],
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
      isSelected: false,
    };
  },
  computed: {
    getYoutubeId() {
      return this.youtubeUrl.slice(-11);
    },
    player() {
      return this.$refs[this.popsId].player;
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
    onClick(event) {
      if (this.isSelected) {
        event.target.setAttribute("class", "unselected-youtube-container");
        this.isSelected = false;
      } else {
        event.target.setAttribute("class", "selected-youtube-container");
        this.isSelected = true;
      }
    },
  },
};
</script>

<style scoped>
/* youtube 태그와 크기, border-radius 일치 */
.unselected-youtube-container {
  width: 160px;
  height: 90px;
  border-radius: 10px;
  border: 2px solid #afb1b6;
  box-sizing: content-box;
}

.selected-youtube-container {
  width: 160px;
  height: 90px;
  border-radius: 10px;
  border: 2px solid #ff5d5d;
  box-sizing: content-box;
}
</style>

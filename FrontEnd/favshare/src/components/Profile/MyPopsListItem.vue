<template>
  <v-col cols="6" align="center">
    <div :id="popsId" class="unselected youtube-container" @click="onClick">
      <v-btn
        class="button-in-container"
        v-if="!isSelected"
        fab
        x-small
        height="18"
        width="18"
        elevation="1"
      ></v-btn>
      <v-btn
        class="button-in-container"
        v-if="isSelected"
        fab
        x-small
        dark
        color="#ff5d5d"
        height="18"
        width="18"
        elevation="1"
      >
        <v-icon>mdi-check</v-icon>
      </v-btn>
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
  </v-col>
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
  watch: {
    isSelected(newVal) {
      const container = document.getElementById(`${this.popsId}`);
      if (newVal) {
        container.setAttribute("class", "selected youtube-container");
      } else {
        container.setAttribute("class", "unselected youtube-container");
      }
    },
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
    onClick() {
      if (this.isSelected) {
        this.isSelected = false;
      } else {
        this.isSelected = true;
      }
    },
  },
};
</script>

<style scoped>
/* youtube 태그와 크기, border-radius 일치 */
.youtube-container {
  width: 160px;
  height: 90px;
  border-radius: 10px;
  box-sizing: content-box;
  margin-bottom: 6px;
  position: relative;
}

.unselected {
  border: 2px solid #afb1b6;
}

.selected {
  border: 2px solid #ff5d5d;
}

.button-in-container {
  position: absolute;
  top: 5px;
  right: 5px;
}
</style>

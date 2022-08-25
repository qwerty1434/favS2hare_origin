<template>
  <v-container>
    <v-row class="position-relative" no-gutters>
      <v-icon
        class="current-time-bar"
        color="#ffe3a9"
        :style="'left: ' + currentTimeBarPosition + 'px'"
        >mdi-triangle-small-down</v-icon
      >
      <v-col cols="12" align="center">
        <youtube
          :video-id="$route.query.youtubeUrl"
          :player-vars="playerVars"
          :ref="'youtube' + $route.query.youtubeId"
          width="320"
          height="180"
          style="pointer-events: none"
          nocookie
          @ready="onReady"
        ></youtube>
      </v-col>
      <v-col cols="12">
        <v-range-slider
          :max="max"
          :min="min"
          v-model="range"
          :thumb-size="26"
          thumb-label="always"
          color="#ffc3c3"
          thumb-color="#ff5d5d"
          track-color="#ffe3a9"
          dense
          ticks
          hide-details
          @mouseup="onMouseUp"
          ><template v-slot:thumb-label="{ value }">{{ secondToMinute(value) }} </template>
        </v-range-slider>
      </v-col>
      <v-col v-if="!isPlaying" cols="1">
        <v-icon @click="playVideo">mdi-play-circle</v-icon>
      </v-col>
      <v-col v-if="isPlaying" cols="1">
        <v-icon @click="pauseVideo">mdi-pause-circle</v-icon>
      </v-col>
      <v-col v-if="isMuted" cols="1">
        <v-icon @click="mute">mdi-volume-off</v-icon>
      </v-col>
      <v-col v-if="!isMuted" cols="1">
        <v-icon @click="mute">mdi-volume-high</v-icon>
      </v-col>
      <v-col offset="8" cols="1">
        <v-icon @click="cutMaxMin">mdi-content-cut</v-icon>
      </v-col>
      <v-col cols="1">
        <v-icon @click="resetMaxMin">mdi-restore</v-icon>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import googleAPI from "@/api/googleAPI";

export default {
  name: "YoutubeEditTool",
  data() {
    return {
      playerVars: {
        controls: 0,
        mute: 1,
      },
      duration: 0,
      max: 0,
      min: 0,
      range: [0, 0],
      isPlaying: false,
      currentTime: 0,
      interval: {},
      isMuted: true,
    };
  },
  watch: {
    currentTime(newValue) {
      if (newValue === this.range[1]) {
        this.player.seekTo(this.range[0]);
        this.currentTime = this.range[0];
        this.pauseVideo();
      }
    },
  },
  computed: {
    player() {
      return this.$refs[`youtube${this.$route.query.youtubeId}`].player;
    },
    currentTimeBarPosition() {
      const px = (319 / (this.max - this.min)) * (this.currentTime - this.min) - 3;
      return px;
    },
  },
  mounted() {
    this.getYoutubeDuration();
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
  methods: {
    getYoutubeDuration() {
      const API_KEY = process.env.VUE_APP_API_KEY_1;
      const params = {
        key: API_KEY,
        part: "contentDetails",
        id: this.$route.query.youtubeUrl,
      };
      axios
        .get(googleAPI.videos(), { params })
        .then((response) => {
          let duration = response.data.items[0].contentDetails.duration;
          duration = duration.split("").filter((str) => {
            return !isNaN(str);
          });
          const minutes = Number(duration.slice(0, -2).join(""));
          const seconds = Number(duration.slice(-2).join(""));
          duration = minutes * 60 + seconds;
          this.duration = duration;
          this.max = duration;
          this.range = [0, duration];
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onReady() {
      this.playVideo();
    },
    onMouseUp() {
      this.player.seekTo(this.range[0]);
      this.currentTime = this.range[0];
      this.pauseVideo();
    },
    playVideo() {
      this.player.playVideo();
      this.isPlaying = true;
      this.interval = setInterval(this.getCurrentTime, 1000);
    },
    pauseVideo() {
      this.player.pauseVideo();
      this.isPlaying = false;
      clearInterval(this.interval);
    },
    secondToMinute(seconds) {
      const vTime = require("video-time");
      return vTime(seconds);
    },
    getCurrentTime() {
      this.player.getCurrentTime().then((response) => {
        this.currentTime = Math.floor(response);
      });
    },
    cutMaxMin() {
      this.max = this.range[1];
      this.min = this.range[0];
    },
    resetMaxMin() {
      this.max = this.duration;
      this.min = 0;
      this.range = [0, this.duration];
      this.currentTime = 0;
      this.player.seekTo(0);
    },
    mute() {
      if (!this.isMuted) {
        this.isMuted = true;
        this.player.mute();
      } else {
        this.isMuted = false;
        this.player.unMute();
      }
    },
  },
};
</script>

<style scoped>
.position-relative {
  position: relative;
}

.current-time-bar {
  position: absolute;
  top: 185px;
}
</style>

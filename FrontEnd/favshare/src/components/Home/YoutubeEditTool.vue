<template>
  <div>
    <!-- 유튜브 영상 -->
    <youtube
      :video-id="$route.query.youtubeId"
      :player-vars="playerVars"
      :ref="'youtube' + $route.query.youtubePk"
      width="320"
      height="180"
      style="pointer-events: none"
    ></youtube>
    <!-- 시간 조정 슬라이더 -->
    <v-range-slider
      :max="max"
      :min="min"
      v-model="range"
      thumb-label="always"
      ticks
      @mouseup="onMouseUp"
      ><template v-slot:thumb-label="{ value }"
        >{{ secondToMinute(value) }}
      </template>
    </v-range-slider>
    <div>현재 위치: {{ secondToMinute(currentTime) }}</div>
    <v-icon v-if="!isPlaying" @click="playVideo">mdi-play-circle</v-icon>
    <v-icon v-if="isPlaying" @click="pauseVideo">mdi-pause-circle</v-icon>
    <v-icon @click="cutMaxMin">mdi-content-cut</v-icon>
    <v-icon @click="resetMaxMin">mdi-restore</v-icon>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "YoutubeEditTool",
  data() {
    return {
      // youtube 태그 파라미터
      playerVars: {
        controls: 0,
      },
      // 원본 영상 길이
      duration: 0,
      // 현재 표시되는 영상 길이
      max: 0,
      min: 0,
      // range slider가 선택한 시간
      range: [0, 0],
      isPlaying: false,
      // 현재 재생 중인 시간
      currentTime: 0,
      interval: {},
    };
  },
  watch: {
    // 현재 재생위치가 range[1]에 닿으면
    currentTime(newValue) {
      if (newValue === this.range[1]) {
        // 영상이 range[0]으로 가고 정지
        this.player.seekTo(this.range[0]);
        this.currentTime = this.range[0];
        this.pauseVideo();
      }
    },
  },
  computed: {
    player() {
      return this.$refs[`youtube${this.$route.query.youtubePk}`].player;
    },
  },
  mounted() {
    this.getYoutubeDuration();
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
  methods: {
    // 영상의 길이 가져오기
    getYoutubeDuration() {
      const URL = "https://www.googleapis.com/youtube/v3/videos";
      const API_KEY = "AIzaSyDzn6H3ySugFQgEV9RaH0fV4-HBYXRWZ6A";
      const params = {
        key: API_KEY,
        part: "contentDetails",
        id: this.$route.query.youtubeId,
      };
      axios
        .get(URL, { params })
        .then((response) => {
          // 응답으로 받아온 duration은 PT15M51S 형식
          // 해당 문자는 15분 51초를 뜻함
          let duration = response.data.items[0].contentDetails.duration;
          // 초 형태로 변환
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
      if (seconds % 60 < 10) {
        return `${parseInt(seconds / 60)}:0${seconds % 60}`;
      } else {
        return `${parseInt(seconds / 60)}:${seconds % 60}`;
      }
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
  },
};
</script>

<style></style>

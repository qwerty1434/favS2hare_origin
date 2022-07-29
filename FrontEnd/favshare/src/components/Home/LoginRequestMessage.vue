<template>
  <div>
    <h3>로그인이 필요합니다~</h3>
    <h5>FavS2hare의 다양한 서비스를 경험해보세요</h5>
    <router-link to="/signin">
      <button>로그인 하러 가기</button>
    </router-link>
    <br />
    <youtube
      :video-id="videoId"
      :player-vars="playerVars"
      ref="youtube"
      @playing="playing"
      :width="360"
      :height="202"
    >
    </youtube>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "LoginRequestMessage",
  data() {
    return {
      videoId: "f6YDKF0LVWw",
      playerVars: {
        autoplay: 0,
        controls: 0,
      },
    };
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {
      console.log("we are watching!!!");
    },
  },
  created() {
    this.playVideo();
  },
};
</script>

<style></style>

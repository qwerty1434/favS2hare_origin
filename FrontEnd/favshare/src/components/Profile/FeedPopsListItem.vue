<template>
  <div>
    <router-link :to="{ name: 'popsinfeed' }">
      <youtube
        :video-id="videoId"
        :player-vars="playerVars"
        :ref="'pops' + this.feedPops.id"
        @ready="onPlayerReady"
        @playing="onPlaying"
        :width="170"
        :height="96"
        style="pointer-events: none"
      >
      </youtube>
    </router-link>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "FeedPopsListItem",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      },
    };
  },
  props: {
    feedPop: Object, // { id(pops), name, *youtubeUrl(추가필요), startSecond, endSecond, content, createDate, views, likeCount }
  },
  computed: {
    videoId() {
      return this.feedPop.youtubeUrl.slice(-11);
    },
    player() {
      return this.$refs[`pops${this.feedPop.id}`].player;
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

<style></style>

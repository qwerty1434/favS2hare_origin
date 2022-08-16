<template>
  <div>
    <v-card height="550px" color="grey lighten-1" class="pops-list-item">
      <div class="name-font">{{ popItem.name }}</div>
      <div class="video">
        <youtube
          :video-id="youtubePk"
          :player-vars="playerVars"
          :ref="'pops' + this.popItem.id"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="336"
          :height="189"
          style="pointer-events: none; border-radius: 8px"
        ></youtube>
      </div>
      <div>{{ popItem.content }}</div>
    </v-card>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "PopsListItem",
  props: {
    popItem: Object, // {id,name,startSecond,endSecond,content,createdDate,views,likeCount,youtubeUrl,liked}
  },
  data() {
    return {
      isLiked: false,
      dialogComment: false,
      dialogInfo: false,
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      },
      section: {
        start: this.popItem.startSecond,
        end: this.popItem.endSecond,
      },
    };
  },
  computed: {
    player() {
      return this.$refs[`pops${this.popItem.id}`].player;
    },
  },
  methods: {
    likePops() {
      if (!this.isLiked) {
        this.isLiked = true;
      } else {
        this.isLiked = false;
      }
    },
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
.pops-list-item {
  margin-bottom: 10px;
  text-align: center;
}

.video {
  margin-top: 60px;
}

.name-font {
  font-family: "EF_WAKEUP" !important;
  text-align: center;
  font-size: 40px;
  padding-top: 60px;
}
@font-face {
  font-family: "EF_WAKEUP";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/EF_WAKEUP.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
</style>

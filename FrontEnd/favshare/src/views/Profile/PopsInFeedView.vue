<template>
  <div class="cover">
    <v-sheet
      rounded="rounded"
      color="grey lighten-3"
      width="340"
      height="724"
      class="sheet"
    >
      <div class="arrow" @click="goBack">
        <v-icon>mdi-arrow-left-thick</v-icon>
      </div>
      <div class="close">
        <v-icon>mdi-close-thick</v-icon>
      </div>
      <div class="video">
        <youtube
          :video-id="feedPop.youtubeUrl"
          :player-vars="playerVars"
          :ref="'pops' + this.feedPop.id"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="340"
          :height="191.25"
          style="pointer-events: none; border-radius: 16px"
        >
        </youtube>
      </div>
      <div class="video-info">
        <div class="user-pic">
          <v-avatar>
            <img :src="feedUserInfo.userPicture" alt="" />
          </v-avatar>
          <div>{{ feedUserInfo.nickname }}</div>
        </div>
        <div>{{ feedPop.content }}</div>
      </div>
    </v-sheet>
    <bottom-navigation-bar></bottom-navigation-bar>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapGetters } from "vuex";
import BottomNavigationBar from "../../components/BottomNavigationBar.vue";

Vue.use(VueYoutube);

export default {
  components: { BottomNavigationBar },
  name: "PopsInFeedView",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      },
      section: {
        start: this.feedPop.startSecond,
        end: this.feedPop.endSecond,
      },
    };
  },
  computed: {
    player() {
      return this.$refs[`pops${this.feedPop.id}`].player;
    },
    ...mapGetters(["feedUserInfo", "feedPop"]),
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
    goBack() {
      history.back();
    },
  },
};
</script>

<style scoped>
.cover {
  text-align: center;
}

.sheet {
  margin: auto;
  margin-top: 10px;
}

.arrow {
  position: absolute;
  left: 15px;
  top: 15px;
}

.close {
  position: absolute;
  right: 15px;
  top: 15px;
}

.video {
  position: absolute;
  top: 60px;
}

.video-info {
  display: flex;
  position: absolute;
  top: 280px;
  left: 15px;
}
</style>

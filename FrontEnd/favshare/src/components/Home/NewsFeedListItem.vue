<template>
  <div>
    <v-sheet class="video" elevation="2" rounded="xl">
      <div class="video__thumbnail">
        <youtube
          :video-id="youtubePk"
          :player-vars="playerVars"
          :ref="'pops' + this.newsFeedPop.popsId"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="320"
          :height="180"
          style="pointer-events: none; border-radius: 8px"
        ></youtube>
      </div>
      <div class="video__details">
        <div
          class="author"
          @click="
            [
              routerPushes('feed'),
              fetchFeedUserInfo(newsFeedPop.userProfileDto.id),
              fetchFeedList(newsFeedPop.userProfileDto.id),
              fetchIsFollowing(newsFeedPop.userProfileDto.id),
            ]
          "
        >
          <img :src="this.newsFeedPop.userProfileDto.profileImageUrl" alt="" />
        </div>
        <div class="title">
          <h3>
            {{ this.newsFeedPop.userProfileDto.nickname }}
          </h3>
          <div class="pop-name">{{ newsFeedPop.popDto.name }}</div>
        </div>
      </div>
    </v-sheet>
  </div>
</template>

<script>
// 이 파일은 feedPop -> newsFeedPop 으로 다 바꿔서 해보면 됩니다.
import router from "@/router";
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapActions } from "vuex";

Vue.use(VueYoutube);

export default {
  name: "NewsFeedListItem",
  props: {
    newsFeedPop: Object,
    // newsFeedPop: Object, // {}
  },
  data() {
    return {
      tmp: String,
      playerVars: {
        autoplay: 1,
        mute: 1,
        contros: 0,
        disablekb: 1,
      },
      // tmp data
      section: {
        start: this.newsFeedPop.popDto.startSecond,
        end: this.newsFeedPop.popDto.endSecond,
      },
    };
  },
  computed: {
    youtubePk() {
      return this.newsFeedPop.popDto.youtubeUrl;
    },
    player() {
      return this.$refs[`pops${this.newsFeedPop.popsId}`].player;
    },
  },
  methods: {
    ...mapActions(["fetchFeedUserInfo", "fetchFeedList", "fetchIsFollowing"]),
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
    printFeed() {
      console.log("herer");
      console.log(this.newsFeedPop);
    },
    routerPushes(icon) {
      router.push({ name: icon });
    },
  },
  created() {
    // this.playVideo();
    this.printFeed();
  },
};
</script>

<style scoped>
.video {
  width: 320px;
  margin-bottom: 10px;
}

.video__thumbnail {
  width: 100%;
  height: 180px;
  border-radius: 30%;
  object-fit: cover;
  margin: auto;
}

.video__thumbnail img {
  object-fit: cover;
  height: 100%;
  width: 100%;
}

.video__details {
  display: flex;
  padding-top: 10px;
  padding-left: 10px;
}

.author img {
  object-fit: cover;
  border-radius: 50%;
  height: 30px;
  width: 30px;
  margin-right: 10px;
}

.title {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.title h3 {
  color: rgb(3, 3, 3);
  line-height: 18px;
  font-size: 14px;
  margin-bottom: 6px;
}

.pop-name {
  padding-left: 30px;
  font-family: "Bingrae", sans-serif !important;
  text-align: center;
  font-size: 20px;
}
@font-face {
  font-family: "Bingrae";
  src: url("@/assets/fonts/Binggrae-Bold.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}
</style>

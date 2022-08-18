<template>
  <!-- eslint-disable -->
  <div>
    <v-sheet
      class="video"
      elevation="2"
      style="border-bottom-left-radius: 8px; border-bottom-right-radius: 8px"
    >
      <div class="video__thumbnail" @click="goPopsDetail">
        <youtube
          :video-id="youtubePk"
          :player-vars="playerVars"
          :ref="'pops' + this.newsFeedPop.popDto.id"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="320"
          :height="180"
          style="
            pointer-events: none;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
          "
        ></youtube>
      </div>
      <div class="pop-name"></div>
      <div class="video__details row-title">
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
        <div class="row-title">
          <v-row class="">
            <v-col>
              <h3>
                {{ newsFeedPop.popDto.name }}
              </h3>
            </v-col>
          </v-row>
          <v-row class="mt-n5 row-title">
            <v-col cols="7">
              <h5 class="grey--text">
                {{ this.newsFeedPop.userProfileDto.nickname }}
              </h5>
            </v-col>
            <v-col>
              <h4>
                <v-icon small> mdi-eye </v-icon>
                {{ newsFeedPop.popDto.views }}&nbsp;&nbsp;
                <v-icon small> mdi-heart </v-icon>
                {{ newsFeedPop.popDto.likeCount }}
              </h4>
            </v-col>
          </v-row>
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
        controls: 0,
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
      return this.$refs[`pops${this.newsFeedPop.popDto.id}`].player;
    },
  },
  methods: {
    ...mapActions(["fetchFeedUserInfo", "fetchFeedList", "fetchIsFollowing"]),
    goPopsDetail() {
      this.$router.push({
        name: "popsdetail",
        params: {
          popsId: this.newsFeedPop.popDto.id,
          editorId: this.newsFeedPop.popDto.userId,
        },
      });
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
  height: 250px;
  width: 320px;
  margin-top: -10px;
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
  border-radius: 10%;
}

.video__details {
  display: flex;
  padding-top: 10px;
  padding-left: 15px;
}

.author img {
  object-fit: cover;
  border-radius: 50%;
  height: 50px;
  width: 50px;
  margin-right: 10px;
}

.title {
  display: flex;
  flex-direction: row;
}

.row-title {
  width: 260px;
}

.pop-name {
  padding-left: 10px;
  font-size: 20px;
}
</style>

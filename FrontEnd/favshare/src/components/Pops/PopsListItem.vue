<template>
  <!-- eslint-disable -->
  <div>
    <v-card height="550px" color="black" class="pops-list-item" @click="goPopsDetail">
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
          style="pointer-events: none"
        ></youtube>
      </div>
      <div class="content-font">{{ popItem.content }}</div>
      <v-row class="pa-5 mt-100" justify="end">
        <v-spacer></v-spacer>
        <v-btn class="mt-2" text icon color="white" v-if="!isLiked" @click="btnLikePops">
          <v-icon>mdi-heart-outline</v-icon>
        </v-btn>
        <v-btn class="mt-2" text icon color="red" v-else @click="btnLikePops">
          <v-icon>mdi-heart</v-icon>
        </v-btn>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapActions, mapGetters } from "vuex";

Vue.use(VueYoutube);

export default {
  name: "PopsListItem",
  props: {
    popItem: Object, // {id,name,startSecond,endSecond,content,createdDate,views,likeCount,youtubeUrl,liked}
  },
  data() {
    return {
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
    ...mapGetters(["userId", "isLiked"]),
    player() {
      return this.$refs[`pops${this.popItem.id}`].player;
    },
    youtubePk() {
      return this.popItem.youtubueUrl;
    },
  },
  methods: {
    ...mapActions(["likePops", "unLikePops"]),
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
    goPopsDetail() {
      console.log(this.popItem.userId);
      this.$router.push({
        name: "popsdetail",
        params: {
          popsId: this.popItem.id,
          editorId: this.popItem.userId,
        },
      });
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
  color: aliceblue;
  font-family: "EF_WAKEUP" !important;
  text-align: center;
  font-size: 40px;
  padding-top: 60px;
  color: white;
}
.content {
  font-family: "EF_WAKEUP" !important;
  color: white;
}
@font-face {
  font-family: "EF_WAKEUP";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/EF_WAKEUP.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}

.content-font {
  color: aliceblue;
}
.mt-100 {
  margin-top: 80px;
}
</style>

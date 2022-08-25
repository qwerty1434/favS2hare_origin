<template>
  <div class="pops-all">
    <div v-if="!isDelete" style="position: relative; padding-left: 68px">
      <v-btn
        fab
        x-small
        color="amber accent-1"
        absolute
        @click="[deleteFeedPop(), spliceFeedPops(index)]"
      >
        <v-icon>mdi-minus-circle-outline</v-icon>
      </v-btn>
    </div>
    <div class="feed-pops-item" @click="goPopsDetail">
      <youtube
        :video-id="feedPop.youtubeUrl"
        :player-vars="playerVars"
        :ref="'pops' + this.feedPop.id"
        @ready="onPlayerReady"
        @playing="onPlaying"
        :width="164"
        :height="92.25"
        style="pointer-events: none; border-radius: 8px"
      >
      </youtube>
    </div>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapActions, mapGetters } from "vuex";
import axios from "axios";
import router from "@/router";
import api from "@/api/springRestAPI";

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
      section: {
        start: this.feedPop.startSecond,
        end: this.feedPop.endSecond,
      },
      isSelected: false,
    };
  },
  props: {
    feedPop: Object,
    index: Number,
  },
  computed: {
    player() {
      return this.$refs[`pops${this.feedPop.id}`].player;
    },
    ...mapGetters(["isDelete", "feedPops", "currentFeedId"]),
  },
  methods: {
    ...mapActions(["spliceFeedPops", "fetchFeedPop"]),
    goPopsDetail() {
      this.$router.push({
        name: "popsdetail",
        params: {
          popsId: this.feedPop.id,
          editorId: this.feedPop.userId,
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
    setIsSelected() {
      if (this.isSelected) {
        this.isSelected = false;
      } else {
        this.isSelected = true;
      }
    },
    deleteFeedPop() {
      axios({
        method: "delete",
        url: api.feed.pop(),
        data: {
          feedId: this.currentFeedId,
          popId: [this.feedPop.id],
        },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((res) => {
          console.log(res);
        });
    },
    routerPush(name) {
      this.fetchFeedPop(this.feedPop);
      router.push({ name: name });
    },
  },
  watch: {
    "$store.state.profile.isDelete": function () {
      console.log(this.$store.state.profile.isDelete);
    },
    "$store.state.profile.feedPops": function () {
      console.log(this.$store.state.profile.feedPops);
    },
  },
};
</script>

<style scoped>
/* .pops-all {
  display: flex;
  text-align: center;
  justify-content: center;
  width: 164px;
  height: 92.25px;
} */
/* .delete-button {
  left: 50px;
} */

.feed-pops-item {
  margin: auto;
  padding-left: 2px;
}
</style>

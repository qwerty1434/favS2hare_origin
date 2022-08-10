<template>
  <div>
    <div v-if="!isDelete" class="delete-button">
      <v-btn fab x-small @click="[deleteFeedPop, spliceFeedPops(feedPop.id)]">
        <v-icon>mdi-minus-circle-outline</v-icon>
      </v-btn>
    </div>
    <div class="feed-pops-item" @click="routerPush('popsinfeed')">
      <youtube
        :video-id="feedPop.youtubeUrl"
        :player-vars="playerVars"
        :ref="'pops' + this.feedPop.id"
        @ready="onPlayerReady"
        @playing="onPlaying"
        :width="164"
        :height="92.25"
        style="pointer-events: none; border-radius: 16px"
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
    feedPop: Object, // { id(pops), name, *youtubeUrl(추가필요), startSecond, endSecond, content, createDate, views, likeCount }
  },
  computed: {
    player() {
      return this.$refs[`pops${this.feedPop.id}`].player;
    },
    ...mapGetters(["isDelete", "feedPops"]),
  },
  methods: {
    ...mapActions(["spliceFeedPops"]),
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
        url: "http://localhost:8080/feed/pop",
        data: {
          popsId: this.feedPop.id,
        },
      });
    },
    routerPush(name) {
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
.delete-button {
  left: 10px;
  top: 10px;
}

.feed-pops-item {
  margin: auto;
  padding-left: 2px;
}
</style>

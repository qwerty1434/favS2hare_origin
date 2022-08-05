<template>
  <div>
    <div v-if="!isDelete" class="delete-button">
      <v-btn fab x-small @click="[deleteFeedPop, spliceFeedPops(feedPop.id)]">
        <v-icon>mdi-minus-circle-outline</v-icon>
      </v-btn>
    </div>
    <div class="feed-pops-item">
      <youtube
        :video-id="videoId"
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
    <!-- <router-link :to="{ name: 'popsinfeed' }">
      <youtube
        :video-id="videoId"
        :player-vars="playerVars"
        :ref="'pops' + this.feedPops.id"
        @ready="onPlayerReady"
        @playing="onPlaying"
        :width="170"
        :height="96"
        style="pointer-events: none; border-radius: 4px"
      >
      </youtube>
    </router-link> -->
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapActions, mapGetters } from "vuex";
import axios from "axios";

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
    videoId() {
      return this.feedPop.youtubeUrl.slice(-11);
    },
    player() {
      return this.$refs[`pops${this.feedPop.id}`].player;
    },
    ...mapGetters(["isDelete"]),
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
      });
    },
  },
  watch: {
    "$store.state.profile.isDelete": function () {
      console.log(this.$store.state.profile.isDelete);
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

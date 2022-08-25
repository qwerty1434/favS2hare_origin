<template>
  <div>
    <v-card class="pops-size" @click="goPopsDetail">
      <div>
        <youtube
          :video-id="searchedPopsListItem.youtubeUrl"
          :player-vars="playerVars"
          :ref="'pops' + this.searchedPopsListItem.id"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="150"
          :height="167"
          style="
            pointer-events: none;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
          "
        ></youtube>
      </div>
      <v-card-actions class="mb-n3">
        <h5>
          {{ searchedPopsListItem.name }}
        </h5>
      </v-card-actions>
      <v-card-actions class="ma-1">
        <h5 class="grey--text">
          {{ searchedPopsListItem.id }}
        </h5>
        <v-spacer></v-spacer>
        <h6>
          <v-icon x-small> mdi-eye </v-icon>
          {{ searchedPopsListItem.views }}&nbsp;&nbsp;
          <v-icon x-small> mdi-heart </v-icon>
          {{ searchedPopsListItem.likeCount }}
        </h6>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "SearchMorePopsListItem",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      },
      section: {
        start: this.searchedPopsListItem.startSecond,
        end: this.searchedPopsListItem.endSecond,
      },
    };
  },
  created() {},
  props: {
    searchedPopsListItem: Object,
  },
  computed: {
    player() {
      return this.$refs[`pops${this.searchedPopsListItem.id}`].player;
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
    goPopsDetail() {
      this.$router.push({
        name: "popsdetail",
        params: {
          popsId: this.searchedPopsListItem.id,
          editorId: this.searchedPopsListItem.userId,
        },
      });
    },
  },
};
</script>
<style scoped>
.pops-size {
  height: 230px;
  width: 150px;
}
</style>

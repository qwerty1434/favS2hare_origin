<template>
  <!-- eslint-disable -->
  <div>
    <v-card class="pops-size ml-2 mr-2" @click="goPopsDetail">
      <div>
        <youtube
          :video-id="popsListItem.youtubeUrl"
          :player-vars="playerVars"
          :ref="'pops' + popsListItem.id"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="150"
          :height="167"
          style="pointer-events: none; border-top-left-radius: 8px; border-top-right-radius: 8px"
        ></youtube>
      </div>
      <v-card-actions class="mt-n2 mb-n2">
        <h5>
          {{ popsListItem.name }}
        </h5>
      </v-card-actions>
      <v-card-actions class="ma-1">
        <h5 class="grey--text">
          {{ popsListItem.id }}
        </h5>
        <v-spacer></v-spacer>
        <h6>
          <v-icon x-small> mdi-eye </v-icon>
          {{ popsListItem.views }}&nbsp;&nbsp;
          <v-icon x-small> mdi-heart </v-icon>
          {{ popsListItem.likeCount }}
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
  name: "SearchPopsListItem",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      },
      section: {
        start: this.popsListItem.startSecond,
        end: this.popsListItem.endSecond,
      },
    };
  },
  props: {
    popsListItem: Object,
  },
  created() {
    console.log(this.popsListItem);
  },
  computed: {
    player() {
      return this.$refs[`pops${this.popsListItem.id}`].player;
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
      console.log(this.popsListItem.userId);
      this.$router.push({
        name: "popsdetail",
        params: {
          popsId: this.popsListItem.id,
          editorId: this.popsListItem.userId,
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

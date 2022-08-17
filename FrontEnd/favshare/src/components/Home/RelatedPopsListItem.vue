<template>
  <!-- eslint-disable -->
  <div>
    <v-card class="related-size" @click="goPopsDetail">
      <div class="align-end">
        <youtube
          :video-id="url"
          :player-vars="playerVars"
          ref="youtube"
          :width="150"
          :height="167"
          style="pointer-events: none; border-top-left-radius: 8px; border-top-right-radius: 8px"
        ></youtube>
      </div>
      <v-card-actions class="mt-n2 mb-n2">
        <h5>
          {{ popsItem.name }}
        </h5>
      </v-card-actions>
      <v-card-actions class="ma-1">
        <h5 class="grey--text">
          {{ popsItem.userId }}
        </h5>
        <v-spacer></v-spacer>
        <h6>
          <v-icon x-small> mdi-eye </v-icon>
          {{ popsItem.views }}&nbsp;&nbsp;
          <v-icon x-small> mdi-heart </v-icon>
          {{ popsItem.likeCount }}
        </h6>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
// @playing="playing"
// style="pointer-events: none"
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "RelatedPopsListItem",
  props: {
    popsItem: Object,
    url: String,
  },
  data() {
    return {
      playerVars: {
        autoplay: 1,
        controls: 0,
        mute: 1,
      },
    };
  },
  methods: {
    playVideo() {
      console.log(this.url);
      this.player.playVideo();
    },
    playing() {
      console.log("playing");
    },
    goPopsDetail() {
      console.log(this.popsItem.userId);
      this.$router.push({
        name: "popsdetail",
        params: {
          popsId: this.popsItem.id,
          editorId: this.popsItem.userId,
        },
      });
    },
  },
  created() {
    this.playVideo();
  },
};
</script>

<style scoped>
.related-size {
  height: 230px;
  width: 150px;
}
</style>

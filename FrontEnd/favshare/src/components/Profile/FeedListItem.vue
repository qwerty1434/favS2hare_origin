<template>
  <div class="feed-item">
    <v-avatar
      size="70"
      @click="fetchFeedPops(feedInfo.id), fetchCurrentFeedId(feedInfo.id)"
    >
      <img :src="feedInfo.feedImageUrl" alt="" />
    </v-avatar>
    <div class="feed-name">{{ feedInfo.name }}</div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "FeedListItem",
  props: {
    feedInfo: Object, // {id(pops), name, feedImageUrl, userId, first}
  },
  methods: {
    ...mapActions(["fetchFeedPops", "fetchCurrentFeedId"]),
  },
};
</script>

<style scoped>
.feed-item {
  text-align: center;
}
.feed-name {
  font-family: "MabinogiClassicR" !important;
  font-size: medium;
}
@font-face {
  font-family: "MabinogiClassicR";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2207-01@1.0/MabinogiClassicR.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
</style>

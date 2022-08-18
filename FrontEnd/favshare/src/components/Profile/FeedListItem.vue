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
  font-size: medium;
}
</style>

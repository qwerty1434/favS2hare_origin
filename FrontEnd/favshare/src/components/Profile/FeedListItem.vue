<template>
  <div class="feed-item ml-1 mr-1">
    <div v-if="feedInfo.first">
      <v-badge icon="mdi-star" bordered overlap avatar color="red lighten-1">
        <v-avatar size="60" @click="fetchFeedPops(feedInfo.id), fetchCurrentFeedId(feedInfo.id)">
          <img :src="feedInfo.feedImageUrl" alt="" />
        </v-avatar>
      </v-badge>
    </div>
    <div v-else>
      <v-avatar size="60" @click="fetchFeedPops(feedInfo.id), fetchCurrentFeedId(feedInfo.id)">
        <img :src="feedInfo.feedImageUrl" alt="" />
      </v-avatar>
    </div>
    <div v-if="feedInfo.first"></div>
    <div class="mt-2 feed-name">{{ feedInfo.name }}</div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "FeedListItem",
  props: {
    feedInfo: Object,
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
  font-size: 15px;
}
</style>

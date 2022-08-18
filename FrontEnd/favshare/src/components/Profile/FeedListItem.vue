<template>
  <div class="feed-item">
    <div v-if="feedInfo.first">
      <v-badge icon="mdi-star" bordered overlap avatar color="red lighten-1">
        <v-avatar
          size="70"
          @click="fetchFeedPops(feedInfo.id), fetchCurrentFeedId(feedInfo.id)"
        >
          <img :src="feedInfo.feedImageUrl" alt="" />
        </v-avatar>
      </v-badge>
    </div>
    <div v-else>
      <v-avatar
        size="70"
        @click="fetchFeedPops(feedInfo.id), fetchCurrentFeedId(feedInfo.id)"
      >
        <img :src="feedInfo.feedImageUrl" alt="" />
      </v-avatar>
    </div>
    <!-- <v-avatar
      size="70"
      @click="fetchFeedPops(feedInfo.id), fetchCurrentFeedId(feedInfo.id)"
    >
      <img :src="feedInfo.feedImageUrl" alt="" />
    </v-avatar> -->
    <div v-if="feedInfo.first"></div>
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

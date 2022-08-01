<template>
  <div>
    <v-avatar @click="this.setShowFeedId(this.feedInfo.id)">
      <img :src="feedInfo.feedImageUrl" alt="" />
    </v-avatar>
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
    ...mapActions(["setShowFeedId"]),
  },
};
</script>

<style></style>

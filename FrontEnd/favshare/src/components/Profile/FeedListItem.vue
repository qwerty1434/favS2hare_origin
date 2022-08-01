<template>
  <div>
    <v-avatar>
      <img :src="feedInfo.feedImageUrl" alt="" />
    </v-avatar>
  </div>
</template>

<script>
export default {
  name: "FeedListItem",
  props: {
    feedInfo: Object, // {id(pops), name, feedImageUrl, userId, first}
  },
};
</script>

<style></style>

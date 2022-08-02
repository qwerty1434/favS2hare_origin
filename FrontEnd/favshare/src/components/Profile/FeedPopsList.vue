<template>
  <div>
    <div v-for="feedPop in feedPops" :key="feedPop.id">
      <feed-pops-list-item :feed-pop="feedPop"></feed-pops-list-item>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import FeedPopsListItem from "../Home/FeedPopsListItem.vue";

export default {
  components: { FeedPopsListItem },
  name: "FeedPopsList",
  computed: {
    ...mapGetters(["feedPops"]), // { id(pops), name, *youtubeUrl(추가필요), startSecond, endSecond, content, createDate, views, likeCount }
  },
};
</script>

<style></style>

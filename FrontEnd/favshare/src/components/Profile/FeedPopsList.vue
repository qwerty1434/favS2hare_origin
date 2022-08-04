<template>
  <div>
    <div v-for="feedPop in feedPops" :key="feedPop.id">
      <feed-pops-list-item :feed-pop="feedPop"></feed-pops-list-item>
    </div>
  </div>
</template>

<script>
import FeedPopsListItem from "./FeedPopsListItem.vue";
// import { mapGetters } from "vuex";

export default {
  data() {
    return {
      feedPops: Array,
    };
  },
  name: "FeedPopsList",
  // computed: {
  //   ...mapGetters(["feedPops"]), // { id(pops), name, *youtubeUrl(추가필요), startSecond, endSecond, content, createDate, views, likeCount }
  // },
  methods: {
    tmpFeedPops() {
      this.feedPops = [
        {
          id: 1,
          youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
          startSecond: 7,
          endSecond: 10,
        },
        {
          id: 2,
          youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
          startSecond: 41,
          endSecond: 43,
        },
        {
          id: 3,
          youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
          startSecond: 54,
          endSecond: 56,
        },
        {
          id: 4,
          youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
          startSecond: 103,
          endSecond: 104,
        },
        {
          id: 5,
          youtubeUrl: "https://www.youtube.com/watch?v=l3-kz1x3MWc",
          startSecond: 57,
          endSecond: 61,
        },
        {
          id: 6,
          youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
          startSecond: 158,
          endSecond: 160,
        },
      ];
    },
  },
  created() {
    this.tmpFeedPops();
  },
  components: { FeedPopsListItem },
};
</script>

<style></style>

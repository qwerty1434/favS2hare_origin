<template>
  <!-- <div class="feed-pops">
    <div v-for="feedPop in feedPops" :key="feedPop.id">
      <feed-pops-list-item :feed-pop="feedPop"></feed-pops-list-item>
    </div>
  </div> -->
  <v-container>
    <v-row justify="space-around" no-gutters>
      <v-col v-for="feedPop in feedPops" :key="feedPop.id" col="6">
        <feed-pops-list-item :feed-pop="feedPop"></feed-pops-list-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import FeedPopsListItem from "./FeedPopsListItem.vue";
import { mapGetters } from "vuex";

export default {
  components: { FeedPopsListItem },
  // data() {
  //   return {
  //     feedPops: Array,
  //   };
  // },
  name: "FeedPopsList",
  computed: {
    ...mapGetters(["feedPops"]), // { id(pops), name, *youtubeUrl(추가필요), startSecond, endSecond, content, createDate, views, likeCount }
  },
  // methods: {
  //   tmpFeedPops() {
  //     this.feedPops = [
  //       {
  //         id: 1,
  //         youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
  //         startSecond: 7,
  //         endSecond: 10,
  //       },
  //       {
  //         id: 2,
  //         youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
  //         startSecond: 41,
  //         endSecond: 43,
  //       },
  //       {
  //         id: 3,
  //         youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
  //         startSecond: 54,
  //         endSecond: 56,
  //       },
  //       {
  //         id: 4,
  //         youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
  //         startSecond: 103,
  //         endSecond: 104,
  //       },
  //       {
  //         id: 5,
  //         youtubeUrl: "https://www.youtube.com/watch?v=l3-kz1x3MWc",
  //         startSecond: 57,
  //         endSecond: 61,
  //       },
  //       {
  //         id: 6,
  //         youtubeUrl: "https://www.youtube.com/watch?v=TjAg-8qqR3g",
  //         startSecond: 158,
  //         endSecond: 160,
  //       },
  //     ];
  //   },
  // },
  // created() {
  //   this.tmpFeedPops();
  // },
  watch: {
    "$store.state.profile.feedPops": function () {
      console.log(this.$store.state.profile.feedPops);
    },
  },
};
</script>

<style>
/* .feed-pops {
  display: flex;
} */
</style>

<template>
  <v-container>
    <v-row justify="space-around" no-gutters>
      <v-col v-for="(feedPop, index) in feedPops" :key="index" col="6">
        <feed-pops-list-item
          :feed-pop="feedPop"
          :index="index"
        ></feed-pops-list-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import FeedPopsListItem from "./FeedPopsListItem.vue";
import { mapGetters } from "vuex";

export default {
  components: { FeedPopsListItem },
  name: "FeedPopsList",
  computed: {
    ...mapGetters(["feedPops"]), // { id(pops), name, *youtubeUrl(추가필요), startSecond, endSecond, content, createDate, views, likeCount }
  },
  watch: {
    "$store.state.profile.feedPops": function () {
      console.log(this.$store.state.profile.feedPops);
    },
  },
};
</script>

<style></style>

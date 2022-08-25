<template>
  <div class="mb-15">
    <div
      v-for="(newsFeedPop, index) in newsFeedPops"
      :key="index"
      class="videos__container"
    >
      <news-feed-list-item :news-feed-pop="newsFeedPop"></news-feed-list-item>
    </div>
  </div>
</template>

<script>
import NewsFeedListItem from "./NewsFeedListItem.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  components: { NewsFeedListItem },
  name: "NewsFeedList",
  data() {
    return {
      feedPops: Array,
    };
  },
  computed: {
    ...mapGetters(["newsFeedPops"]),
  },
  methods: {
    ...mapActions(["fetchFriendFeedPops"]),
  },
  created() {
    this.fetchFriendFeedPops();
  },
};
</script>

<style scoped>
.videos {
  background-color: #f9f9f9;
  width: 100%;
  height: 100%;
  padding: 15px 15px;
  border-top: 1px solid #ddd;
  overflow-y: scroll;
}

.videos__container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  flex-wrap: wrap;
  padding: 15px;
}
</style>

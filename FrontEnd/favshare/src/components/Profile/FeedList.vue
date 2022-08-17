<template>
  <div>
    <!-- <v-divider></v-divider>
    <div>
      <v-icon @click="fetchIsDelete(false)">mdi-video-input-component</v-icon>
    </div> -->
    <div v-for="feedInfo in feedList" :key="feedInfo.id" class="popsitem">
      <feed-list-item :feed-info="feedInfo"></feed-list-item>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import FeedListItem from "./FeedListItem.vue";

export default {
  components: { FeedListItem },
  name: "FeedList",
  computed: {
    ...mapGetters(["isDelete", "feedList"]),
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["fetchIsDelete"]),
    // deleteFeedPops() {
    //   axios({
    //     method: "delete",
    //     url: "http://13.124.112.241:8080/feed/pop",
    //   }).then((res) => {
    //     console.log(res);
    //   });
    // },
  },
  watch: {
    "$store.state.profile.isDelete": function () {
      console.log(this.$store.state.profile.isDelete);
    },
    "$store.state.profile.feedList": function () {
      console.log(this.$store.state.profile.feedList);
    },
  },
};
</script>

<style scoped>
.popsitem {
  padding-right: 10px;
}
</style>

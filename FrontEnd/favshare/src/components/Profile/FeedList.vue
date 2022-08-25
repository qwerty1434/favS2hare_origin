<template>
  <div>
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
  padding-right: 5px;
  padding-top: 23px;
}
</style>

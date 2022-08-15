<template>
  <!-- eslint-disable -->
  <v-container>
    <div class="mt-6"></div>
    <div v-for="searchedFollowingListItem in searchedFollowingList">
      <search-more-following-list-item
        :searched-following-list-item="searchedFollowingListItem"
      ></search-more-following-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchMoreFollowingListItem from "./SearchMoreFollowingListItem.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "SearchMoreFollowingList",
  components: { SearchMoreFollowingListItem },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["searchedFollowingList"]),
  },
  methods: {
    ...mapActions([]),
  },
};
</script>
<style></style>

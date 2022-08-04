<template>
  <v-container>
    <div>
      <search-more-following-list-item></search-more-following-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchMoreFollowingListItem from "./SearchMoreFollowingListItem.vue";

export default {
  name: "SearchMoreFollowingList",
  components: { SearchMoreFollowingListItem },
  data() {
    return {
      searchedFollowingList: [],
    };
  },
};
</script>
<style></style>

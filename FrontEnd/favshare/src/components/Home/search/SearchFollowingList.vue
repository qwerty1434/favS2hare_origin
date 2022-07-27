<template>
  <v-container>
    <div>
      <search-following-list-item></search-following-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchFollowingListItem from "./SearchFollowingListItem.vue";

export default {
  name: "SearchFollowingList",
  components: { SearchFollowingListItem },
};
</script>
<style></style>

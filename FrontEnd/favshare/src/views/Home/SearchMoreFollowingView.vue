<template>
  <div>
    <div>
      <v-field></v-field>
    </div>
    <search-following-list-item></search-following-list-item>
  </div>
</template>

<script>
import SearchFollowingListItem from "@/components/Home/search/SearchFollowingListItem.vue";

export default {
  name: "SearchMoreFollowingView",
  components: { SearchFollowingListItem },
};
</script>

<style></style>

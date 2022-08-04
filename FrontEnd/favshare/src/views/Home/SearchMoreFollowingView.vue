<template>
  <div>
    <div>
      <v-field></v-field>
    </div>
    <div>
      <search-following-list-item></search-following-list-item>
    </div>
    <div>
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import SearchFollowingListItem from "@/components/Home/search/SearchFollowingListItem.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "SearchMoreFollowingView",
  components: { SearchFollowingListItem, BottomNavigationBar },
};
</script>

<style></style>

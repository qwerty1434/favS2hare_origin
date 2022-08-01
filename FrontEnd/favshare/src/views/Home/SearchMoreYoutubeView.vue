<template>
  <div>
    <div>
      <search-video-list-item></search-video-list-item>
    </div>
    <div>
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import SearchVideoListItem from "@/components/Home/search/SearchVideoListItem.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "SearchMoreYoutubeView",
  components: { SearchVideoListItem, BottomNavigationBar },
};
</script>

<style></style>

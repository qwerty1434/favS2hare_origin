<template>
  <div>
    <div>
      <search-more-pops-list></search-more-pops-list>
    </div>
    <div>
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import SearchMorePopsList from "@/components/Home/search/SearchMorePopsList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "SearchMorePopsView",
  components: { SearchMorePopsList, BottomNavigationBar },
};
</script>

<style></style>

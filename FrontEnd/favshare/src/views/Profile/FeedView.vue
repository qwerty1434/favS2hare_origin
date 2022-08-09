<template>
  <div>
    <nav-bar></nav-bar>
    <feed-info class="feed-view"></feed-info>
    <feed-list class="rpops"></feed-list>
    <feed-pops-list></feed-pops-list>
    <bottom-navigation-bar></bottom-navigation-bar>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";

import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import FeedPopsList from "@/components/Profile/FeedPopsList.vue";
import FeedInfo from "@/components/Profile/FeedInfo.vue";
import FeedList from "@/components/Profile/FeedList.vue";

export default {
  components: { NavBar, BottomNavigationBar, FeedPopsList, FeedInfo, FeedList },
  name: "FeedView",
};
</script>

<style>
.feed-view {
  padding-top: 20px;
  padding-left: 40px;
}

.rpops {
  width: 360px;
  padding: 20px;
  overflow: scroll;
  overflow: auto;
  white-space: nowrap;
  display: flex;
}
</style>

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
import { mapActions, mapGetters } from "vuex";

import NavBar from "@/components/NavBar.vue";

import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import FeedPopsList from "@/components/Profile/FeedPopsList.vue";
import FeedInfo from "@/components/Profile/FeedInfo.vue";
import FeedList from "@/components/Profile/FeedList.vue";

export default {
  components: { NavBar, BottomNavigationBar, FeedPopsList, FeedInfo, FeedList },
  name: "FeedView",
  computed: {
    ...mapGetters(["feedUserInfo"]), // {id(유저), nickname, count, followerNum, followingNum, userPicture, feedsList: [{id(피드), name, feedImageUrl, userId, first}]}
  },
  methods: {
    ...mapActions(["fetchFeedUserInfo"]),
    // feeds id로 내용물 찾는 api 찾아야함
  },
  // created() {
  //   this.fetchUserInfo();
  // },
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

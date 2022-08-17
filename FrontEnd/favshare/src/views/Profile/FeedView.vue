<template>
  <div>
    <div v-if="isSignin">
      <feed-info class="feed-view"></feed-info>
      <nav-bar></nav-bar>
      <v-divider></v-divider>
      <feed-list class="rpops"></feed-list>
      <v-divider></v-divider>
      <feed-pops-list></feed-pops-list>
    </div>
    <div v-else>
      <login-request-message></login-request-message>
    </div>
    <bottom-navigation-bar></bottom-navigation-bar>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import { mapGetters } from "vuex";
import LoginRequestMessage from "@/components/Home/LoginRequestMessage.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import FeedPopsList from "@/components/Profile/FeedPopsList.vue";
import FeedInfo from "@/components/Profile/FeedInfo.vue";
import FeedList from "@/components/Profile/FeedList.vue";

export default {
  components: {
    NavBar,
    BottomNavigationBar,
    FeedPopsList,
    FeedInfo,
    FeedList,
    LoginRequestMessage,
  },
  name: "FeedView",
  computed: {
    ...mapGetters(["isSignin"]),
  },
};
</script>

<style>
.feed-view {
  padding-top: 50px;
  padding-left: 40px;
}

.rpops {
  width: 360px;
  padding-top: 40px;
  padding: 20px;
  overflow: scroll;
  overflow: auto;
  white-space: nowrap;
  display: flex;
}
</style>

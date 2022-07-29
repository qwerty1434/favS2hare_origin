<template>
  <div>
    <div class="top">
      <nav-bar></nav-bar>
      <v-tabs fixed-tabs background-color="indigo" dark>
        <v-tab @click="setTabYoutube"> 동영상 탭 </v-tab>
        <v-tab @click="setTabFeed"> 친구 피드 </v-tab>
      </v-tabs>
    </div>
    <div v-if="!isFeed" class="tab">
      <youtube-list></youtube-list>
    </div>
    <div v-else class="tab">
      <news-feed></news-feed>
    </div>
    <div class="bottom">
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import NewsFeed from "../../components/Home/NewsFeed.vue";
import YoutubeList from "../../components/Home/YoutubeList.vue";
import NavBar from "../../components/NavBar.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  components: { YoutubeList, NewsFeed, NavBar, BottomNavigationBar },
  name: "HomeView",
  data() {
    return {
      isFeed: false, // -> 친구피드 탭이면 true, 동영상 탭이면 false
      //
    };
  },
  methods: {
    setTabYoutube() {
      this.isFeed = false;
    },
    setTabFeed() {
      this.isFeed = true;
    },
  },
};
</script>

<style>
.top {
  position: fixed;
  width: 100%;
}

.tab {
  padding-top: 100px;
}

.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>

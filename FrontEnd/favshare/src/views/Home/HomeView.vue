<template>
  <div>
    <div class="top">
      <nav-bar></nav-bar>
      <v-tabs
        fixed-tabs
        active-class="active-tab"
        hide-slider
        class="tab-font"
        v-model="active_tab"
      >
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
import { mapActions, mapGetters } from "vuex";

export default {
  components: { YoutubeList, NewsFeed, NavBar, BottomNavigationBar },
  name: "HomeView",
  data() {
    return {
      active_tab: this.homeActiveTab,
    };
  },
  computed: {
    ...mapGetters(["isFeed", "homeActiveTab"]),
  },
  methods: {
    ...mapActions(["setTabYoutube", "setTabFeed"]),
  },
  watch: {
    "$store.state.home.homeActiveTab": function () {
      console.log(this.$store.state.home.homeActiveTab);
    },
  },
  created() {
    this.active_tab = this.homeActiveTab;
  },
};
</script>

<style scoped>
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

.active-tab {
  color: #ff5d5d !important;
  background-color: white;
}

.v-tab::before {
  background-color: white;
}

.tab-font {
  text-align: center;
  font-size: 20px;
}
</style>

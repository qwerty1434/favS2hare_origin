<template>
  <div>
    <v-app-bar dense elevation="1" grow color="white" absolute class="comm-top">
      <v-row>
        <v-col cols="2">
          <v-icon @click="goBack">mdi-arrow-left</v-icon>
        </v-col>
        <v-col class="ml-n5">
          <v-toolbar-title> <h4>동영상</h4> </v-toolbar-title>
        </v-col>
      </v-row>
    </v-app-bar>
    <div class="search-view">
      <search-more-youtube-list></search-more-youtube-list>
    </div>
    <div class="comm-bottom">
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import SearchMoreYoutubeList from "@/components/Home/search/SearchMoreYoutubeList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "SearchMoreYoutubeView",
  components: { SearchMoreYoutubeList, BottomNavigationBar },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.comm-top {
  position: fixed;
  width: 100%;
}
.search-view {
  padding-top: 30px;
  padding-bottom: 60px;
}
.comm-bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>

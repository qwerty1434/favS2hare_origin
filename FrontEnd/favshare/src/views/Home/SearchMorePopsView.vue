<template>
  <!-- eslint-disable -->
  <div>
    <v-app-bar dense elevation="1" grow color="white" absolute class="comm-top">
      <v-row>
        <v-col cols="2">
          <v-icon @click="goBack">mdi-arrow-left</v-icon>
        </v-col>
        <v-col class="ml-n5">
          <v-toolbar-title> <h4>POPS</h4> </v-toolbar-title>
        </v-col>
      </v-row>
    </v-app-bar>
    <div class="search-view">
      <search-more-pops-list></search-more-pops-list>
    </div>
    <div class="comm-bottom">
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
  padding-top: 40px;
  padding-bottom: 60px;
}
.comm-bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>

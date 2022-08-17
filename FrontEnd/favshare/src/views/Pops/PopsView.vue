<template>
  <!-- eslint-disable -->
  <div class="no-scroll">
    <div class="top">
      <nav-bar></nav-bar>
      <v-sheet class="pops-view">
        <pops-type-list></pops-type-list>
      </v-sheet>
    </div>
    <div class="pops-list">
      <pops-list></pops-list>
    </div>
    <div class="bottom">
      <bottom-navigation-bar class="bottom"></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import PopsTypeList from "@/components/Pops/PopsTypeList.vue";
import PopsList from "@/components/Pops/PopsList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import NavBar from "@/components/NavBar.vue";

export default {
  name: "PopsView",
  components: {
    PopsTypeList,
    PopsList,
    BottomNavigationBar,
    NavBar,
  },
};
</script>

<style scoped>
.pops-body {
  padding-top: 130px;
}
.top {
  position: fixed;
  width: 100%;
  top: 0;
  z-index: 2;
}
.pops-view {
  padding-top: 5px;
}
.pops-list {
  padding-top: 160px;
}
.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
/* 스크롤바 제거 */
.no-scroll::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
</style>

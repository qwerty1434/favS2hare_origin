<template>
  <!-- eslint-disable -->
  <div>
    <div class="top">
      <nav-bar></nav-bar>
      <div class="pops-view">
        <pops-type-list></pops-type-list>
      </div>
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
}
.pops-view {
  padding: 20px;
  overflow: auto;
  white-space: nowrap;
  display: flex;
}
.pops-list {
  padding-top: 140px;
  overflow: auto;
}
.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>

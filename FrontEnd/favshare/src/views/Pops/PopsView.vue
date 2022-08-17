<template>
  <!-- eslint-disable -->
  <div>
    <div class="top">
      <nav-bar></nav-bar>
      <div class="pops-view">
        <pops-type-list></pops-type-list>
      </div>
    </div>
    <!-- <window-test class="tmp"></window-test> -->
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
// import windowTest from "@/components/Pops/windowTest";

export default {
  name: "PopsView",
  components: {
    PopsTypeList,
    PopsList,
    BottomNavigationBar,
    NavBar,
    // windowTest,
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
  z-index: 2;
}
.pops-list {
  padding-top: 140px;
  padding-left: 10px;
  overflow: scroll;
}
.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}

.tmp {
  padding-top: 200px;
}
</style>

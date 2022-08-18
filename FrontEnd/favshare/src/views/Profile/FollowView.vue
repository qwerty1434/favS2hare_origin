<template>
  <div>
    <div class="top">
      <nav-bar></nav-bar>
      <v-tabs fixed-tabs active-class="active-tab" hide-slider class="tab-font">
        <v-tab @click="setTabFollower"> 팔로워 </v-tab>
        <v-tab @click="setTabFollowing"> 팔로잉 </v-tab>
      </v-tabs>
    </div>
    <div v-if="!followtab" class="tab">
      <follower-list></follower-list>
    </div>
    <div v-else class="tab">
      <following-list></following-list>
    </div>
    <div class="bottom">
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import NavBar from "../../components/NavBar.vue";
import FollowerList from "@/components/Profile/FollowerList.vue";
import FollowingList from "@/components/Profile/FollowingList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "FollowView",
  components: { NavBar, FollowerList, FollowingList, BottomNavigationBar },
  data() {
    return {};
  },
  created() {
    this.isFollowingTab();
  },
  watch: {
    "$store.state.profile.followtab": function () {
      console.log(this.$store.state.profile.followtab);
    },
  },
  methods: {
    ...mapActions(["setTabFollower", "setTabFollowing"]),
    isFollowingTab() {
      if (this.followtab == 1) {
        console.log("팔로잉 tab");
      }
    },
  },
  computed: {
    ...mapGetters(["followtab"]),
  },
};
</script>

<style scoped>
.top {
  position: fixed;
  width: 100%;
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

.tab-font {
  text-align: center;
  font-size: 20px;
}
</style>

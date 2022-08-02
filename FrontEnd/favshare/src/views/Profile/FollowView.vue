<template>
  <div>
    <div class="top">
      <nav-bar></nav-bar>
      <v-tabs fixed-tabs background-color="indigo" dark>
        <v-tab @click="setTabFollower"> 팔로워 </v-tab>
        <v-tab @click="setTabFollowing"> 팔로잉 </v-tab>
      </v-tabs>
    </div>
    <div v-if="!isFollowing" class="tab">
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

export default {
  name: "FollowView",
  components: { NavBar, FollowerList, FollowingList, BottomNavigationBar },
  data() {
    return {
      isFollowing: false,
    };
  },
  methods: {
    setTabFollower() {
      this.isFollowing = false;
    },
    setTabFollowing() {
      this.isFollowing = true;
    },
  },
};
</script>

<style></style>

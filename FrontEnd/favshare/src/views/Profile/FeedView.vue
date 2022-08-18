<template>
  <div>
    <nav-bar></nav-bar>
    <div v-if="isSignin">
      <feed-info class="feed-view"></feed-info>
      <v-divider></v-divider>
      <div class="delete-button">
        <h4>FEEDS</h4>
        <div v-if="feedUserInfo.id === userId">
          <div v-if="isDelete">
            <v-icon @click="fetchIsDelete(false)">mdi-cog-outline</v-icon>
          </div>
          <div v-else>
            <v-btn
              class="ma-2"
              outlined
              color="red lighten-1"
              elevation="2"
              small
              @click="fetchIsDelete(true)"
              >삭제완료</v-btn
            >
            <!-- <button @click="fetchIsDelete(true)">삭제</button> -->
          </div>
        </div>
        <!-- <div v-if="isDelete">
          <v-icon @click="fetchIsDelete(false)">mdi-cog-outline</v-icon>
        </div>
        <div v-else>
          <button @click="fetchIsDelete(true)">[삭제완료]</button>
        </div> -->
      </div>
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
import LoginRequestMessage from "@/components/Home/LoginRequestMessage.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import FeedPopsList from "@/components/Profile/FeedPopsList.vue";
import FeedInfo from "@/components/Profile/FeedInfo.vue";
import FeedList from "@/components/Profile/FeedList.vue";
import { mapActions, mapGetters } from "vuex";

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
    ...mapGetters([
      "isDelete",
      "feedList",
      "isSignin",
      "feedUserInfo",
      "userId",
    ]),
  },
  methods: {
    ...mapActions(["fetchIsDelete"]),
  },
};
</script>

<style scoped>
.delete-button {
  display: flex;
  flex-direction: row;
  padding: 40px;
  padding-top: 15px;
  padding-bottom: 0px;
  justify-content: space-between;
}
.feed-view {
  padding-top: 20px;
  padding-left: 30px;
}

.rpops {
  width: 360px;
  padding: 20px;
  padding-top: 10px;
  overflow: scroll;
  overflow: auto;
  white-space: nowrap;
  display: flex;
}
</style>

<template>
  <!-- tmp html -->
  <div>
    <button @click="getLogin">[ 로그인하기 ]</button>
    <button @click="getLogout">[ 로그아웃하기 ]</button>
    <div v-if="isLogin">
      <news-feed-list></news-feed-list>
    </div>
    <div v-else>
      <login-request-message></login-request-message>
    </div>
  </div>
  <!-- for-use html -->
  <!-- <div>
    <div v-if="userId">
      <news-feed-list></news-feed-list>
    </div>
    <div v-else>
      <login-request-message></login-request-message>
    </div>
  </div> -->
</template>

<script>
import { mapGetters } from "vuex";
import LoginRequestMessage from "./LoginRequestMessage.vue";
import NewsFeedList from "./NewsFeedList.vue";

export default {
  components: { NewsFeedList, LoginRequestMessage },
  name: "NewsFeed",
  data() {
    return {
      isLogin: true,
    };
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  methods: {
    // 밑에 두개 다 tmp용
    getLogin() {
      this.isLogin = true;
    },
    getLogout() {
      this.isLogin = false;
    },
  },
  // for-use watch
  // watch: {
  //   "$store.state.account.userId": function () {
  //     console.log(this["$store.state.account.userId"]);
  //   },
  // },
};
</script>

<style></style>

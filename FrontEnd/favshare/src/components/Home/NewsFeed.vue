<template>
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
</template>

<script>
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
  methods: {
    getLogin() {
      this.isLogin = true;
    },
    getLogout() {
      this.isLogin = false;
    },
  },
};
</script>

<style></style>

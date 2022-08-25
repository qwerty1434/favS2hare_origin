<template>
  <div>
    <div v-if="isSignin">
      <news-feed-list></news-feed-list>
    </div>
    <div v-else>
      <login-request-message></login-request-message>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import LoginRequestMessage from "@/components/Home/LoginRequestMessage.vue";
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
    ...mapGetters(["userId", "isSignin"]),
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

<template>
  <v-bottom-navigation grow absolute class="b-nav">
    <!-- <router-link
      :to="{ name: 'home' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn>
        <span>Home</span>

        <v-icon>mdi-home</v-icon>
      </v-btn>
    </router-link> -->
    <v-btn @click="routerPushes('home')">
      <span>Home</span>

      <v-icon>mdi-home</v-icon>
    </v-btn>

    <!-- <router-link
      :to="{ name: 'pops' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn>
        <span>Pops</span>

        <v-icon>mdi-party-popper</v-icon>
      </v-btn>
    </router-link> -->
    <v-btn @click="routerPushes('pops')">
      <span>Pops</span>

      <v-icon>mdi-party-popper</v-icon>
    </v-btn>

    <!-- <router-link
      :to="{ name: 'search' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn>
        <span>Search</span>

        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </router-link> -->
    <v-btn @click="routerPushes('search')">
      <span>Search</span>

      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn @click="[fetchFeedUserInfo(userId), fetchFeedList]">
      <span>Profile</span>

      <v-icon>mdi-account</v-icon>
    </v-btn>
    <!-- <router-link
      :to="{ name: 'feed' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn @click="fetchFeedUserInfo">
        <span>Profile</span>

        <v-icon>mdi-account</v-icon>
      </v-btn>
    </router-link> -->
  </v-bottom-navigation>
</template>

<script>
import router from "@/router";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "BottomNavigationBar",
  data() {
    return {
      value: 5,
    };
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  methods: {
    ...mapActions(["fetchFeedUserInfo", "fetchFeedList"]),
    routerPushes(icon) {
      router.push({ name: icon });
    },
  },
};
</script>

<style>
.button {
  padding-top: 8px;
  color: #ff5d5d !important;
}

.b-nav {
  color: #ff5d5d !important;
}

.bnav-active {
  color: #ff5d5d !important;
}
</style>

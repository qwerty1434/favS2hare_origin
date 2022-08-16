<template>
  <v-bottom-navigation
    :value="bottomValue"
    grow
    color="teal"
    absolute
    class="bottom"
  >
    <v-btn @click="[routerPushes('home'), fetchBottomValue(1)]">
      <span>Home</span>
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn
      @click="[routerPushes('pops'), fetchBottomValue(2), fetchPopList(0)]"
    >
      <span>Pops</span>
      <v-icon>mdi-party-popper</v-icon>
    </v-btn>

    <v-btn @click="[routerPushes('search'), fetchBottomValue(3)]">
      <span>Search</span>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn
      @click="
        [
          fetchFeedUserInfo(userId),
          fetchFeedList(userId),
          routerPushes('feed'),
          fetchBottomValue(4),
        ]
      "
    >
      <span>Profile</span>
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
import router from "@/router";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "BottomNavigationBar",
  data() {
    return {
      value: this.bottomValue,
    };
  },
  computed: {
    ...mapGetters(["userId", "bottomValue"]),
  },
  methods: {
    ...mapActions([
      "fetchFeedUserInfo",
      "fetchFeedList",
      "fetchBottomValue",
      "fetchPopList",
    ]),
    routerPushes(icon) {
      router.push({ name: icon });
    },
  },
  watch: {
    "$store.state.bottomValue": function () {
      console.log(this.$store.state.bottomValue);
    },
  },
};
</script>

<style>
/* .button {
  padding-top: 8px;
  color: #ff5d5d !important;
}

.b-nav {
  color: #ff5d5d !important;
}

.bnav-active {
  color: #ff5d5d !important;
} */
</style>

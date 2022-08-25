<template>
  <v-bottom-navigation
    :value="bottomValue - 1"
    grow
    color="teal"
    absolute
    class="bottom"
  >
    <v-btn class="pinkBtn" @click="[routerPushes('home'), fetchBottomValue(1)]">
      <span>Home</span>
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn
      class="pinkBtn"
      @click="[routerPushes('pops'), fetchBottomValue(2), fetchPopList(0)]"
    >
      <span>Pops</span>
      <v-icon>mdi-party-popper</v-icon>
    </v-btn>

    <v-btn
      class="pinkBtn"
      @click="[routerPushes('search'), fetchBottomValue(3)]"
    >
      <span>Search</span>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn
      class="pinkBtn"
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
    "$store.state.bottomValue": function () {},
  },
};
</script>

<style>
.v-btn.pinkBtn {
  color: #ff5d5d !important;
}
</style>

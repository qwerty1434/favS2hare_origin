<template>
  <!-- eslint-disable -->
  <div class="ml-2 mr-2">
    <v-row>
      <v-col align="center">
        <v-avatar
          color="orange lighten-4"
          size="65"
          @click="
            [
              routerPushes('feed'),
              fetchFeedUserInfo(userListItem.id),
              fetchFeedList(userListItem),
              fetchIsFollowing(userListItem),
            ]
          "
        >
          <img class="following-size" :src="userListItem.profileImageUrl" alt="image" />
        </v-avatar>
        <h6 class="mt-3">
          {{ userListItem.nickname }}
        </h6>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import { mapActions } from "vuex";
import router from "@/router";

export default {
  name: "SearchFollowingListItem",
  data() {
    return {};
  },
  props: {
    userListItem: Object,
  },
  methods: {
    ...mapActions(["fetchFeedUserInfo", "fetchFeedList", "fetchIsFollowing"]),
    // goFriendFeed() {
    //   this.$router.push({ name: "feed", params: this.userListItem.id });
    // },
    routerPushes(icon) {
      router.push({ name: icon });
    },
  },
};
</script>
<style>
.following-size {
  height: 70px;
  width: 70px;
}
</style>

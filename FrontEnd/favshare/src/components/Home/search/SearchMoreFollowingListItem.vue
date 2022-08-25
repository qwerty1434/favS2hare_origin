<template>
  <v-hover>
    <v-row>
      <v-col align="center" cols="3">
        <v-avatar
          color="orange lighten-4"
          size="55"
          @click="
            [
              routerPushes('feed'),
              fetchFeedUserInfo(searchedFollowingListItem.id),
              fetchFeedList(searchedFollowingListItemid),
              fetchIsFollowing(searchedFollowingListItemid),
            ]
          "
        >
          <img
            class="following-size"
            :src="searchedFollowingListItem.profileImageUrl"
            alt="image"
          />
        </v-avatar>
      </v-col>
      <v-col class="ml-n4">
        <h5 class="mt-3">
          {{ searchedFollowingListItem.nickname }}
        </h5>
        <h5 class="grey--text">
          {{ searchedFollowingListItem.content }}
        </h5>
      </v-col>
    </v-row>
  </v-hover>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
import router from "@/router";

export default {
  name: "SearchMoreFollowingListItem",
  data() {
    return {};
  },
  props: {
    searchedFollowingListItem: Object,
  },
  computed: {
    ...mapGetters([""]),
  },
  methods: {
    ...mapActions(["fetchFeedUserInfo", "fetchFeedList", "fetchIsFollowing"]),
    routerPushes(icon) {
      router.push({ name: icon });
    },
  },
};
</script>
<style>
.following-size {
  height: 100px;
  width: 100px;
}
.v-row:hover {
  color: red;
}
</style>

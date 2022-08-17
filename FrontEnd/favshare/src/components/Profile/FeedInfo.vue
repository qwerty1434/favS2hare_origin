<template>
  <div>
    <div class="user-info">
      <div class="user-pic">
        <v-avatar>
          <img :src="feedUserInfo.profileImageUrl" alt="John" />
        </v-avatar>
      </div>
      <div class="profile-stats">
        <ul>
          <li>
            <div>게시 '팝'</div>
            <div>{{ feedUserInfo.popCount }}</div>
          </li>
          <li @click="[setTabFollower, routerPushes('follow')]">
            <div>팔로워 수</div>
            <div>{{ feedUserInfo.followerNum }}</div>
          </li>
          <li @click="[setTabFollowing, routerPushes('follow')]">
            <div>팔로잉 수</div>
            <div>{{ feedUserInfo.followingNum }}</div>
          </li>
        </ul>
      </div>
    </div>
    <div class="follow-edit">
      <div v-if="feedUserInfo.id === userId">
        <button @click="goProfileEdit">프로필 편집</button>
      </div>
      <div v-else>
        <div v-if="isFollowing">
          <button @click="followBtn">팔로잉</button>
        </div>
        <div v-else>
          <button @click="fetchFollowInProfile(feedUserInfo.id)">팔로우</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "FeedInfo",
  computed: {
    ...mapGetters(["feedUserInfo", "userId", "isFollowing"]),
  },
  methods: {
    ...mapActions([
      "setTabFollower",
      "setTabFollowing",
      "fetchFollowInProfile",
    ]),
    routerPushes(name) {
      router.push({ name: name });
    },
    goProfileEdit() {
      this.$router.push({ name: "profileedit" });
    },
  },
  watch: {
    "$store.state.profile.feedUserInfo": function () {
      console.log(this.$store.state.profile.feedUserInfo);
    },
    "$store.state.profile.isFollowing": function () {
      console.log(this.$store.state.profile.isFollowing);
    },
  },
};
</script>

<style scoped>
.follow-edit {
  text-align: center;
  padding-top: 30px;
  padding-right: 30px;
}
.user-info {
  display: flex;
}

.user-pic {
  align-content: center;
}

.profile-stats li {
  cursor: pointer;
  list-style-type: none;
  float: left;
  padding-right: 10px;
  align-content: center;
  justify-content: center;
}

li {
  text-align: center;
}
</style>

<template>
  <!-- eslint-disable -->
  <div>
    <div class="user-info">
      <div class="user-pic">
        <v-avatar size="65">
          <img :src="feedUserInfo.profileImageUrl" alt="John" />
        </v-avatar>
      </div>
      <div class="ml-4 mt-3 profile-stats">
        <ul>
          <li>
            <div><h4 class="user-descip">게시 팝</h4></div>
            <div class="mt-1">
              <h4>{{ feedUserInfo.popCount }}</h4>
            </div>
          </li>
          <li @click="[routerPushes('follow'), fetchFollowerList()]">
            <div @click="setTabFollower">
              <h4 class="user-descip">팔로워</h4>
            </div>
            <div class="mt-1">
              <h4>{{ feedUserInfo.followerNum }}</h4>
            </div>
          </li>
          <li @click="[routerPushes('follow'), fetchFollowingList()]">
            <div @click="setTabFollowing">
              <h4 class="user-descip">팔로잉</h4>
            </div>
            <div class="mt-1">
              <h4>{{ feedUserInfo.followingNum }}</h4>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="mt-3">
      <h4 class="user-name">{{ feedUserInfo.nickname }}</h4>
    </div>
    <div>
      <h5 class="user-content">{{ feedUserInfo.content }}</h5>
    </div>
    <div class="follow-edit">
      <div v-if="feedUserInfo.id === userId">
        <v-btn
          class="ma-2"
          outlined
          rounded
          small
          color="#FF5D5D"
          elevation="1"
          @click="goProfileEdit"
          style="width: 250px"
          >프로필 편집</v-btn
        >
      </div>
      <div v-else>
        <div v-if="isFollowing">
          <v-btn
            class="ma-2 white--text"
            small
            rounded
            color="grey"
            elevation="1"
            style="width: 250px"
            @click="cancelFollowing"
            >팔로우 취소</v-btn
          >
        </div>
        <div v-else>
          <v-btn
            class="ma-2 white--text"
            small
            rounded
            color="#FF5D5D"
            elevation="1"
            style="width: 250px"
            @click="fetchFollowInProfile(feedUserInfo.id)"
            >팔로우</v-btn
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import { mapActions, mapGetters } from "vuex";
import api from "@/api/springRestAPI";

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
      "fetchFeedUserInfo",
      "fetchFollowerList",
      "fetchFollowingList",
    ]),
    routerPushes(name) {
      router.push({ name: name });
    },
    goProfileEdit() {
      this.$router.push({ name: "profileedit" });
    },
    cancelFollowing() {
      console.log(this.userId);
      console.log(this.feedUserInfo.id);
      axios({
        method: "delete",
        url: api.userFollow.followFromDelete(),
        data: {
          fromUserId: this.userId,
          toUserId: this.feedUserInfo.id,
        },
      })
        .then((res) => {
          console.log(res);
          this.$store.commit("SET_ISFOLLOWING", false);
          this.fetchFeedUserInfo(this.feedUserInfo.id);
        })
        .catch((res) => console.log(res));
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
  // created() {
  //   this.fetchIsFollowing
  // }
};
</script>

<style scoped>
.follow-edit {
  text-align: center;
  padding-top: 10px;
  padding-bottom: 10px;
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
  padding-right: 20px;
  align-content: center;
  justify-content: center;
}

li {
  text-align: center;
}

.user-descip {
  font-weight: 400;
}

.user-name {
  font-size: 15px;
}
.user-content {
  font-weight: 400;
}
</style>

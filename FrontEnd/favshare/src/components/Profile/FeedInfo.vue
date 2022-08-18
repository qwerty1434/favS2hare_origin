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
            <div class="stat-font">게시 팝</div>
            <div>{{ feedUserInfo.popCount }}</div>
          </li>
          <li
            @click="
              [
                setTabFollower,
                routerPushes('follow'),
                fetchFollowerList(feedUserInfo.id),
              ]
            "
          >
            <div class="stat-font">팔로워 수</div>
            <div>{{ feedUserInfo.followerNum }}</div>
          </li>
          <li
            @click="
              [
                setTabFollower,
                routerPushes('follow'),
                fetchFollowingList(feedUserInfo.id),
              ]
            "
          >
            <div class="stat-font">팔로잉 수</div>
            <div>{{ feedUserInfo.followingNum }}</div>
          </li>
        </ul>
      </div>
    </div>
    <div class="follow-edit">
      <div v-if="feedUserInfo.id === userId">
        <v-btn
          class="ma-2"
          outlined
          color="red lighten-1"
          elevation="2"
          @click="goProfileEdit"
          >프로필 편집</v-btn
        >
      </div>
      <div v-else>
        <div v-if="isFollowing">
          <v-btn
            class="ma-2"
            outlined
            color="red lighten-1"
            elevation="2"
            @click="cancelFollowing"
            >팔로우 취소</v-btn
          >
        </div>
        <div v-else>
          <v-btn
            class="ma-2"
            outlined
            color="red lighten-1"
            elevation="2"
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
  font-family: "MabinogiClassicR" !important;
}

@font-face {
  font-family: "EarlyFontDiary";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_220508@1.0/EarlyFontDiary.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "Happiness-Sans-Title";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2205@1.0/Happiness-Sans-Title.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "MabinogiClassicR";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2207-01@1.0/MabinogiClassicR.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
</style>

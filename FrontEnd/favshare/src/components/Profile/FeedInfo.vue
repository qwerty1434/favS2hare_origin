<template>
  <div>
    <div class="user-info">
      <div class="user-pic">
        <v-avatar>
          <img :src="feedUserInfo.userPicture" alt="John" />
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
          <li @click="[setTabFollower, routerPushes('follow')]">
            <div>팔로잉 수</div>
            <div>{{ feedUserInfo.followingNum }}</div>
          </li>
        </ul>
      </div>
    </div>
    <div>
      <div v-if="feedUserInfo.id === userId">
        <button @click="goProfileEdit">프로필 편집</button>
      </div>
      <div v-else>
        <button>팔로우/팔로잉</button>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "FeedInfo",
  data() {
    return {
      tmpUserInfo: {
        id: 1,
        nickname: "은우",
        count: 7,
        followerNum: 100000,
        followingNum: 1,
        userPicture:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWSvJiz9yA4YjpGfs_og5XpIsnIEc_cCiaGw&usqp=CAU",
        feedsList: [
          {
            id: 1,
            name: "나연",
            feedImageUrl: "b.jpg",
            userId: 1,
            first: true,
          },
          {
            id: 2,
            name: "BTS",
            feedImageUrl: "c.jpg",
            userId: 1,
            first: false,
          },
        ],
      },
    };
  },
  computed: {
    ...mapGetters(["feedUserInfo", "userId"]),
  },
  methods: {
    ...mapActions(["setTabFollower", "setTabFollowing"]),
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
  },
};
</script>

<style>
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
</style>

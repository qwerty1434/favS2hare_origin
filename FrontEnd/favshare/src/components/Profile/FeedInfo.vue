<template>
  <div>
    <div>
      <div class="user-pic">
        <v-avatar>
          <img :src="tmpUserInfo.userPicture" alt="John" />
        </v-avatar>
      </div>
      <div class="profile-stats">
        <ul>
          <router-link :to="{ name: 'follow' }">
            <li>
              <span class="stats">{{ tmpUserInfo.count }}</span
              >게시 '팝'
            </li>
          </router-link>
          <router-link :to="{ name: 'follow' }">
            <li @click="setTabFollower">
              <span class="stats">{{ tmpUserInfo.followerNum }}</span
              >팔로워 수
            </li>
          </router-link>
          <router-link :to="{ name: 'follow' }">
            <li @click="setTabFollowing">
              <span class="stats">{{ tmpUserInfo.followingNum }}</span
              >팔로잉 수
            </li>
          </router-link>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
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
    ...mapGetters(["userInfo"]),
    ...mapActions(["setTabFollower", "setTabFollowing"]),
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
}
</style>

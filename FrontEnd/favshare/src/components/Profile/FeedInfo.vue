<template>
  <div>
    <div class="user-info">
      <div class="user-pic">
        <v-avatar>
          <img :src="tmpUserInfo.userPicture" alt="John" />
        </v-avatar>
        <p>{{ tmpUserInfo.nickname }}</p>
      </div>
      <div>
        <ul>
          <li>
            <span class="stats">{{ tmpUserInfo.count }}</span
            >게시 '팝'
          </li>
          <li>
            <span class="stats">{{ tmpUserInfo.followerNum }}</span
            >팔로워 수
          </li>
          <li>
            <span class="stats">{{ tmpUserInfo.followingNum }}</span
            >팔로잉 수
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

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

ul {
  list-style: none;
}

li {
  float: left;
}
</style>

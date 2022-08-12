<template>
  <v-container>
    <v-row>
      <v-col><img :src="feedUserInfo.userPicture" alt="John" /></v-col>
      <v-col>{{ feedUserInfo.nickname }}</v-col>
      <v-col></v-col>
      <v-col cols="4" align="center"
        ><v-btn small v-if="isFollowing" @click="unFollow">팔로우 취소</v-btn>
        <v-btn small v-else @click="unFollow" color="primary"
          >팔로우</v-btn
        ></v-col
      >
    </v-row>
  </v-container>
</template>

<script>
// 팔로우 버튼 누를 시 -> post요청 => fromUserId: 자기아이디, toUserId: 상대아이디
// 팔로잉취수 버튼 누를 시 -> delete user/follow/from => fromUserId: 자기아이디, toUserId: 상대아이디
import axios from "axios";

import { mapGetters } from "vuex";
export default {
  name: "FollowingListItem",
  data() {
    return {
      isFollowing: true,
    };
  },
  props: {
    followingItem: Object,
  },
  computed: {
    ...mapGetters(["feedUserInfo", "userId"]),
  },
  methods: {
    unFollow(event) {
      console.log(event.target.textContent);
      if (this.isFollowing) {
        this.isFollowing = false;
        event.target.textContent = "팔로우";
        axios({
          method: "post",
          url: "http://localhost:8080/user/follow",
          data: {
            fromUserId: this.userId,
            toUserId: this.followingItem.toUserId,
          },
        }).then((res) => console.log(res));
      } else {
        this.isFollowing = true;
        event.target.textContent = "팔로우 취소";
        axios({
          method: "delete",
          url: "http://loaclhost:8080/user/follow/from",
          data: {
            fromUserId: this.userId,
            toUserId: this.followingItem.toUserId,
          },
        }).then((res) => console.log(res));
      }
    },
  },
};
</script>
<style></style>

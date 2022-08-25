<template>
  <div class="ma-3">
    <v-row align="center">
      <v-col
        ><v-avatar size="45px"
          ><img :src="followingItem.profileImageUrl" alt="image" /></v-avatar
      ></v-col>
      <v-col class="ml-n3" cols="4"
        ><h5>{{ followingItem.nickname }}</h5></v-col
      >
      <v-spacer></v-spacer>
      <v-col cols="4" align="center"
        ><v-btn
          class="white--text"
          elevation="0"
          color="grey lighten"
          rounded
          small
          v-if="isFollowing"
          @click="unFollow"
          >팔로우 취소</v-btn
        >
        <v-btn
          class="mr-n5 white--text"
          color="red lighten-1"
          elevation="0"
          rounded
          small
          v-else
          @click="unFollow"
          >팔로우</v-btn
        ></v-col
      >
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import api from "@/api/springRestAPI";
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
    ...mapGetters(["userId"]),
  },
  methods: {
    unFollow(event) {
      if (this.isFollowing) {
        this.isFollowing = false;
        event.target.textContent = "팔로우";
        axios({
          method: "post",
          url: api.userFollow.follow(),
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
          url: api.userFollow.followFromDelete(),
          data: {
            fromUserId: this.userId,
            toUserId: this.followingItem.toUserId,
          },
        }).then((res) => console.log(res));
      }
    },
    printFollow() {},
  },
  created() {
    this.printFollow();
  },
};
</script>
<style></style>

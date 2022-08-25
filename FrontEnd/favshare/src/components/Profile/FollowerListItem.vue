<template>
  <div class="ma-3">
    <v-row align="center">
      <v-col
        ><v-avatar size="45px"><img :src="followerItem.profileImageUrl" alt="" /></v-avatar
      ></v-col>
      <v-col class="ml-n3" cols="4"
        ><h5>{{ followerItem.nickname }}</h5></v-col
      >
      <v-spacer></v-spacer>
      <v-col align="center">
        <v-btn
          class="mr-n5 white--text"
          rounded
          small
          color="red lighten-1"
          elevation="0"
          v-if="!isFollowing"
          @click="unFollow"
        >
          팔로우
        </v-btn>
        <v-btn
          class="mr-n5 white--text"
          color="red lighten-1"
          elevation="0"
          rounded
          outlined
          small
          v-else
          @click="unFollow"
        >
          팔로잉
        </v-btn>
      </v-col>
      <v-col align="center">
        <v-btn
          class="white--text"
          elevation="0"
          color="grey lighten"
          rounded
          small
          @click="deleteFollower"
          >삭제</v-btn
        >
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  name: "FollowerListItem",
  data() {
    return {
      isFollowing: false,
    };
  },
  props: {
    followerItem: Object,
  },
  computed: {
    ...mapGetters(["userId", "feedUserInfo"]),
  },
  methods: {
    unFollow(event) {
      console.log(event.target.textContent);
      console.log(this.followerItem);
      console.log(this.userId);
      if (!this.isFollowing) {
        this.isFollowing = true;
        event.target.textContent = "팔로잉";
        axios({
          method: "post",
          url: api.userFollow.follow(),
          data: {
            fromUserId: this.feed,
            toUserId: this.followerItem.fromUserId,
          },
        }).then((res) => console.log(res.data));
      } else {
        this.isFollowing = false;
        event.target.textContent = "팔로우";
        axios({
          method: "delete",
          url: api.userFollow.followFromDelete(),
          data: {
            fromUserId: this.userId,
            toUserId: this.followerItem.fromUserId,
          },
        });
      }
    },
    deleteFollower() {
      console.log("follower 삭제");
      axios({
        method: "delete",
        url: api.userFollow.followToDelete(),
        data: {
          fromUserId: this.followerItem.fromUserId,
          toUserId: this.userId,
        },
      }).then((res) => {
        console.log(res.data);
        this.$router.push({ name: "follow" });
      });
    },
    initIsFollowing() {
      console.log("init", this.followerItem);
      if (this.followerItem.followForFollow) {
        this.isFollowing = true;
      }
    },
  },
  created() {
    this.initIsFollowing();
  },
};
</script>

<style></style>

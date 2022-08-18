<template>
  <v-container>
    <v-row align="center">
      <v-col><img :src="followerItem.profileImageUrl" alt="" /></v-col>
      <v-col>{{ followerItem.nickname }}</v-col>
      <v-col></v-col>
      <v-col align="right">
        <v-btn
          small
          outlined
          color="red lighten-1"
          elevation="2"
          v-if="!isFollowing"
          @click="unFollow"
        >
          팔로우
        </v-btn>
        <v-btn small v-else @click="unFollow"> 팔로잉 </v-btn>
      </v-col>
      <v-col align="right">
        <v-btn small @click="deleteFollower">삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
import api from "@/api/springRestAPI";
// 팔로우 버튼 누를 시 -> post요청 => fromUserId: 자기아이디, toUserId: 상대아이디
// 팔로잉 버튼 누를 시 -> delete user/follow/from => fromUserId: 자기아이디, toUserId: 상대아이디
// 삭제 버튼 누를 시 -> delete user/follow/to => fromUserId: 상대아이디, toUserId: 자기아이디

// get from = 팔로워리스트 받음
// get to = 팔로잉리스트 받음
// delete from =

export default {
  name: "FollowerListItem",
  data() {
    return {
      isFollowing: false,
    };
  },
  props: {
    followerItem: Object, // {nickname: ~~}
  },
  computed: {
    ...mapGetters(["userId"]),
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
            fromUserId: this.userId,
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

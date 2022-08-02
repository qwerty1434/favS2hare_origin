<template>
  <v-container>
    <v-row align="center">
      <v-col>사용자 사진</v-col>
      <v-col>사용자 이름</v-col>
      <v-col></v-col>
      <v-col align="right">
        <v-btn small v-if="!isFollowing" @click="unFollow" color="primary">
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
export default {
  name: "FollowerListItem",
  data() {
    return {
      isFollowing: false,
    };
  },
  methods: {
    unFollow(event) {
      console.log(event.target.textContent);
      if (!this.isFollowing) {
        this.isFollowing = true;
        event.target.textContent = "팔로잉";
      } else {
        this.isFollowing = false;
        event.target.textContent = "팔로우";
      }
    },
    deleteFollower() {
      console.log("follower 삭제");
    },
  },
};
</script>
<style></style>

<template>
  <v-container>
    <v-row>
      <v-col>사용자 사진</v-col>
      <v-col>사용자 이름</v-col>
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
export default {
  name: "FollowingListItem",
  data() {
    return {
      isFollowing: true,
    };
  },
  methods: {
    unFollow(event) {
      console.log(event.target.textContent);
      if (this.isFollowing) {
        this.isFollowing = false;
        event.target.textContent = "팔로우";
      } else {
        this.isFollowing = true;
        event.target.textContent = "팔로우 취소";
      }
    },
  },
};
</script>
<style></style>

<template>
  <v-container>
    <v-row align="center">
      <v-col cols="2">
        <v-icon>mdi-arrow-left</v-icon>
      </v-col>
      <v-col class="ml-16">프로필 수정</v-col>
    </v-row>
    <v-divider class="ma-3"></v-divider>
    <div class="ma-10" align="center">
      <div class="user-pic">
        <v-avatar>
          <img :src="user.profile_image_url" alt="John" />
        </v-avatar>
      </div>
      <br />
      <div><router-link to="">프로필 사진 변경</router-link></div>
    </div>
    <v-row>
      <v-col cols="3">닉네임</v-col>
      <v-col>
        <v-text-field v-model="editUserInfo.nickname" dense> </v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="4">프로필 소개</v-col>
      <v-col>
        <v-text-field v-model="editUserInfo.content" dense></v-text-field>
      </v-col>
    </v-row>
    <div align="center" class="ma-10">
      <v-btn @click="updateProfile">수정완료</v-btn>
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "ProfileEditView",
  data() {
    return {
      user: {
        // id: "",
        // nickname: "ssafy",
        // content: "하이^_^",
        profile_image_url:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWSvJiz9yA4YjpGfs_og5XpIsnIEc_cCiaGw&usqp=CAU",
      },
    };
  },
  created() {
    console.log(this.userId);
    this.getProfileInfo(this.userId);
  },
  computed: {
    ...mapGetters(["userId", "editUserInfo"]),
  },
  methods: {
    ...mapActions(["getProfileInfo", "updateProfileInfo"]),
    updateProfile() {
      console.log("update profile");
      this.updateProfileInfo({
        userId: this.userId,
        nickname: this.editUserInfo.nickname,
        content: this.editUserInfo.content,
      });
      alert("프로필 정보가 수정되었습니다.");
      this.$router.go();
    },
  },
};
</script>

<style></style>

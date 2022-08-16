<template>
  <!-- eslint-disable -->
  <v-container>
    <v-row align="center">
      <v-col cols="2">
        <v-icon @click="goBack">mdi-arrow-left</v-icon>
      </v-col>
      <v-col cols="8" align="center">프로필 수정</v-col>
    </v-row>
    <v-divider class="ma-2"></v-divider>
    <v-row class="ma-10" align="center">
      <v-col align="center" cols="12">
        <div class="user-pic">
          <v-avatar size="60">
            <v-img :src="user.profile_image_url" alt="image"> </v-img>
          </v-avatar>
        </div>
      </v-col>
      <v-col class="ml-n100">
        <div>
          <v-file-input prepend-icon="mdi-image-plus-outline" hide-input label="프로필 사진 변경" />
        </div>
      </v-col>
      <div></div>
    </v-row>
    <div class="ma-4">
      <v-row align="center">
        <v-col cols="3"><h5>닉네임</h5></v-col>
        <v-col class="ml-n4">
          <v-text-field class="font-t" v-model="editUserInfo.nickname" dense> </v-text-field>
        </v-col>
      </v-row>
      <v-row class="mt-n5 mb-5" align="center">
        <v-col cols="4"><h5>프로필 소개</h5></v-col>
        <v-col class="ml-n3">
          <v-text-field class="font-t" v-model="editUserInfo.content" dense></v-text-field>
        </v-col>
      </v-row>
    </div>
    <div align="center" class="ma-10">
      <v-btn class="white--text" color="grey darken-1" small rounded @click="updateProfile"
        >수정완료</v-btn
      >
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
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.ml-n100 {
  margin-top: 15px;
  margin-left: -121px;
}
</style>

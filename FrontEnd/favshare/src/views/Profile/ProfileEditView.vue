<template>
  <v-container>
    <v-row align="center">
      <v-col cols="2">
        <v-icon large>mdi-arrow-left</v-icon>
      </v-col>
      <v-col class="ml-16">프로필 수정</v-col>
    </v-row>
    <v-divider class="ma-3"></v-divider>
    <div class="ma-10" align="center">
      <div>프로필 사진</div>
      <div><router-link to="">프로필 사진 변경</router-link></div>
    </div>
    <v-row>
      <v-col cols="3">닉네임</v-col>
      <v-col><v-text-field v-model="user.nickname" dense></v-text-field></v-col>
    </v-row>
    <v-row>
      <v-col cols="4">프로필 소개</v-col>
      <v-col><v-text-field v-model="user.content" dense></v-text-field></v-col>
    </v-row>
    <div align="center" class="ma-10">
      <v-btn @click="updateProfile">수정완료</v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "ProfileEditView",
  data() {
    return {
      user: {
        id: "",
        nickname: "ssafy",
        content: "하이^_^",
        profile_image_url: "",
      },
    };
  },
  methods: {
    updateProfile() {},
  },
};
</script>

<style></style>

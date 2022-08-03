<template>
  <div class="ma-2 div-center">
    <div>
      <v-row class="mb-n15 pb-1" align="center">
        <v-col cols="3"><h5>이메일</h5></v-col>
        <v-col>
          <v-text-field v-model="sampleUser.email" disabled></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mb-n15" align="center">
        <v-col cols="3"><h5>비밀번호</h5></v-col>
        <v-col>
          <v-text-field type="password" v-model="sampleUser.pw"></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mb-n15" align="center">
        <v-col cols="4"><h5>비밀번호 확인</h5></v-col>
        <v-col>
          <v-text-field type="password" v-model="sampleUser.pw"></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mb-n15" align="center">
        <v-col cols="2"><h5>이름</h5></v-col>
        <v-col>
          <v-text-field v-model="sampleUser.name"></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mb-n15" align="center">
        <v-col cols="3"><h5>전화번호</h5></v-col>
        <v-col>
          <v-text-field v-model="sampleUser.phone"></v-text-field>
        </v-col>
      </v-row>
      <v-row align="center">
        <v-col cols="3"><h5>생년월일</h5></v-col>
        <v-col>
          <v-text-field v-model="sampleUser.birth"></v-text-field>
        </v-col>
      </v-row>
    </div>
    <v-row>
      <v-col align="center">
        <v-btn small rounded @click="updateProfile">정보 저장</v-btn>
      </v-col>
    </v-row>
  </div>
</template>
<script>
export default {
  name: "AccountEdit",
  data() {
    return {
      sampleUser: {
        email: "ssafy7@ssafy.com",
        pw: "zxczxc",
        name: "홍길동",
        phone: "010-0000-0000",
        birth: "2022-08-03",
      },
    };
  },
  created() {
    this.getSampleUser();
  },
  methods: {
    // getSampleUser() {
    //   axios.get().then((response) => {
    //     this.sampleUser = response.data;
    //   });
    // },
    updateProfile() {
      console.log("update profile");
    },
  },
};
</script>
<style>
.div-center {
  width: 90%;
  position: absolute;
  top: 20%;
}
</style>

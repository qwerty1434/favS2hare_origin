<template>
  <!-- eslint-disable -->
  <div>
    <v-row class="pb-2">
      <v-col cols="2">
        <v-icon @click="goBack">mdi-arrow-left</v-icon>
      </v-col>
      <v-col cols="8" align="center">계정 정보</v-col>
    </v-row>
    <v-divider></v-divider>
    <div class="ma-2 div-center">
      <div>
        <v-row class="mb-n15 pb-1" align="center">
          <v-col cols="3"><h5>이메일</h5></v-col>
          <v-col>
            <v-text-field class="font-t" v-model="userId" disabled></v-text-field>
          </v-col>
        </v-row>
        <v-row class="mb-n15" align="center">
          <v-col cols="3"><h5>비밀번호</h5></v-col>
          <v-col>
            <v-text-field
              class="font-t"
              type="password"
              v-model="passwordNew"
              :rules="user_pw_rule"
              hide
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="mb-n15" align="center">
          <v-col cols="4"><h5>비밀번호 확인</h5></v-col>
          <v-col>
            <v-text-field
              class="font-t"
              type="password"
              v-model="passwordCheck"
              :rules="user_pw_rule2"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="mb-n15" align="center">
          <v-col cols="2"><h5>이름</h5></v-col>
          <v-col>
            <v-text-field class="font-t" v-model="userInfo.name"></v-text-field>
          </v-col>
        </v-row>
        <v-row class="mb-n15" align="center">
          <v-col cols="3"><h5>전화번호</h5></v-col>
          <v-col>
            <v-text-field class="font-t" v-model="userInfo.phone"></v-text-field>
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col cols="3"><h5>생년월일</h5></v-col>
          <v-col>
            <v-text-field class="font-t" v-model="birthDateForm"></v-text-field>
          </v-col>
        </v-row>
      </div>
      <v-row>
        <v-col align="center">
          <v-btn class="white--text" color="grey darken-2" small rounded @click="updateProfile"
            >정보 저장</v-btn
          >
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
/* eslint-disable */
import dayjs from "dayjs";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "AccountEdit",
  data() {
    return {
      birthDateForm: "",
      // sampleUser: {
      //   email: "ssafy7@ssafy.com",
      //   pw: "zxczxc",
      //   name: "홍길동",
      //   phone: "010-0000-0000",
      //   birth: "2022-08-03",
      // },
      state: "ins",
      passwordCheck: "",
      passwordNew: "",
      user_pw_rule: [
        (v) => (this.state === "ins" ? !!v || "패스워드는 필수 입력사항입니다." : true),
        (v) => !(v && v.length >= 30) || "패스워드는 30자 이상 입력할 수 없습니다.",
      ],
      user_pw_rule2: [
        (v) => (this.state === "ins" ? !!v || "패스워드는 필수 입력사항입니다." : true),
        (v) => !(v && v.length >= 30) || "패스워드는 30자 이상 입력할 수 없습니다.",
        (v) => v === this.passwordNew || "패스워드가 일치하지 않습니다.",
      ],
    };
  },
  created() {
    this.birthDateForm = dayjs(this.userInfo.birthDate).format("YYYY-MM-DD");
    // console.log(this.userInfo);
  },
  computed: {
    ...mapGetters(["userId", "userInfo"]),
  },
  methods: {
    ...mapActions(["updateUserInfo"]),
    // getSampleUser() {
    //   axios.get().then((response) => {
    //     this.sampleUser = response.data;
    //   });
    // },
    updateProfile() {
      console.log("update user");
      this.updateUserInfo({
        userId: this.userId,
        password: this.passwordNew,
        name: this.userInfo.name,
        phone: this.userInfo.phone,
        birthDate: this.userInfo.birthDate,
      });
      alert("계정 정보가 수정되었습니다.");
      this.$router.go();
    },
    goBack() {
      this.$router.go();
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
.font-t {
  font-size: 15px;
}
</style>

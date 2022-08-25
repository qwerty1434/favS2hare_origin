<template>
  <div>
    <v-row class="pb-2">
      <v-col cols="2">
        <v-icon @click="goBack">mdi-arrow-left</v-icon>
      </v-col>
      <v-col cols="8" align="center">계정 정보</v-col>
    </v-row>
    <v-divider></v-divider>
    <div class="top-confirm">
      <div class="ml-10">
        <v-subheader>개인정보를 확인하거나 수정하려면<br /></v-subheader>
        <v-subheader class="mt-n6">비밀번호를 입력하세요.</v-subheader>
      </div>
      <div class="body-confirm">
        <v-row align="center">
          <v-col cols="3"><h5>비밀번호</h5></v-col>
          <v-col>
            <v-text-field id="pw-value" type="password" v-model="pwValue" dense></v-text-field>
          </v-col>
        </v-row>
        <v-row class="mt-9">
          <v-col align="center">
            <v-btn class="white--text" small rounded color="grey darken-2" @click="confirmPw"
              >계정 정보 수정</v-btn
            >
          </v-col>
        </v-row>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "PwConfirm",
  data() {
    return {
      pwValue: "",
    };
  },
  computed: {
    ...mapGetters(["userId", "userInfo"]),
  },
  methods: {
    ...mapActions(["getUserInfo"]),
    confirmPw() {
      if (this.pwValue == "") {
        alert("비밀번호를 입력하세요.");
      } else {
        this.getUserInfo({ userId: this.userId, password: this.pwValue });
        if (this.userInfo.password == this.pwValue) {
          this.$emit("input-change");
        } else {
          alert("잘못된 비밀번호입니다.");
        }
      }
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style>
.inner {
  position: absolute;
  top: 30%;
  transform: translateY(-50%);
}
.top-confirm {
  margin-top: 90px;
}
.body-confirm {
  margin-top: 110px;
  margin-left: 20px;
  margin-right: 20px;
}
</style>

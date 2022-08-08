<template>
  <!-- eslint-disable -->
  <v-container>
    <div>
      <div>
        <v-subheader>개인정보를 확인하거나 수정하려면<br /></v-subheader>
        <v-subheader class="mt-n6">비밀번호를 입력하세요.</v-subheader>
      </div>
      <v-row align="center">
        <v-col cols="3"><h5>비밀번호</h5></v-col>
        <v-col>
          <v-text-field id="pw-value" type="password" v-model="pwValue" dense></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col align="center">
          <v-btn small rounded @click="confirmPw">계정 정보 수정</v-btn>
        </v-col>
      </v-row>
    </div>
  </v-container>
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
        // 비밀번호 비교 후 동일하면 컴포넌트 변경
        this.getUserInfo({ userId: this.userId, password: this.pwValue });
        if (this.userInfo.password == this.pwValue) {
          this.$emit("input-change");
          console.log(this.userInfo);
        } else {
          alert("잘못된 비밀번호입니다.");
        }
      }
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
</style>

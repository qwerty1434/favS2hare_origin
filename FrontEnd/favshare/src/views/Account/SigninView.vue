<template>
  <div>
    <!-- 로고 -->
    <h3>FavS2hare</h3>
    <!-- Login Form -->
    <form>
      <div class="input-sheet">
        email
        <input type="text" v-model="user.email" />
      </div>
      <div class="input-sheet">
        PW
        <input type="password" v-model="user.password" />
      </div>
      <v-btn @click.prevent="confirm" color="#ff5d5d" class="btn-color" rounded
        >로그인</v-btn
      >
    </form>
    <!-- 소셜 로그인 버튼 -->
    <!-- 회원가입, 비밀번호찾기로 이동 -->
    <router-link to="signup">아직 FavS2hare 회원이 아니신가요?</router-link>
    <br />
    <router-link to="findpw">비밀번호가 기억나지 않아요.</router-link>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "SigninView",
  data() {
    return {
      user: {
        email: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(["isSignin"]),
  },
  methods: {
    ...mapActions(["userConfirm", "getUserInfo"]),
    async confirm() {
      // 로그인
      await this.userConfirm(this.user);
      // sessionStorage에 생긴 토큰으로 유저 정보 갱신
      let token = sessionStorage.getItem("access-token");
      if (this.isSignin) {
        await this.getUserInfo(token);
        // home으로 자동 이동
        this.$router.push({ name: "home " });
      }
    },
  },
};
</script>
<style scoped>
.input-sheet {
  width: 75%;
  height: 30px;
  background-color: #ffe3a9;
  border-radius: 25px;
}

.btn-color {
  color: white;
}
</style>

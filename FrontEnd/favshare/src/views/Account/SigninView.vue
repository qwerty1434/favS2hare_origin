<template>
  <div>
    <!-- 로고 -->
    <h3>FavS2hare</h3>
    <!-- Login Form -->
    <form>
      <div class="input-sheet">
        email
        <input type="text" v-model="email" />
      </div>
      <div class="input-sheet">
        PW
        <input type="password" v-model="password" />
      </div>
      <v-btn @click.prevent="signin" color="#ff5d5d" class="btn-color" rounded
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
// import axios from "axios";

export default {
  name: "SigninView",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    signin() {
      console.log(this.email);
      console.log(this.password);
      // axios
      //   .get("http://localhost:8080/user/login")
      //   .then((res) => {
      //     console.log(res);
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
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

<template>
  <div>
    <router-link to="signin">뒤로가기</router-link>
    <!-- 로고 -->
    <h3>FavS2hare</h3>
    <!-- 인증번호발송 Form -->
    <form>
      <div class="input-sheet">
        email
        <input type="text" v-model="email" />
      </div>
      <v-btn
        @click.prevent="sendAuthNumber"
        color="#ff5d5d"
        class="btn-color"
        rounded
        >인증번호 발송</v-btn
      >
    </form>
    <!-- 인증번호확인 Form -->
    <form>
      <div class="input-sheet">
        인증번호
        <input type="text" v-model="authNumber" />
      </div>
      <v-btn
        @click.prevent="checkAuthNumber"
        color="#ff5d5d"
        class="btn-color"
        rounded
        >확인</v-btn
      >
    </form>
    <!-- Signup Form -->
    <form>
      <div class="input-sheet">
        PW
        <input type="password" v-model="password" />
      </div>
      <div class="input-sheet">
        PW 확인
        <input type="password" v-model="password2" />
      </div>
      <div class="input-sheet">
        이름
        <input type="text" v-model="username" />
      </div>
      <div class="input-sheet">
        닉네임
        <input type="text" v-model="nickname" />
      </div>
      <div class="input-sheet">
        생년월일
        <input type="text" v-model="birthday" />
      </div>
      <div class="input-sheet">
        전화번호
        <input type="text" v-model="phone" />
      </div>
      <v-btn @click.prevent="signup" color="#ff5d5d" class="btn-color" block
        >Join</v-btn
      >
    </form>
  </div>
</template>

<script>
// import axios from "axios";

export default {
  name: "SignupView",
  data() {
    return {
      email: "",
      authNumber: "",
      password: "",
      password2: "",
      username: "",
      nickname: "",
      birthday: "",
      phone: "",
    };
  },
  methods: {
    // 인증번호 발송
    sendAuthNumber() {
      console.log(this.email);
    },
    // 인증번호 확인
    checkAuthNumber() {
      console.log(this.authNumber);
    },
    // 회원가입
    signup() {
      console.log(this.email);
      console.log(this.password);
      console.log(this.password2);
      console.log(this.username);
      console.log(this.nickname);
      console.log(this.birthday);
      console.log(this.phone);
      // axios
      //   .post("http://localhost:8080/user/signup", {
      //     email: this.email,
      //     password: this.password,
      //     password2: this.password2,
      //     username: this.username,
      //     nickname: this.nickname,
      //     birthday: this.birthday,
      //     phone: this.phone,
      //   })
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

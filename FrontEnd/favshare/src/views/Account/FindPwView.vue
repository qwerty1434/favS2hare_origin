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
    <!-- 비밀번호재설정 Form -->
    <form>
      <div class="input-sheet">
        비밀번호
        <input ref="password" type="password" v-model="password" disabled />
      </div>
      <div class="input-sheet">
        비밀번호 확인
        <input ref="password2" type="password" v-model="password2" disabled />
      </div>
      <v-btn
        @click.prevent="changePassword"
        color="#ff5d5d"
        class="btn-color"
        block
        >비밀번호 변경하기</v-btn
      >
    </form>
  </div>
</template>

<script>
export default {
  name: "FindPwView",
  data() {
    return {
      email: "",
      authNumber: "",
      password: "",
      password2: "",
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
      // 확인이 되었다면, 비밀번호 입력창 활성화
      const passwordTag1 = this.$refs.password;
      const passwordTag2 = this.$refs.password2;
      passwordTag1.disabled = false;
      passwordTag2.disabled = false;
    },
    // 비밀번호 변경
    changePassword() {
      console.log(this.password);
      console.log(this.password2);
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

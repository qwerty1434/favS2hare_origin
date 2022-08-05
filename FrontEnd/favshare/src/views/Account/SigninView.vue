<template>
  <v-container>
    <!-- 로고 -->
    <v-row class="logo-part text-center">
      <v-col>
        <h3>FavS2hare</h3>
      </v-col>
    </v-row>
    <!-- Login Form -->
    <v-form ref="form">
      <v-row no-gutters>
        <v-col offset="1" cols="10">
          <v-text-field
            class="text-field"
            v-model="user.email"
            prepend-inner-icon="mdi-account-box"
            :rules="[rules.idRequired, rules.idIsEmail]"
            label="ID"
            placeholder="e-mail@example.com"
            background-color="#FFE3A9"
            filled
            rounded
            dense
          ></v-text-field>
        </v-col>
        <v-col offset="1" cols="10">
          <v-text-field
            class="text-field"
            v-model="user.password"
            prepend-inner-icon="mdi-lock-question"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.pwRequired, rules.pwLength]"
            label="PW"
            background-color="#FFE3A9"
            :type="showPassword ? 'text' : 'password'"
            filled
            rounded
            dense
            @click:append="showPassword = !showPassword"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-form>
    <v-row>
      <v-col class="text-center" cols="12">
        <v-btn
          @click.prevent="confirm"
          color="#ff5d5d"
          class="signin-button"
          rounded
          >로그인</v-btn
        >
      </v-col>
    </v-row>
    <!-- 소셜 로그인 버튼 -->
    <v-row class="social-icon-container">
      <v-col class="d-flex justify-center">
        <img
          src="@/assets/google.png"
          @click="googleSignin"
          alt="google"
          class="social-icon"
        />
        <img
          src="@/assets/facebook.png"
          @click="facebookSignin"
          alt="facebook"
          class="social-icon"
        />
        <img
          src="@/assets/twitter.png"
          @click="twitterSignin"
          alt="twitter"
          class="social-icon"
        />
      </v-col>
    </v-row>
    <!-- 회원가입, 비밀번호찾기로 이동 -->
    <v-row class="message-container">
      <v-col class="text-center" cols="12">
        <router-link to="signup">아직 FavS2hare 회원이 아니신가요?</router-link>
      </v-col>
      <v-col class="text-center" cols="12">
        <router-link to="findpw">비밀번호가 기억나지 않아요.</router-link>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "SigninView",
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
      showPassword: false,
      rules: {
        idRequired: (value) => !!value || "ID를 입력해주세요",
        idIsEmail: (value) =>
          this.checkEmail(value) || "이메일 형식에 맞지 않는 아이디입니다",
        pwRequired: (value) => !!value || "PW를 입력해주세요",
        pwLength: (value) =>
          (value.length >= 8 && value.length <= 16) ||
          "PW는 8~16자로 작성해주세요",
      },
    };
  },
  computed: {
    ...mapState(["isSignin"]),
  },
  methods: {
    ...mapActions(["userConfirm", "getUserInfo"]),
    async confirm() {
      // 유효성 검사를 통과할 경우
      if (this.$refs.form.validate()) {
        // 로그인
        await this.userConfirm(this.user);
        // sessionStorage에 생긴 토큰으로 유저 정보 갱신
        let token = sessionStorage.getItem("access-token");
        if (this.isSignin) {
          await this.getUserInfo(token);
          // home으로 자동 이동
          this.$router.push({ name: "home " });
        }
      } else {
        alert("올바른 형식의 아이디/비밀번호를 입력해주세요");
        this.user.email = "";
        this.user.password = "";
      }
    },
    checkEmail(email) {
      // 정규식으로 이메일 확인
      /* eslint-disable-next-line */
      const reg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      return reg.test(email);
    },
    googleSignin() {
      alert("Google Signin");
    },
    facebookSignin() {
      alert("Facebook Signin");
    },
    twitterSignin() {
      alert("Twitter Signin");
    },
  },
};
</script>
<style scoped>
.logo-part {
  height: 220px;
  line-height: 220px;
}

.text-field {
  margin-bottom: 10px;
}

.signin-button {
  color: white;
}

.social-icon {
  height: 32px;
  width: 32px;
  margin-right: 20px;
  margin-left: 20px;
}

.social-icon-container {
  margin-top: 10px;
}

.message-container {
  margin-top: 70px;
}
</style>

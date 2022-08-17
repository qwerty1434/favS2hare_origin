<template>
  <div>
    <v-container>
      <!-- 뒤로가기 -->
      <div v-if="!isConfirmed" class="bar-top">
        <router-link to="signin">
          <v-icon>mdi-keyboard-backspace</v-icon>
        </router-link>
      </div>
      <div v-if="isConfirmed" class="bar-top">
        <v-icon @click="backToSignup">mdi-keyboard-backspace</v-icon>
      </div>
      <!-- 로고 -->
      <v-row class="logo-part text-center">
        <v-col>
          <h3>FavS2hare</h3>
        </v-col>
      </v-row>
      <!-- 인증번호전송 Form -->
      <v-form ref="sendingForm" v-if="!isConfirmed">
        <v-row no-gutters>
          <v-col offset="1" cols="10">
            <v-text-field
              id="idInput"
              v-model="user.email"
              prepend-inner-icon="mdi-account-box"
              :rules="[rules.isEmail]"
              label="ID"
              placeholder="e-mail@example.com"
              background-color="#FFE3A9"
              filled
              rounded
              dense
              @keydown.enter.prevent="sendAuthNumber"
              @input="resetAuthNumber"
            ></v-text-field>
          </v-col>
          <v-col v-if="!isSent" class="text-center" offset="7" cols="4">
            <v-btn
              @click.prevent="sendAuthNumber"
              color="#ff5d5d"
              class="btn"
              rounded
              dark
              >인증번호 발송</v-btn
            >
          </v-col>
        </v-row>
      </v-form>
      <!-- 인증번호확인 Form -->
      <v-form v-if="isSent && !isConfirmed" ref="confirmForm">
        <v-row no-gutters>
          <v-col offset="1" cols="10">
            <v-text-field
              id="authNumberInput"
              v-model="user.authNumber"
              :rules="[rules.authNumberLength]"
              label="인증번호"
              placeholder="_ _ _ _ _ _ _ _ _ _ _ _ _ _ _"
              background-color="#FFE3A9"
              filled
              rounded
              dense
              @keydown.enter.prevent="checkAuthNumber"
            ></v-text-field>
          </v-col>
          <v-col class="text-center" offset="7" cols="4">
            <v-btn
              id="confirmButton"
              @click.prevent="checkAuthNumber"
              color="#ff5d5d"
              class="btn"
              rounded
              dark
              >확인</v-btn
            >
          </v-col>
        </v-row>
      </v-form>
      <!-- Signup Form -->
      <v-form ref="signupForm" v-if="isConfirmed">
        <v-row no-gutters>
          <v-col offset="1" cols="10">
            <v-text-field
              v-model="user.password"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.pwLength]"
              label="PW"
              background-color="#FFE3A9"
              :type="showPassword ? 'text' : 'password'"
              filled
              rounded
              dense
              @click:append="showPassword = !showPassword"
            ></v-text-field>
          </v-col>
          <v-col offset="1" cols="10">
            <v-text-field
              v-model="password2"
              :rules="[rules.pwSame]"
              label="PW 확인"
              background-color="#FFE3A9"
              type="password"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
          <v-col offset="1" cols="10">
            <v-text-field
              v-model="user.username"
              label="이름"
              background-color="#FFE3A9"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
          <v-col offset="1" cols="10">
            <v-text-field
              v-model="user.nickname"
              label="닉네임"
              background-color="#FFE3A9"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
          <v-col offset="1" cols="10">
            <v-text-field
              v-model="user.birthDate"
              :rules="[rules.isBirthDate]"
              label="생년월일"
              placeholder="YYYY-MM-DD"
              background-color="#FFE3A9"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
          <v-col offset="1" cols="10">
            <v-text-field
              v-model="user.phone"
              :rules="[rules.phoneNumber]"
              label="전화번호"
              placeholder="- 를 포함하여 입력해주세요"
              background-color="#FFE3A9"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
    <v-btn
      v-if="isConfirmed"
      @click.prevent="signup"
      color="#ff5d5d"
      height="50"
      block
      dark
      tile
      >Sign Up</v-btn
    >
  </div>
</template>

<script>
import axios from "axios";
/* eslint-disable */

export default {
  name: "SignupView",
  data() {
    return {
      user: {
        email: "",
        authNumber: "",
        password: "",
        name: "",
        nickname: "",
        birthDate: "",
        phone: "",
      },
      password2: "",
      rules: {
        isEmail: (value) => this.checkEmail(value) || "ID는 이메일 형식입니다",
        authNumberLength: (value) =>
          value.length == 15 || "인증번호는 15자입니다",
        pwLength: (value) =>
          (value.length >= 9 && value.length <= 16) ||
          "PW는 8~16자로 작성해주세요",
        pwSame: (value) =>
          value === this.user.password || "PW와 일치하지 않습니다",
        isBirthDate: (value) =>
          this.checkBirthDate(value) || "올바르지 않은 형식의 생년월일입니다",
        phoneNumber: (value) =>
          this.checkPhoneNumber(value) || "올바르지 않은 형식의 전화번호입니다",
      },
      // 인증번호를 발송했는지 여부
      isSent: false,
      // 이메일 인증이 완료되었는지 여부
      isConfirmed: false,
      showPassword: false,
      // 이메일로 발급된 인증번호
      receivedAuthNumber: "",
    };
  },
  methods: {
    // 이메일 형식인지 확인
    checkEmail(email) {
      /* eslint-disable-next-line */
      const reg =
        /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      return reg.test(email);
    },
    // 생일 형식(YYYY-MM-DD) 확인
    checkBirthDate(birthDate) {
      /* eslint-disable-next-line */
      const reg =
        /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
      return reg.test(birthDate);
    },
    // 전화번호 형식(010-0000-0000) 확인
    checkPhoneNumber(phone) {
      /* eslint-disable-next-line */
      const reg = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/;
      return reg.test(phone);
    },
    // 인증번호 발송
    sendAuthNumber() {
      // 유효성 검사를 통과할 경우
      if (this.$refs.sendingForm.validate()) {
        // 가입된 사용자인지 확인
        // 가입된 사용자 => then, 가입되지 않은 사용자 catch
        // 의문: 모든 에러가 catch로 올텐데, "가입되지 않은 ID"임을 catch로 받아도 될까?
        // 에러 타입에 따라 if 문을 넣어야하나?
        // 400 에러 뜨면 통과인듯...?
        axios
          // .get(`http://13.124.112.241:8080/user/signup/${this.user.email}`)
          .get(`http://13.124.112.241:8080/user/signup/${this.user.email}`)
          .then(() => {
            console.log(
              "사용자에게는 이미 가입된 경우만 alert가 보여야 합니다"
            );
            alert("이미 가입된 ID입니다");
          })
          .catch((error) => {
            console.log("가입된 사용자 확인 API 에러: ", error);
            // 가입되지 않은 사용자라면 인증번호 요청
            // 500 에러가 뜨는 기묘한 상황
            axios
              .get(
                // `http://13.124.112.241:8080/user/password/sendAuth/${this.user.email}`
                `http://13.124.112.241:8080/user/password/sendAuth/${this.user.email}`
              )
              .then((response) => {
                // 요청 결과로 받은 인증번호 저장
                this.receivedAuthNumber = response.data;
                this.isSent = true;
              })
              .catch((error) => {
                console.log("인증번호 전송 API 에러: ", error);
              });
          });
        // 유효성 검사 통과 못한 경우
      } else {
        this.user.email = "";
        alert("이메일 형식의 ID를 입력해주세요");
      }
    },
    // 인증번호 확인
    checkAuthNumber() {
      // 유효성 검사를 통과할 경우
      if (this.$refs.confirmForm.validate()) {
        // 사용자가 입력한 인증번호와 응답받은 인증번호가 일치할 때
        if (this.user.authNumber === this.receivedAuthNumber) {
          this.isConfirmed = true;
        } else {
          this.user.authNumber = "";
          alert("잘못된 인증번호입니다");
        }
      } else {
        this.user.authNumber = "";
        alert("인증번호의 자리수를 확인해주세요");
      }
    },
    // 회원가입
    async signup() {
      // 유효성 검사를 통과할 경우
      if (this.$refs.signupForm.validate()) {
        await axios
          .post(
            // "http://13.124.112.241:8080/user/signup",
            "http://13.124.112.241:8080/user/signup",
            JSON.stringify(this.user),
            {
              headers: {
                "Content-Type": "application/json",
              },
            }
          )
          .then(() => {
            // 회원가입 성공시 로그인 화면으로 이동
            alert("성공적으로 회원가입이 완료되었습니다");
            this.$router.push({ name: "signin" });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("입력한 내용을 다시 확인해주세요");
      }
    },
    resetAuthNumber() {
      this.receivedAuthNumber = "";
      this.user.authNumber = "";
      this.isSent = false;
    },
    backToSignup() {
      (this.user.authNumber = ""),
        (this.user.password = ""),
        (this.password2 = ""),
        (this.user.username = ""),
        (this.user.nickname = ""),
        (this.user.birthDate = ""),
        (this.user.phone = ""),
        (this.receivedAuthNumber = ""),
        (this.isSent = false);
      this.isConfirmed = false;
    },
  },
};
</script>

<style scoped>
.bar-top {
  padding: 15px;
}

.logo-part {
  height: 220px;
  line-height: 220px;
  margin-bottom: 30px;
}

.btn {
  width: 100%;
  margin-bottom: 30px;
}
</style>

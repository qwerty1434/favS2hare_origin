<template>
  <div class="LRM">
    <div class="LRM-font ma">로그인이 필요합니다</div>
    <div class="su">
      <div class="logo-container">
        <img class="logo" src="@/assets/favshare.png" alt="Logo" />
      </div>
      <div class="LRM-font font-container">의 다양한 서비스를 경험해보세요</div>
    </div>
    <div class="LRM-font">
      <v-btn @click="goSignin" color="#ff5d5d" dark rounded
        >로그인하러 가기</v-btn
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginRequestMessage",
  data() {
    return {};
  },
  methods: {
    goSignin() {
      this.$router.push({ name: "signin" });
    },
  },
};
</script>

<style scoped>
.LRM {
  text-align: center;
}

.LRM-font {
  font-family: "Cafe24Ssurround", sans-serif !important;
}

.ma {
  font-size: 30px;
  margin-top: 200px;
}

.su {
  font-size: 15px;
  margin-top: 7px;
  margin-bottom: 40px;
}

.logo-container {
  display: inline-block;
  vertical-align: middle;
  margin-right: 5px;
}

.logo {
  height: 26px;
}

.font-container {
  display: inline-block;
}

@font-face {
  font-family: "Cafe24Ssurround";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>

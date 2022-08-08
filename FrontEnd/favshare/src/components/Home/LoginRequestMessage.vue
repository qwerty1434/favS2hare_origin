<template>
  <div class="LRM">
    <div class="LRM-font ma">로그인이 필요합니다</div>
    <div class="LRM-font su">FavS2hare의 다양한 서비스를 경험해보세요</div>
    <!-- <router-link to="/signin" active-class="active-login">
      <button>로그인 하러 가기</button>
    </router-link> -->
    <div class="LRM-font rl text-center">
      <v-btn rounded> 로그인 하러 가기 </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginRequestMessage",
  data() {
    return {};
  },
};
</script>

<style scoped>
.LRM {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  height: 100%;
}
.LRM-font {
  font-family: "Cafe24Ssurround", sans-serif !important;
  text-align: center;
}
.ma {
  font-size: 30px;
}
.su {
  font-size: 15px;
}
.rl {
  color: #ff5d5d !important;
}
@font-face {
  font-family: "Cafe24Ssurround";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.active-login {
  color: #ff5d5d !important;
  background-color: white;
}
</style>

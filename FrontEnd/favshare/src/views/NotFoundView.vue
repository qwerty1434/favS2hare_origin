<template>
  <!-- eslint-disable -->
  <v-container>
    <div class="main-box">
      <v-row class="err-font">
        <v-col align="center">
          <h1>404 Not Found</h1>
        </v-col>
      </v-row>
      <v-row class="mt-n5 err-font-min">
        <v-col align="center">
          <h4>요청하신 페이지를 찾을 수 없습니다 ㅠㅠ</h4>
        </v-col>
      </v-row>
      <v-row>
        <v-col align="center">
          <v-btn color="#ff5d5d" outlined elevation="5" @click="goHome">홈으로 이동</v-btn>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "NotFoundView",
  methods: {
    goHome() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
.err-font {
  color: #ff5d5d;
}
.err-font-min {
  color: #8a8484;
}
.main-box {
  margin-top: 200px;
}
</style>

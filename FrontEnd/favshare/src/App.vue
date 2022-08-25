<template>
  <v-app class="v-application">
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  }),
};
</script>

<style>
.v-application {
  font-family: "SpoqaHanSansNeo", sans-serif !important;
}

@font-face {
  font-family: "SpoqaHanSansNeo";
  src: url("assets/fonts/SpoqaHanSansNeo-Thin.ttf") format("truetype");
  font-weight: 100;
}

@font-face {
  font-family: "SpoqaHanSansNeo";
  src: url("assets/fonts/SpoqaHanSansNeo-Light.ttf") format("truetype");
  font-weight: 200;
}

@font-face {
  font-family: "SpoqaHanSansNeo";
  src: url("assets/fonts/SpoqaHanSansNeo-Regular.ttf") format("truetype");
  font-weight: 400;
}

@font-face {
  font-family: "SpoqaHanSansNeo";
  src: url("assets/fonts/SpoqaHanSansNeo-Medium.ttf") format("truetype");
  font-weight: 500;
}

@font-face {
  font-family: "SpoqaHanSansNeo";
  src: url("assets/fonts/SpoqaHanSansNeo-Bold.ttf") format("truetype");
  font-weight: 700;
}
</style>

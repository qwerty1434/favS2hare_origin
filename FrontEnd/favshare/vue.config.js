const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  //이렇게 추가?
  // devServer: {
  //   disableHostCheck: true,
  // },
});

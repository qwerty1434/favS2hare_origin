const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  devServer: {
    allowedHosts: "all",
    historyApiFallback: true,
  },
  chainWebpack: (config) => {
    config.module.rules.delete("eslint");
  },
});

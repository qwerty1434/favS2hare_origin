import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueYoutube from "vue-youtube";
// import firebase from "firebase/compat/app";

// const firebaseConfig = {
//   apiKey: "AIzaSyCj3WWR7IRe4nXKGzX5QppcRImaewZuqlk",
//   authDomain: "favshare.firebaseapp.com",
//   projectId: "favshare",
//   storageBucket: "favshare.appspot.com",
//   messagingSenderId: "868608428599",
//   appId: "1:868608428599:web:0f13ccb7971f4251ee8db2",
//   measurementId: "G-QXF5YX26E9",
// };

// firebase.initializeApp(firebaseConfig);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

Vue.use(VueYoutube);

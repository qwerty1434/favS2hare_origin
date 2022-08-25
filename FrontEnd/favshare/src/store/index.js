import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import createPersistedState from "vuex-persistedstate";

import search from "./modules/search";
import home from "./modules/home";
import profile from "./modules/profile";
import account from "./modules/account";
import myvideo from "./modules/myvideo";
import pops from "./modules/pops";
import dialog from "./modules/dialog";

export default new Vuex.Store({
  state: {
    bottomValue: 1,
  },
  getters: {
    bottomValue: (state) => state.bottomValue,
  },
  mutations: {
    SET_BOTTOMVALUE: (state, value) => (state.bottomValue = value),
  },
  actions: {
    fetchBottomValue({ commit }, value) {
      commit("SET_BOTTOMVALUE", value);
    },
  },
  modules: {
    search: search,
    home: home,
    profile: profile,
    account: account,
    myvideo: myvideo,
    pops: pops,
    dialog: dialog,
  },
  plugins: [
    createPersistedState({
      paths: ["search", "home", "profile", "account", "myvideo", "pops"],
      storage: sessionStorage,
    }),
  ],
});

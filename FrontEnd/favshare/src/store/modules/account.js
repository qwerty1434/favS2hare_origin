import axios from "axios";
import jwt_decode from "jwt-decode";
import api from "@/api/springRestAPI";

export default {
  state: {
    isSignin: false,
    userId: 0,
    isSigninError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    userId: (state) => state.userId,
    userInfo: (state) => state.userInfo,
    isSignin: (state) => state.isSignin,
  },
  mutations: {
    SET_IS_SIGNIN: (state, isSignin) => {
      state.isSignin = isSignin;
    },
    SET_IS_SIGNIN_ERROR: (state, isSigninError) => {
      state.isSigninError = isSigninError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    SET_USER: (state, user) => {
      state.user = user;
    },
    SET_USERID: (state, userId) => {
      state.isSignin = true;
      state.userId = userId;
    },
    RESET_FORM: (state, userInfo) => {
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await axios
        .post(api.user.login(), JSON.stringify(user), {
          headers: {
            "Content-Type": "application/json",
          },
          data: {
            email: user.email,
            password: user.password,
          },
        })
        .then((response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_SIGNIN", true);
            commit("SET_IS_SIGNIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
            commit("SET_USERID", jwt_decode(token).userId);
          } else {
            commit("SET_IS_SIGNIN", false);
            commit("SET_IS_SIGNIN_ERROR", true);
          }
        })
        .catch((response) => {
          console.log(response);
        });
    },
    getUserInfo({ commit }, { userId, password }) {
      axios({
        method: "post",
        url: api.userInfo.confirmPw(),
        data: {
          id: userId,
          password: password,
        },
      })
        .then((res) => {
          console.log("succ");
          commit("SET_USER_INFO", res.data);
        })
        .catch(() => {
          console.log(userId);
          console.log("fail");
          commit("SET_USER_INFO", null);
        });
    },
    updateUserInfo({ commit }, { userId, password, name, phone, birthDate }) {
      console.log(userId);
      console.log(name);
      console.log(password);
      console.log(phone);
      console.log(birthDate);
      axios({
        method: "put",
        url: api.userInfo.editProfile(),
        data: {
          id: userId,
          password: password,
          name: name,
          phone: phone,
          birthDate: birthDate,
        },
      })
        .then((res) => {
          commit("RESET_FORM");
        })
        .catch((err) => {
          console.log(err);
          commit(err);
        });
    },
  },
};

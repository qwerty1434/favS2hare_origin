import axios from "axios";
import jwt_decode from "jwt-decode";

export default {
  state: {
    isSignin: false,
    isSigninError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_SIGNIN: (state, isSignin) => {
      state.isSignin = isSignin;
    },
    SET_IS_SIGNIN_ERROR: (state, isSigninError) => {
      state.isSigninError = isSigninError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isSignin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await axios
        // 주소 변경 필요
        .post("http://localhost:8080/user/login", JSON.stringify(user), {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_SIGNIN", true);
            commit("SET_IS_SIGNIN_ERROR", false);
            // sessionStorage에 access token 저장
            // sessionStorage: 탭 단위 저장소, 새로고침에는 데이터가 사라지지 않고, 탭을 닫으면 사라짐
            // localStorage: 모든 탭과 창에서 데이터 공유, 브라우저나 OS 재시작해도 데이터 유지
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_SIGNIN", false);
            commit("SET_IS_SIGNIN_ERROR", true);
          }
        })
        .catch(() => {});
    },
    async getUserInfo({ commit }, token) {
      let decoded_token = jwt_decode(token);
      await axios
        .get(`http://localhost:8080/user/Info/${decoded_token.userid}`, {
          headers: {
            "Content-Type": "application/json",
            "access-token": sessionStorage.getItem("access_token"),
          },
        })
        .then((response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

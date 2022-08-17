<template>
  <div>
    <v-app-bar color="white" elevation="0" dense>
      <router-link :to="{ name: 'home' }" active-class="navbar-active">
        <img class="navbar__logo" src="@/assets/favshare.png" alt="Logo" />
      </router-link>

      <v-spacer></v-spacer>

      <!-- <v-btn icon @click="goAlarm">
        <v-icon>mdi-alarm</v-icon>
      </v-btn> -->

      <v-menu transition="slide-y-transition" bottom dense>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on" @click="openModal">
            <v-icon>mdi-account</v-icon>
          </v-btn>
        </template>

        <v-list v-if="this.userId != 0" class="pa-0" align="center" dense>
          <v-list-item class="pa-1">
            <v-avatar class="ml-2" color="red" size="25">
              <img :src="userInfo.profileImageUrl" alt="John" />
            </v-avatar>
            <v-list-item-title>{{ userInfo.nickname }}</v-list-item-title>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="goMyVideo">
            <v-list-item-title>저장된 영상</v-list-item-title>
          </v-list-item>
          <v-list-item @click="goMyAccount">
            <v-list-item-title>계정 정보</v-list-item-title>
          </v-list-item>
          <v-list-item @click="goMyInterest">
            <v-list-item-title>관심사 변경</v-list-item-title>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="btnLogout">
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>
        </v-list>
        <v-list v-else class="pa-0" align="center" dense>
          <v-list-item @click="btnLogin">
            <v-list-item-title>로그인</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <v-divider></v-divider>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "NavBar",
  data() {
    return {
      userInfo: {},
    };
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  created() {},
  methods: {
    goAlarm() {
      this.$router.push({ name: "alarm" });
    },
    openModal() {
      axios({
        method: "get",
        url: `http://13.124.112.241:8080/user/profile/${this.userId}`,
        data: {
          userId: this.userId,
        },
      }).then((res) => {
        this.userInfo = res.data;
        console.log(this.userInfo);
      });
    },
    goMyVideo() {
      this.$router.push({ name: "myyoutube" });
    },
    goMyAccount() {
      this.$router.push({ name: "account" });
    },
    goMyInterest() {
      this.$router.push({ name: "interest" });
    },
    btnLogout() {
      this.$store.commit("SET_USERID", 0);
      this.$store.commit("SET_USER_INFO", null);
      this.$store.commit("SET_IS_SIGNIN", false);
      this.$router.push({ name: "home" });
    },
    btnLogin() {
      this.$router.push({ name: "signin" });
    },
  },
};
</script>

<style>
.class {
  margin: 0;
}

a {
  text-decoration: none;
  color: black;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 8px 12px;
}

.navbar__logo {
  height: 30px;
}

.navbar__icons {
  padding: 8px 10px;
}

.navbar-active {
  color: black !important;
}

.nav-font {
  font-family: "Bingrae", sans-serif !important;
  text-align: center;
  font-size: 20px;
}
@font-face {
  font-family: "Bingrae";
  src: url("@/assets/fonts/Binggrae-Bold.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}
</style>

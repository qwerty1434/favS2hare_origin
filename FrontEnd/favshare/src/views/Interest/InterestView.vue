<template>
  <div>
    <v-app-bar color="white" elevation="0" dense>
      <router-link :to="{ name: 'home' }" active-class="navbar-active">
        <img class="navbar__logo" src="@/assets/favshare.png" alt="Logo" />
      </router-link>
    </v-app-bar>
    <v-divider></v-divider>
    <v-container class="container">
      <v-row align="center" no-gutters>
        <v-col v-if="currentPage == 1" cols="1">
          <v-icon>mdi-circle-small</v-icon>
        </v-col>
        <v-col v-if="currentPage > 1" cols="1">
          <v-icon @click="previousPage">mdi-keyboard-backspace</v-icon>
        </v-col>
        <v-col cols="11">
          <div class="progress-bar">
            <interest-progress
              :currentPage="currentPage"
              :totalPage="totalPage"
            ></interest-progress>
          </div>
        </v-col>
      </v-row>
      <v-row class="question-part" no-gutters>
        <v-col v-if="currentPage === 1" cols="12" class="text-center">
          <h3>당신이 선호하는 가수는?</h3>
        </v-col>
        <v-col v-if="currentPage === 2" cols="12" class="text-center">
          <h3>당신이 선호하는 노래는?</h3>
        </v-col>
      </v-row>
      <v-row
        v-if="currentPage === 1"
        class="search-bar"
        justify="space-between"
        align="center"
        no-gutters
      >
        <v-col cols="10">
          <input
            type="text"
            v-model="keyword"
            class="search-input"
            placeholder="검색"
            @keydown.enter.prevent="search"
          />
        </v-col>
        <v-col cols="1">
          <v-icon @click.prevent="search" color="#FF5D5D" dense
            >mdi-magnify</v-icon
          >
        </v-col>
      </v-row>
      <idol-choice-list
        ref="idolChoiceList"
        v-if="currentPage === 1"
        :idolList="idolList"
        @emitSelectIdol="addIdolInList"
        @emitUnselectIdol="removeIdolInList"
      ></idol-choice-list>
      <Song-choice-list
        v-if="currentPage === 2"
        :songList="songList"
        @emitSelectSong="addSongInList"
        @emitUnselectSong="removeSongInList"
      ></Song-choice-list>
    </v-container>
    <v-footer fixed padless>
      <v-btn
        v-if="currentPage === 1"
        @click.prevent="nextPage"
        color="#FF5D5D"
        height="50"
        dark
        block
        tile
        >Continue</v-btn
      >
      <v-btn
        v-if="currentPage === totalPage"
        @click.prevent="sendInterest"
        color="#FF5D5D"
        height="50"
        dark
        block
        tile
        >Finish</v-btn
      >
    </v-footer>
  </div>
</template>

<script>
import axios from "axios";

import InterestProgress from "@/components/Interest/InterestProgress.vue";
import IdolChoiceList from "@/components/Interest/IdolChoiceList.vue";
import SongChoiceList from "@/components/Interest/SongChoiceList.vue";
import { mapGetters } from "vuex";
import api from "@/api/springRestAPI";

export default {
  name: "InterestView",
  components: {
    InterestProgress,
    IdolChoiceList,
    SongChoiceList,
  },
  data() {
    return {
      currentPage: 1,
      totalPage: 2,
      keyword: "",
      idolList: [],
      songList: [],
      idolChoiceList: [],
      songChoiceList: [],
    };
  },
  mounted() {
    this.getIdolList();
    this.getSongList();
  },
  watch: {
    keyword(newVal) {
      if (!newVal) {
        this.getIdolList();
        this.$refs.idolChoiceList.isSearching = false;
      }
    },
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  methods: {
    getIdolList() {
      axios
        .get(api.userInterest.getIdolInfo())
        .then((response) => {
          this.idolList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 좋아하는 노래 선택사항 요청
    getSongList() {
      axios
        .get(api.userInterest.getInterestSong())
        .then((response) => {
          this.songList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addIdolInList(id) {
      this.idolChoiceList.push(id);
    },
    removeIdolInList(id) {
      const idx = this.idolChoiceList.indexOf(id);
      this.idolChoiceList.splice(idx, 1);
    },
    addSongInList(id) {
      this.songChoiceList.push(id);
    },
    removeSongInList(id) {
      const idx = this.songChoiceList.indexOf(id);
      this.songChoiceList.splice(idx, 1);
    },
    search() {
      axios
        .get((response) => {
          // "선택 안함" 안보이게 하기
          this.$refs.idolChoiceList.isSearching = true;
          this.idolList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    nextPage() {
      this.currentPage = 2;
    },
    previousPage() {
      this.currentPage = 1;
      this.idolChoiceList = [];
      this.songChoiceList = [];
    },
    // 사용자 선택 전송
    // 각각 사용자가 선택한 id들을 담고 있고, 정렬되어 있지 않은 상태
    sendInterest() {
      axios
        .post(api.userInterest.selectInterest(), {
          userId: this.userId,
          idolList: this.idolChoiceList,
          songList: this.songChoiceList,
        })
        .then(() => {
          alert("관심사 등록이 완료되었습니다");
          this.$router.push({ name: "home" });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.container {
  padding: 23px;
}

.logo-part {
  height: 65px;
  line-height: 65px;
  padding-left: 15px;
}

.logo {
  height: 30px;
}

.progress-bar {
  padding-left: 10px;
}

.question-part {
  height: 110px;
  line-height: 110px;
}

.search-bar {
  height: 27px;
  background-color: #efeff0;
  border-radius: 16px;
}

.search-input {
  outline: none;
  padding-left: 13px;
  font-size: 13px;
}
</style>

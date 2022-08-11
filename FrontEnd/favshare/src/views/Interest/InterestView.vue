<template>
  <div>
    <!-- 로고 -->
    <div class="logo-part">
      <h3>FavS2hare</h3>
    </div>
    <v-divider></v-divider>
    <v-container class="container">
      <v-row align="center" no-gutters>
        <!-- back icon -->
        <v-col v-if="currentPage == 1" cols="1">
          <v-icon>mdi-circle-small</v-icon>
        </v-col>
        <v-col v-if="currentPage > 1" cols="1">
          <v-icon @click="previousPage">mdi-keyboard-backspace</v-icon>
        </v-col>
        <!-- progress bar -->
        <v-col cols="11">
          <div class="progress-bar">
            <interest-progress
              :currentPage="currentPage"
              :totalPage="totalPage"
            ></interest-progress>
          </div>
        </v-col>
      </v-row>
      <!-- question -->
      <v-row class="question-part" no-gutters>
        <v-col v-if="currentPage === 1" cols="12" class="text-center">
          <h3>당신이 선호하는 가수는?</h3>
        </v-col>
        <v-col v-if="currentPage === 2" cols="12" class="text-center">
          <h3>당신이 선호하는 노래는?</h3>
        </v-col>
      </v-row>
      <!-- search bar-->
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
          />
        </v-col>
        <v-col cols="1">
          <v-icon color="#FF5D5D" dense>mdi-magnify</v-icon>
        </v-col>
      </v-row>
      <!-- 응답 선택 -->
      <singer-choice-list
        v-if="currentPage === 1"
        :singerList="singerList"
        @emitSelectSinger="addSingerInList"
        @emitUnselectSinger="removeSingerInList"
      ></singer-choice-list>
      <Song-choice-list
        v-if="currentPage === 2"
        :songList="songList"
        @emitSelectSong="addSongInList"
        @emitUnselectSong="removeSongInList"
      ></Song-choice-list>
    </v-container>
    <!-- continue -->
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
      <!-- finish -->
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
import InterestProgress from "@/components/Interest/InterestProgress.vue";
import SingerChoiceList from "@/components/Interest/SingerChoiceList.vue";
import SongChoiceList from "@/components/Interest/SongChoiceList.vue";

export default {
  name: "InterestView",
  components: {
    InterestProgress,
    SingerChoiceList,
    SongChoiceList,
  },
  data() {
    return {
      currentPage: 1,
      totalPage: 2,
      // 검색어
      keyword: "",
      // 취향 후보 리스트
      singerList: [],
      songList: [],
      // 사용자가 선택한 리스트
      singerChoiceList: [],
      songChoiceList: [],
    };
  },
  mounted() {
    this.getDummySingerList();
    this.getDummySongList();
  },
  methods: {
    // 좋아하는 가수 선택사항 요청
    getSingerList() {
      console.log("가수 선택사항 가져옴");
    },
    // 좋아하는 노래 선택사항 요청
    getSongList() {
      console.log("노래 선택사항 가져옴");
    },
    // 가짜 가수 리스트
    getDummySingerList() {
      this.singerList = [
        {
          singerId: 1,
          name: "나연",
          content: "?",
          picture:
            "https://img.insight.co.kr/static/2022/04/01/700/img_20220401144441_w5o9o513.webp",
        },
        {
          singerId: 2,
          name: "아이브",
          content: "?",
          picture: "",
        },
        {
          singerId: 3,
          name: "아이브",
          content: "?",
          picture: "",
        },
      ];
    },
    // 가짜 노래 리스트
    getDummySongList() {
      this.songList = [
        {
          songId: 1,
          name: "POP!",
          content: "?",
        },
        {
          songId: 2,
          name: "LOVE DIVE",
          content: "?",
        },
        {
          songId: 3,
          name: "LOVE DIVE",
          content: "?",
        },
      ];
    },
    addSingerInList(singerId) {
      this.singerChoiceList.push(singerId);
    },
    removeSingerInList(singerId) {
      const idx = this.singerChoiceList.indexOf(singerId);
      this.singerChoiceList.splice(idx, 1);
    },
    addSongInList(songId) {
      this.songChoiceList.push(songId);
    },
    removeSongInList(songId) {
      const idx = this.songChoiceList.indexOf(songId);
      this.songChoiceList.splice(idx, 1);
    },
    nextPage() {
      this.currentPage = 2;
    },
    previousPage() {
      this.currentPage = 1;
      this.singerChoiceList = [];
      this.songChoiceList = [];
    },
    // 사용자 선택 전송
    // 각각 사용자가 선택한 singerId와 songId들을 담고 있고, 정렬되어 있지 않은 상태
    sendInterest() {
      console.log(this.singerChoiceList);
      console.log(this.songChoiceList);
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

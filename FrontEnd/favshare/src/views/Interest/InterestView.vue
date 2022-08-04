<template>
  <div>
    <h3>FavS2hare</h3>
    <hr />
    <div>
      <v-icon v-if="currentPage == 1">mdi-circle-small</v-icon>
      <v-icon v-if="currentPage > 1" @click="previousPage"
        >mdi-arrow-left</v-icon
      >
      <!-- progress bar -->
      <interest-progress
        :currentPage="currentPage"
        :totalPage="totalPage"
      ></interest-progress>
    </div>
    <!-- question -->
    <h2 v-if="currentPage === 1">당신이 선호하는 가수는?</h2>
    <h2 v-if="currentPage === 2">당신이 선호하는 노래는?</h2>
    <!-- search bar-->
    <div class="search-bar">
      <input type="text" v-model="keyword" />
      <v-icon color="#FF5D5D" dense>mdi-magnify</v-icon>
    </div>
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
.search-bar {
  width: 90%;
  height: 25px;
  background-color: #d9d9d9;
  border-radius: 25px;
}
</style>

<template>
  <!-- eslint-disable -->
  <div>
    <div class="top">
      <search-bar @input-change="searchOnEnter"></search-bar>
    </div>
    <!-- v-if로 검색전, 검색후 화면 나누기 -->
    <div class="search-view" v-if="searchSuccess">
      <!-- <div class="search-view"> -->
      <search-result
        :searched-keyword="keyword"
        :pops-list="popsList"
        :youtube-list="youtubeList"
        :user-list="userList"
      ></search-result>
    </div>
    <div class="bottom">
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import SearchBar from "@/components/common/SearchBar.vue";
import SearchResult from "@/components/Home/search/SearchResult.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "SearchView",
  components: { SearchBar, SearchResult, BottomNavigationBar },
  data() {
    return {
      searchSuccess: false,
      keyword: "",
      paramsData: null,
      popsList: Array,
      youtubeList: Array,
      userList: Array,
    };
  },
  computed: {
    ...mapGetters(["userId", "searchedPopsList", "searchedYoutubeList", "searchedFollowingList"]),
  },
  mounted() {
    this.paramsData = JSON.parse(this.$route.query.keyword);
  },
  methods: {
    ...mapActions(["getSearchedPopsList", "getSearchedYoutubeList", "getSearchedFollowingList"]),
    // getSearchedAll() {
    //   console.log("search succeess");
    //   this.searchSuccess = true;
    // },
    searchOnEnter: function (keyword) {
      this.keyword = keyword;
      this.$router.push({
        path: "search",
        query: { keyword: JSON.stringify(keyword) },
      });
      this.getSearchedPopsList({ keyword: keyword, userId: this.userId });
      this.popsList = this.searchedPopsList;
      this.getSearchedYoutubeList({ keyword: keyword });
      this.youtubeList = this.searchedYoutubeList;
      this.getSearchedFollowingList({ keyword: keyword, userId: this.userId });
      this.userList = this.searchedFollowingList;
      this.searchSuccess = true;
    },
  },
};
</script>

<style scoped>
.top {
  position: fixed;
  width: 100%;
}
.search-view {
  padding-top: 60px;
}
.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>

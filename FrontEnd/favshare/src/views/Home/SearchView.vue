<template>
  <!-- eslint-disable -->
  <div class="top">
    <search-bar @input-change="searchOnEnter"></search-bar>
    <!-- v-if로 검색전, 검색후 화면 나누기 -->
    <div v-if="searchSuccess">
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
      popsList: Array,
      youtubeList: Array,
      userList: Array,
    };
  },
  computed: {
    ...mapGetters(["searchedPopsList", "searchedYoutubeList", "searchedFollowingList"]),
  },
  methods: {
    ...mapActions(["getSearchedPopsList", "getSearchedYoutubeList", "getSearchedFollowingList"]),
    // getSearchedAll() {
    //   console.log("search succeess");
    //   this.searchSuccess = true;
    // },
    searchOnEnter: function (keyword) {
      this.keyword = keyword;
      this.getSearchedPopsList({ keyword: keyword });
      this.popsList = this.searchedPopsList;
      this.getSearchedYoutubeList({ keyword: keyword });
      this.youtubeList = this.searchedYoutubeList;
      this.getSearchedFollowingList({ keyword: keyword });
      this.userList = this.searchedFollowingList;
      this.searchSuccess = true;
    },
  },
};
</script>

<style></style>

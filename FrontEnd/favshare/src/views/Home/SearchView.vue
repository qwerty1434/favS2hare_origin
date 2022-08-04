<template>
  <div>
    <div class="container">
      <search-bar @input-change="searchOnEnter"></search-bar>
    </div>
    <!-- v-if로 검색전, 검색후 화면 나누기 -->
    <div v-if="searchSuccess">
      <search-result></search-result>
    </div>
    <div class="bottom">
      <bottom-navigation-bar></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import SearchBar from "@/components/common/SearchBar.vue";
import SearchResult from "@/components/Home/search/SearchResult.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "SearchView",
  components: { SearchBar, SearchResult, BottomNavigationBar },
  data() {
    return {
      searchSuccess: false,
    };
  },
  methods: {
    // getSearchedAll() {
    //   console.log("search succeess");
    //   this.searchSuccess = true;
    // },
    searchOnEnter: function (keyword) {
      console.log(keyword);
      this.searchSuccess = true;
    },
  },
};
</script>

<style></style>

<template>
  <v-container>
    <search-bar></search-bar>
    <!-- v-if로 검색전, 검색후 화면 나누기 -->
    <!-- <div @searched="getSearchedAll" v-if="searchSuccess"> -->
    <search-result></search-result>
    <!-- </div> -->
  </v-container>
</template>

<script>
import SearchBar from "@/components/common/SearchBar.vue";
import SearchResult from "@/components/Home/search/SearchResult.vue";

export default {
  name: "SearchView",
  components: { SearchBar, SearchResult },
  data() {
    return {
      searchSuccess: false,
    };
  },
  methods: {
    getSearchedAll() {
      console.log("search succeess");
      this.searchSuccess = true;
    },
  },
};
</script>

<style></style>

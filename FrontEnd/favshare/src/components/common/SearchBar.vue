<template>
  <v-container>
    <v-row class="ma-n5">
      <v-col cols="1">
        <v-icon>mdi-arrow-left</v-icon>
      </v-col>
      <v-col cols="8">
        <v-text-field
          id="search-bar"
          placeholder="검색어를 입력하세요."
          v-model="searchKeyword"
          dense
          rounded
          @keydown.enter="onInputKeyword"
        ></v-text-field>
      </v-col>
      <v-spacer></v-spacer>
      <v-col cols="2">
        <v-btn class="ml-3" text icon @click="searchAll">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-divider></v-divider>
  </v-container>
</template>
<script>
export default {
  name: "SearchBar",
  data() {
    return {
      searchKeyword: "",
    };
  },
  methods: {
    searchAll() {
      if (this.searchKeyword === "") {
        alert("검색 키워드를 입력하세요.");
      } else {
        this.$emit("input-change", this.searchKeyword);
        // console.log(searchKeyword);
        // document.getElementById("search-bar").value = "";
      }
    },
    onInputKeyword: function (event) {
      this.$emit("input-change", event.target.value);
      // this.router.push({
      //   name: "SearchView",
      //   params: { searchKeyword: this.searchKeyword },
      // });
    },
  },
};
</script>
<style>
.min-height {
  min-height: 30px !important;
}
</style>

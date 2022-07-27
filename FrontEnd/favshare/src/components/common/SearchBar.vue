<template>
  <div>
    <v-row>
      <v-col cols="auto">
        <v-text-field
          id="search-bar"
          placeholder="검색어를 입력하세요."
          v-model="searchKeyword"
          filled
          dense
          rounded
        ></v-text-field>
      </v-col>
      <v-col>
        <v-btn rounded @click="searchAll(searchKeyword)">검색</v-btn>
      </v-col>
    </v-row>
    <!-- <v-divider></v-divider> -->
  </div>
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
    searchAll(searchKeyword) {
      if (searchKeyword === "") {
        alert("검색 키워드를 입력하세요.");
      } else {
        this.$emit("searched");
        console.log(searchKeyword);
        // document.getElementById("search-bar").value = "";
      }
    },
  },
};
</script>
<style></style>

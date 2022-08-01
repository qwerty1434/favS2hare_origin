<template>
  <div>
    <v-row dense>
      <v-col cols="2">
        <v-icon large>mdi-arrow-left</v-icon>
      </v-col>
      <v-col cols="8">
        <v-text-field
          id="search-bar"
          placeholder="검색어를 입력하세요."
          v-model="searchKeyword"
          filled
          dense
          rounded
          @keydown.enter="onInputKeyword"
        ></v-text-field>
      </v-col>
      <v-col cols="2">
        <v-btn class="ma-2" text icon @click="searchAll(searchKeyword)">
          <v-icon large>mdi-magnify</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-divider></v-divider>
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
        this.$emit("input-change", searchKeyword);
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
<style></style>

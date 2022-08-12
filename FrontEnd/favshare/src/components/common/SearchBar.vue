<template>
  <div>
    <v-row dense>
      <v-col cols="1" class="ma-1 mt-4">
        <v-icon>mdi-arrow-left</v-icon>
      </v-col>
      <v-col class="mt-3">
        <v-text-field
          class="input-none"
          id="search-bar"
          placeholder="검색어를 입력하세요."
          v-model="searchKeyword"
          dense
          rounded
          outlined
          @keydown.enter="onInputKeyword"
        ></v-text-field>
      </v-col>
      <v-col cols="1" class="mt-3 mr-4">
        <v-btn text icon @click="searchAll">
          <v-icon color="red">mdi-magnify</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-divider class="mt-n4"></v-divider>
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
      //   name: "search",
      //   params: { searchKeyword: this.searchKeyword },
      // });
    },
  },
};
</script>
<style>
.v-input__slot {
  min-height: 30px !important;
}
.input-none {
  background-color: none;
}
</style>

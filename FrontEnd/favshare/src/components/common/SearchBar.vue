<template>
  <div>
    <v-app-bar class="comm-top" dense elevation="1" absolute grow color="white">
      <v-row>
        <v-col class="mt-6">
          <v-text-field
            align="center"
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
        <v-col cols="1">
          <v-btn class="mt-5 ml-n6" text icon @click="searchAll">
            <v-icon color="red">mdi-magnify</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-app-bar>
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
<style scoped>
.input-none {
  background-color: none;
}
.comm-top {
  position: fixed;
  width: 100%;
}
</style>

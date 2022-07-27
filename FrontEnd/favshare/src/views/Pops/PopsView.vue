<template>
  <v-container>
    <div>
      <search-bar @input-change="searchOnEnter"></search-bar>
    </div>
    <div>
      <pops-type-list @click-type="searchOnEnter"></pops-type-list>
    </div>
    <div v-if="searchSuccess">
      <pops-list></pops-list>
    </div>
  </v-container>
</template>

<script>
import SearchBar from "@/components/common/SearchBar.vue";
import PopsTypeList from "@/components/Pops/PopsTypeList.vue";
import PopsList from "@/components/Pops/PopsList.vue";

export default {
  name: "PopsView",
  components: { SearchBar, PopsTypeList, PopsList },
  data() {
    return {
      searchSuccess: false,
    };
  },
  methods: {
    searchOnEnter: function (keyword) {
      console.log(keyword);
      this.searchSuccess = true;
    },
  },
};
</script>

<style></style>

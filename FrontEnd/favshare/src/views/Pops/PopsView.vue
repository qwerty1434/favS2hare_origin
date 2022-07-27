<template>
  <v-container>
    <div>
      <search-bar></search-bar>
    </div>
    <div>
      <pops-type-list></pops-type-list>
    </div>
    <div>
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
};
</script>

<style></style>

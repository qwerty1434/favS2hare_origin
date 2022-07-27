<template>
  <v-container>
    <div>
      <search-more-pops-list-item></search-more-pops-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchMorePopsListItem from "./SearchMorePopsListItem.vue";

export default {
  name: "SearchMorePopsList",
  components: { SearchMorePopsListItem },
  data() {
    return {
      searchedPopsList: [],
    };
  },
};
</script>
<style></style>

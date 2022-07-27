<template>
  <v-container>
    <div class="wrap-vertical">
      <search-pops-list-item></search-pops-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchPopsListItem from "./SearchPopsListItem.vue";

export default {
  name: "SearchPopsList",
  components: { SearchPopsListItem },
  data() {
    return {
      searchedPopsList: [],
    };
  },
};
</script>
<style></style>

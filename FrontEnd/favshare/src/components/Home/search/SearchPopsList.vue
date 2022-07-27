<template>
  <v-container>
    <div>
      <search-pops-list-item></search-pops-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchPopsListItem from "./SearchPopsListItem.vue";

export default {
  name: "SearchPopsList",
  components: { SearchPopsListItem },
};
</script>
<style></style>

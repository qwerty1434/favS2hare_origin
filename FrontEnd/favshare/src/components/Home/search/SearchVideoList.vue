<template>
  <v-container>
    <div>
      <search-video-list-item></search-video-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchVideoListItem from "./SearchVideoListItem.vue";

export default {
  name: "SearchVideoList",
  components: { SearchVideoListItem },
};
</script>
<style></style>

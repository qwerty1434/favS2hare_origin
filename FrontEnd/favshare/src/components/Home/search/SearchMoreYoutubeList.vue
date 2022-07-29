<template>
  <v-container>
    <div>
      <search-more-video-list-item></search-more-video-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchMoreVideoListItem from "./SearchMoreVideoListItem.vue";

export default {
  name: "SearchMoreYoutubeList",
  components: { SearchMoreVideoListItem },
  data() {
    return {
      searchedVideoList: [],
    };
  },
};
</script>
<style></style>

<template>
  <!-- eslint-disable -->
  <div class="rpops">
    <div v-for="youtubeListItem in youtubeList.slice(0, 5)">
      <search-video-list-item :youtube-list-item="youtubeListItem"></search-video-list-item>
    </div>
  </div>
</template>
<script>
import SearchVideoListItem from "./SearchVideoListItem.vue";

export default {
  name: "SearchVideoList",
  components: { SearchVideoListItem },
  data() {
    return {};
  },
  props: {
    youtubeList: Array,
  },
};
</script>
<style></style>

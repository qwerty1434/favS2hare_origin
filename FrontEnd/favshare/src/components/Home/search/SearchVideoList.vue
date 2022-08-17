<template>
  <!-- eslint-disable -->
  <div class="video-search mt-7">
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
<style>
.video-search {
  /* 가로 스크롤 */
  height: 200px;
  overflow: scroll;
  white-space: nowrap;
  display: flex;
}
</style>

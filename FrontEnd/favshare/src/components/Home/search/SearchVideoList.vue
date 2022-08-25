<template>
  <div class="video-search mt-7">
    <div
      v-for="(youtubeListItem, index) in youtubeList.slice(0, 5)"
      :key="index"
    >
      <search-video-list-item
        :youtube-list-item="youtubeListItem"
      ></search-video-list-item>
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
  height: 200px;
  overflow: scroll;
  white-space: nowrap;
  display: flex;
}
</style>

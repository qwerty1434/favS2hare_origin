<template>
  <v-container class="mt-1">
    <div v-for="searchedYoutubeListItem in searchedYoutubeList">
      <search-more-video-list-item
        :searched-youtube-list-item="searchedYoutubeListItem"
      ></search-more-video-list-item>
    </div>
  </v-container>
</template>
<script>
import SearchMoreVideoListItem from "./SearchMoreVideoListItem.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "SearchMoreYoutubeList",
  components: { SearchMoreVideoListItem },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["searchedYoutubeList"]),
  },
  methods: {
    ...mapActions([]),
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style></style>

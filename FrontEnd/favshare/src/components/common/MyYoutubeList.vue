<template>
  <v-container>
    <my-youtube-list-item
      v-for="youtubeItem in youtubeList"
      :youtubeId="youtubeItem.youtubeId"
      :youtubeUrl="youtubeItem.youtubeUrl"
      :key="youtubeItem.youtubeId"
    ></my-youtube-list-item>
  </v-container>
</template>
<script>
import MyYoutubeListItem from "./MyYoutubeListItem.vue";

export default {
  name: "MyYoutubeList",
  components: { MyYoutubeListItem },
  props: ["youtubeList"],
};
</script>
<style></style>

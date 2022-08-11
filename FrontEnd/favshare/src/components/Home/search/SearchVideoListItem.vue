<template>
  <v-card class="video-size mr-4">
    <v-img class="video-size" src="@/assets/sample/popssample.jpg">
      {{ youtubeListItem }}
    </v-img>
  </v-card>
</template>
<script>
export default {
  name: "SearchVideoListItem",
  data() {
    return {};
  },
  props: {
    youtubeListItem: String,
  },
};
</script>
<style>
.video-size {
  height: 150px;
  width: 250px;
}
</style>

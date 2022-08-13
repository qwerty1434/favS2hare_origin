<template>
  <v-card class="video-size mr-4">
    <v-img height="130" src="@/assets/sample/popssample.jpg"> </v-img>
    {{ youtubeListItem }}
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
  height: 170px;
  width: 230px;
}
</style>

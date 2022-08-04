<template>
  <v-card class="video-size">
    <v-img class="video-size" src="@/assets/sample/popssample.jpg" />
  </v-card>
</template>
<script>
export default {
  name: "SearchVideoListItem",
  data() {
    return {
      searchedVideo: [],
    };
  },
};
</script>
<style>
.video-size {
  height: 150px;
  width: 250px;
}
</style>

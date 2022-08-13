<template>
  <v-row>
    <v-col>
      <v-sheet color="grey lighten-3" class="my-video" elevation="1" rounded>
        <h5>영상 띄워주는 부분</h5>
      </v-sheet>
    </v-col>
    <v-col class="ml-n5 mt-4">
      <h4>youtube description</h4>
      <h5>userId</h5>
      <v-spacer></v-spacer>
      <h6>
        <v-icon small> mdi-eye </v-icon>
        10
        <v-icon small>mdi-heart</v-icon>
        10
      </h6>
    </v-col>
  </v-row>
</template>
<script>
export default {
  name: "SearchMoreVideoListItem",
  data() {
    return {};
  },
  props: {
    searchedYoutubeListItem: Object,
  },
  created() {
    console.log("video list item");
  },
};
</script>
<style></style>

<template>
  <v-container>
    <v-row class="pb-2">
      <v-col cols="2">
        <v-icon>mdi-arrow-left</v-icon>
      </v-col>
      <v-col>저장된 영상</v-col>
    </v-row>
    <v-divider></v-divider>
    <my-youtube-list :youtubeList="youtubeList"></my-youtube-list>
  </v-container>
</template>
<script>
import MyYoutubeList from "@/components/common/MyYoutubeList.vue";

export default {
  name: "MyYoutubeView",
  components: { MyYoutubeList },
  data() {
    return {
      youtubeList: [],
    };
  },
  created() {
    this.getSampleVideos();
  },
  methods: {
    getSampleVideos() {
      this.youtubeList = [
        {
          youtubeId: 1,
          youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
        },
        {
          youtubeId: 1,
          youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
        },
      ];
    },
  },
};
</script>
<style></style>

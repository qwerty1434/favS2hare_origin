<template>
  <!-- eslint-disable -->
  <v-container>
    <!-- <my-youtube-list-item
      v-for="youtubeItem in myVideoList"
      :youtubeId="youtubeItem.youtubeId"
      :youtubeUrl="youtubeItem.youtubeUrl"
      :key="youtubeItem.youtubeId"
    ></my-youtube-list-item> -->
    <div v-for="myYoutubeVideo in myYoutubeVideos" :key="myYoutubeVideo.id">
      <my-youtube-list-item :my-youtube-video="myYoutubeVideo"></my-youtube-list-item>
    </div>
  </v-container>
</template>
<script>
import MyYoutubeListItem from "./MyYoutubeListItem.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "MyYoutubeList",
  components: { MyYoutubeListItem },
  data() {
    return {
      myYoutubeVideos: Array,
    };
  },
  created() {
    console.log(this.userIdInMyVideo);
    this.getMyVideo({ userIdInMyVideo: this.userIdInMyVideo });
    this.myYoutubeVideos = this.myVideoList;
    console.log(this.myYoutubeVideos);
  },
  computed: {
    ...mapGetters(["userIdInMyVideo", "myVideoList"]),
  },
  methods: {
    ...mapActions(["getMyVideo"]),
  },
};
</script>
<style></style>

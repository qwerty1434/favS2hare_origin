<template>
  <div class="videos">
    <div v-for="youtubeVideo in youtubeVideos" :key="youtubeVideo.id" class="videos__container">
      <youtube-list-item :youtube-video="youtubeVideo"></youtube-list-item>
    </div>
  </div>
</template>

<script>
import YoutubeListItem from "./YoutubeListItem.vue";
// import axios from "axios";

export default {
  components: { YoutubeListItem },
  name: "YoutubeList",
  data() {
    return {
      youtubeVideos: Array,
    };
  },
  methods: {
    // getYoutubeList() {
    //   axios({
    //     method: "get",
    //     url: "http://localhost:8080/youtube/",
    //     headers: "유저토큰",
    //   })
    //     .then((res) => {
    //       this.youtubeVideos = res.data;
    //     })[]
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
    setDummyYoutubes() {
      this.youtubeVideos = [
        { id: 1, videoId: "f6YDKF0LVWw" },
        { id: 2, videoId: "lw-1QX47ocA" },
        { id: 3, videoId: "EEWxd4bj6f4" },
        { id: 4, videoId: "p25b_ZgvbV4" },
        { id: 5, videoId: "foo8805uLhc" },
      ];
    },
  },
  created() {
    this.setDummyYoutubes();
  },
};
</script>

<style>
.videos {
  background-color: #f9f9f9;
  width: 100%;
  height: 100%;
  padding: 15px 15px;
  border-top: 1px solid #ddd;
  overflow-y: scroll;
}

.videos__container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  flex-wrap: wrap;
}
</style>

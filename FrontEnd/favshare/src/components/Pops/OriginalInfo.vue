<template>
  <div>
    <img :src="thumbnail" alt="no image" />
    <div>{{ title }}</div>
    <div>{{ description }}</div>
    <div>Pops {{ countPops }}개</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "OriginalInfo",
  props: ["youtube", "popsList"],
  data() {
    return {
      title: "",
      description: "",
      thumbnail: "",
    };
  },
  computed: {
    countPops() {
      return this.popsList.length;
    },
  },
  mounted() {
    this.getOriginalInfo();
  },
  methods: {
    // youtube api: 원본 동영상에 대한 정보 요청
    getOriginalInfo() {
      const URL = "https://www.googleapis.com/youtube/v3/videos";
      const API_KEY = "AIzaSyDzn6H3ySugFQgEV9RaH0fV4-HBYXRWZ6A";
      const youtubeId = this.youtube.youtubeUrl.slice(-11);
      const params = {
        key: API_KEY,
        part: "snippet",
        id: youtubeId,
      };
      axios
        .get(URL, { params })
        .then((response) => {
          this.title = response.data.items[0].snippet.title;
          this.thumbnail =
            response.data.items[0].snippet.thumbnails.default.url;

          let description = response.data.items[0].snippet.description;
          if (description.length > 20) {
            description = description.slice(0, 20) + "...";
          }
          this.description = description;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>

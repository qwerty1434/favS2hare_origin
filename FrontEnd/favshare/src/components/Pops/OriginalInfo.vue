<template>
  <v-container>
    <v-row>
      <v-col cols="5">
        <img :src="thumbnail" class="original-image" alt="no image" />
      </v-col>
      <v-col cols="7">
        <div>
          <div>
            <h4>
              {{ title }}
            </h4>
          </div>
          <div class="description">{{ description }}</div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "OriginalInfo",
  props: ["youtubeUrl"],
  data() {
    return {
      title: "",
      description: "",
      thumbnail: "",
    };
  },
  mounted() {
    this.getOriginalInfo();
  },
  methods: {
    // youtube api: 원본 동영상에 대한 정보 요청
    getOriginalInfo() {
      const URL = "https://www.googleapis.com/youtube/v3/videos";
      const API_KEY = "AIzaSyDzn6H3ySugFQgEV9RaH0fV4-HBYXRWZ6A";
      const params = {
        key: API_KEY,
        part: "snippet",
        id: this.youtubeUrl,
      };
      axios
        .get(URL, { params })
        .then((response) => {
          this.title = response.data.items[0].snippet.title;
          this.thumbnail = response.data.items[0].snippet.thumbnails.medium.url;
          let description = response.data.items[0].snippet.description;
          if (description.length > 120) {
            description = description.slice(0, 120) + "...";
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

<style>
.original-image {
  object-fit: cover;
  width: 125px;
  height: 125px;
  border-radius: 30px;
}

.description {
  font-size: 13px;
  margin-top: 5px;
}
</style>

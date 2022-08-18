<template>
  <!-- eslint-disable -->
  <div>
    <v-row>
      <v-col>
        <router-link
          :to="{
            name: 'youtube',
            params: {
              videoInfo: {
                videoId: this.searchedYoutubeListItem,
                channelName: this.channelName,
                channelProfilePic: this.channelProfilePic,
                videoTitle: this.videoTitle,
              },
            },
          }"
        >
          <img class="my-video" :src="this.thumbNail" alt="" />
        </router-link>
      </v-col>
      <v-col class="ml-n5 mt-4">
        <h5>{{ this.videoTitle.substr(0, 50) }} ...</h5>
        <v-spacer></v-spacer>
        <h6 class="mt-2 mb-2 grey--text">{{ this.channelName.substr(0, 19) }}</h6>
      </v-col>
    </v-row>
    <v-divider></v-divider>
  </div>
</template>
<script>
/* eslint-disable */
import axios from "axios";
import googleAPI from "@/api/googleAPI";

export default {
  name: "SearchMoreVideoListItem",
  data() {
    return {
      //for-use data
      thumbNail: String,
      channelProfilePic: String,
      channelName: String,
      videoTitle: String,
    };
  },
  props: {
    searchedYoutubeListItem: Object,
  },
  created() {
    console.log("video list item");
    console.log(this.searchedYoutubeListItem);
    this.getSearchedVideoInfo();
  },
  methods: {
    getSearchedVideoInfo() {
      // 썸네일, 채널 id 받아오기
      const API_KEY = process.env.VUE_APP_API_KEY_1;
      const params = {
        key: API_KEY,
        part: "snippet",
        id: this.searchedYoutubeListItem,
      };
      axios.get(googleAPI.videos(), { params }).then((res) => {
        this.thumbNail = res.data.items[0].snippet.thumbnails.medium.url;
        this.videoTitle = res.data.items[0].snippet.title;
        const tmpChannelId = res.data.items[0].snippet.channelId;
        axios
          .get(googleAPI.channels(), {
            key: API_KEY,
            part: "snippet",
            id: tmpChannelId,
          })
          .then((res) => {
            this.channelProfilePic = res.data.items[0].snippet.thumbnails.default.url;
            this.channelName = res.data.items[0].snippet.title;
          });
      });
    },
  },
};
</script>
<style>
.my-video {
  border-radius: 10%;
}
</style>

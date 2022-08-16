<template>
  <v-card class="video-size mr-4">
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
      <v-img height="130" :src="this.thumbNail"> </v-img>
    </router-link>
    <h5>{{ this.videoTitle.substr(0, 40) }} ...</h5>
  </v-card>
</template>
<script>
/* eslint-disable */
import axios from "axios";

export default {
  name: "SearchVideoListItem",
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
    youtubeListItem: String,
  },
  created() {
    console.log("video list");
    console.log(this.youtubeListItem);
    this.getSearchedVideoList();
  },
  methods: {
    getSearchedVideoList() {
      // 썸네일, 채널 id 받아오기
      axios({
        method: "get",
        url: `https://www.googleapis.com/youtube/v3/videos?part=snippet&id=${this.youtubeListItem}&key=AIzaSyDzn6H3ySugFQgEV9RaH0fV4-HBYXRWZ6A`,
      }).then((res) => {
        this.thumbNail = res.data.items[0].snippet.thumbnails.medium.url;
        this.videoTitle = res.data.items[0].snippet.title;
        const tmpChannelId = res.data.items[0].snippet.channelId;
        axios({
          method: "get",
          url: `https://www.googleapis.com/youtube/v3/channels?part=snippet&id=${tmpChannelId}&key=AIzaSyDzn6H3ySugFQgEV9RaH0fV4-HBYXRWZ6A`,
        }).then((res) => {
          this.channelProfilePic = res.data.items[0].snippet.thumbnails.default.url;
          this.channelName = res.data.items[0].snippet.title;
        });
      });
    },
  },
};
</script>
<style>
.video-size {
  height: 170px;
  width: 230px;
}
</style>

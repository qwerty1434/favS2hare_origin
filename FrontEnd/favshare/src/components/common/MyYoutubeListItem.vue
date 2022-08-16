<template>
  <div>
    <v-row>
      <v-col>
        <router-link
          :to="{
            name: 'youtube',
            params: {
              videoInfo: {
                videoId: this.myYoutubeVideo,
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
        <h6 @click="btnDeleteMyYoutube">
          <v-icon small> mdi-close </v-icon>
          제거
        </h6>
      </v-col>
    </v-row>
    <v-divider></v-divider>
  </div>
</template>
<script>
/* eslint-disable */
import axios from "axios";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "MyYouTubeListItem",
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
    myYoutubeVideo: Object,
  },
  computed: {
    ...mapGetters(["userIdInMyVideo", "myVideoList"]),
  },
  created() {
    this.getStoredVideoInfo();
  },
  methods: {
    ...mapActions(["deleteMyVideo"]),
    getStoredVideoInfo() {
      // 썸네일, 채널 id 받아오기
      axios({
        method: "get",
        url: `https://www.googleapis.com/youtube/v3/videos?part=snippet&id=${this.myYoutubeVideo}&key=AIzaSyDzn6H3ySugFQgEV9RaH0fV4-HBYXRWZ6A`,
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
    btnDeleteMyYoutube() {
      if (confirm("영상을 보관함에서 삭제하시겠습니까?")) {
        this.deleteMyVideo({
          userId: this.userIdInMyVideo,
          youtubeUrl: this.myYoutubeVideo.url,
        });
      }
      this.$emit("delete-video");
    },
  },
};
</script>
<style>
.my-video {
  width: 150px;
  height: 100px;
  margin-top: 10px;
}
</style>

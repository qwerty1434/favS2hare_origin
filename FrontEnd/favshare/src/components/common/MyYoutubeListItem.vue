<template>
  <div>
    <v-row>
      <v-col>
        <router-link
          :to="{
            name: 'youtube',
            params: {
              videoInfo: {
                videoId: this.myYoutubeVideo.url,
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
import axios from "axios";
import { mapGetters, mapActions } from "vuex";
import googleAPI from "@/api/googleAPI";

export default {
  name: "MyYouTubeListItem",
  data() {
    return {
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
    ...mapGetters(["userId", "myVideoList"]),
  },
  created() {
    this.getStoredVideoInfo();
    this.reload();
  },
  methods: {
    ...mapActions(["deleteMyVideo"]),
    getStoredVideoInfo() {
      const API_KEY = process.env.VUE_APP_API_KEY_1;
      const params = {
        key: API_KEY,
        part: "snippet",
        id: this.myYoutubeVideo.url,
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
    btnDeleteMyYoutube() {
      if (confirm("영상을 보관함에서 삭제하시겠습니까?")) {
        this.deleteMyVideo({
          userId: this.userId,
          youtubeUrl: this.myYoutubeVideo.url,
        });
      }
      this.$emit("delete-video");
      this.reload();
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

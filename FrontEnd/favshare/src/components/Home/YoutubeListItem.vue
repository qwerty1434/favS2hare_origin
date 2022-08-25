<template>
  <div>
    <v-sheet class="video" elevation="2" rounded="xl">
      <div class="video__thumbnail" style="text-align: center">
        <router-link
          :to="{
            name: 'youtube',
            params: {
              videoInfo: {
                videoId: this.homeYoutube.youtubeId,
                channelName: this.channelName,
                channelProfilePic: this.channelProfilePic,
                videoTitle: this.videoTitle,
              },
            },
          }"
          style="text-align: center"
        >
          <img :src="this.thumbNail" alt="" />
        </router-link>
      </div>
      <div class="video__details">
        <div class="author">
          <img :src="this.channelProfilePic" alt="" />
        </div>
        <div class="title">
          <h3>
            {{ this.videoTitle }}
          </h3>
        </div>
      </div>
    </v-sheet>
  </div>
</template>

<script>
import axios from "axios";
import googleAPI from "@/api/googleAPI";
export default {
  name: "YoutubeListItem",
  props: {
    homeYoutube: Object,
  },
  data() {
    return {
      thumbNail: String,
      channelProfilePic: String,
      channelName: String,
      videoTitle: String,
      videoId: String,
    };
  },
  methods: {
    getVideoInfo() {
      const API_KEY = process.env.VUE_APP_API_KEY_1;
      const params = {
        key: API_KEY,
        part: "snippet",
        id: this.homeYoutube.youtubeId,
      };
      axios
        .get(googleAPI.videos(), { params })
        .then((res) => {
          this.thumbNail = res.data.items[0].snippet.thumbnails.medium.url;
          this.videoTitle = res.data.items[0].snippet.title;
          const tmpChannelId = res.data.items[0].snippet.channelId;
          axios
            .get(
              `https://www.googleapis.com/youtube/v3/channels?part=snippet&id=${tmpChannelId}&key=${API_KEY}`
            )
            .then((res) => {
              this.channelProfilePic =
                res.data.items[0].snippet.thumbnails.default.url;
              this.channelName = res.data.items[0].snippet.title;
            })
            .catch((res) => {
              console.log(res);
            });
        })
        .catch((res) => {
          console.log(res);
        });
    },
  },
  created() {
    this.getVideoInfo();
  },
};
</script>

<style scoped>
.video {
  width: 320px;
  margin-bottom: 20px;
}

.video__thumbnail {
  width: 100%;
  height: 180px;
  border-radius: 30%;
  object-fit: cover;
  margin: auto;
}

.video__thumbnail img {
  object-fit: cover;
  height: 100%;
  width: 100%;
  border-radius: 10%;
}

.video__details {
  display: flex;
  margin-top: 10px;
  padding-left: 15px;
}

.author img {
  object-fit: cover;
  border-radius: 50%;
  height: 30px;
  width: 30px;
  margin-right: 10px;
}

.title {
  display: flex;
  flex-direction: column;
}

.title h3 {
  color: rgb(3, 3, 3);
  line-height: 18px;
  font-size: 14px;
  margin-bottom: 6px;
}
</style>

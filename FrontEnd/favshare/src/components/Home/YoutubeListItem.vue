<template>
  <div>
    <v-sheet class="video" elevation="2" rounded="xl">
      <div class="video__thumbnail" style="text-align: center">
        <router-link
          :to="{
            name: 'youtube',
            params: {
              youtubeId: youtubeVideo.id, // 서버 id
              videoInfo: {
                videoId: this.videoId, // 유튜브 id
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

export default {
  name: "YoutubeListItem",
  props: {
    youtubeVideo: Object,
    // homeYoutube: Object,  // { youtubeId: ?, youtubeUrl: ? } 형태
  },
  data() {
    return {
      thumbNail: String,
      channelId: String,
      channelProfilePic: String,
      channelName: String,
      videoTitle: String,
      videoId: String,
    };
  },
  methods: {
    getEx() {
      const videoId = this.youtubeVideo.youtubeUrl.slice(-11);
      this.videoId = videoId;
      axios({
        method: "get",
        url: `https://www.googleapis.com/youtube/v3/videos?part=snippet&id=${videoId}&key=AIzaSyB45yVkJSllvfe7yH7DNPVcy1jvIRQjYkk`,
      }).then((res) => {
        console.log("여기", res.data);
        // res.data.items[0].snippet.thumbnails.medium => width:320, height: 180 짜리 썸네일
        this.thumbNail = res.data.items[0].snippet.thumbnails.medium.url;
        this.videoTitle = res.data.items[0].snippet.title;
        const tmpChannelId = res.data.items[0].snippet.channelId;
        axios({
          method: "get",
          url: `https://www.googleapis.com/youtube/v3/channels?part=snippet&id=${tmpChannelId}&key=AIzaSyB45yVkJSllvfe7yH7DNPVcy1jvIRQjYkk`,
        }).then((res) => {
          this.channelProfilePic =
            res.data.items[0].snippet.thumbnails.default.url;
          this.channelName = res.data.items[0].snippet.title;
        });
      });
    },
  },
  created() {
    this.getEx();
  },
};
</script>

<style>
/* .container {
  height: 100vh;
}
img {
  width: 90%;
  margin-left: auto;
  margin-right: auto;
}
.profile {
  width: 10px;
  height: auto;
}
.box {
  text-align: center;
  width: 30px;
  height: 30px;
  border-radius: 70%;
  overflow: hidden;
} */

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
}

.author img {
  object-fit: cover;
  border-radius: 50%;
  height: 40px;
  width: 40px;
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

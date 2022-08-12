<template>
  <v-row>
    <v-col>
      <v-sheet color="grey lighten-3" class="my-video" elevation="1" rounded>
        <h3>{{ myYoutubeVideo.url }}</h3>
        <h5>영상 띄워주는 부분</h5>
        <!-- <div class="video__thumbnail" style="text-align: center">
        <router-link
          :to="{
            name: 'youtube',
            params: {
              videoInfo: {
                videoId: this.videoId, // 유튜브 id -> 실재로 쓸 때는 this.videoId => homeYoutube로 바꾸기
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
      </div> -->
      </v-sheet>
    </v-col>
    <v-col class="ml-n5 mt-4">
      <h4>youtube description</h4>
      <h5>userId</h5>
      <v-spacer></v-spacer>
      <h6>
        <v-icon small> mdi-close </v-icon>
        제거
      </h6>
    </v-col>
  </v-row>
</template>
<script>
export default {
  name: "MyYouTubeListItem",
  props: {
    myYoutubeVideo: Object,
  },
  created: {},
};
</script>
<style>
.my-video {
  width: 150px;
  height: 100px;
  margin-top: 10px;
}
</style>

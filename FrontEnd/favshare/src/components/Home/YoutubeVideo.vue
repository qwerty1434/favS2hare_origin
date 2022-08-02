<template>
  <div>
    <youtube
      :video-id="this.videoInfo.videoId"
      :player-vars="playerVars"
      ref="youtube"
      :width="330"
      :height="186"
      @playing="playing"
    ></youtube>
    <div class="videoinfo__icons">
      <div class="videoinfo">
        {{ this.videoInfo.channelName }}
      </div>
      <div class="editicons">
        <router-link
          :to="{
            name: 'youtubeedit',
            query: {
              youtubePk: this.youtubePk,
              youtubeId: this.videoInfo.youtubeId,
            },
          }"
        >
          <v-icon class="icons">mdi-movie-open-edit-outline</v-icon>
        </router-link>
        <div v-if="this.isBookmarkClicked">
          <v-icon @click="setBookmark">mdi-bookmark</v-icon>
        </div>
        <div v-else>
          <v-icon @click="setBookmark">mdi-bookmark-outline</v-icon>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 유튜브id, 채널이름, 영상제목, 채널 이미지
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapGetters } from "vuex";

Vue.use(VueYoutube);

export default {
  name: "YoutubeVideo",
  // props: {
  //   videoInfo: Object,
  //   youtubeId: Number,
  // },
  data() {
    return {
      playerVars: {
        autoplay: 1,
        controls: 1,
      },
      isBookmarkClicked: 0,
    };
  },
  computed: {
    ...mapGetters(["videoInfo"]),
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {
      console.log("playing");
    },
    setBookmark() {
      if (this.isBookmarkClicked === 1) {
        this.isBookmarkClicked = 0;
      } else {
        this.isBookmarkClicked = 1;
      }
    },
    // postStoredYoutube() {
    //   axios({
    //     method: "post",
    //     url: "http://localhost:8080/youtube/bookmark",
    //     data: { userId: this.userId, youtubeId: this.youtubePk },
    //   }).then((res) => {
    //     if (res.data != 200) {
    //       console.log("something wrong");
    //     }
    //   });
    // },
  },
  created() {
    this.playVideo();
  },
};
</script>

<style>
.videoinfo__icons {
  display: flex;
  justify-content: space-between;
  padding-right: 20px;
}

.editicons {
  display: flex;
  justify-content: space-between;
}

.icons {
  padding-right: 5px;
}
</style>

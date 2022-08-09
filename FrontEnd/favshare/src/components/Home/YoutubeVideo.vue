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
              youtubePk: this.videoInfo.youtubeId,
              youtubeId: this.videoInfo.videoId,
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
        <!-- <div v-if="this.isBookmarkClicked">
          <v-icon @click="[setBookmark, postStoredYoutube]"></v-icon>
        </div>
        <div v-else>
          <v-icon @click="[setBookmark, deleteStoredYoutube]"></v-icon>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
// 유튜브id, 채널이름, 영상제목, 채널 이미지
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapGetters } from "vuex";
import axios from "axios";

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
    ...mapGetters(["videoInfo", "userId"]),
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
        this.deleteStoredYoutube();
      } else {
        this.isBookmarkClicked = 1;
        this.postStoredYoutube();
      }
    },
    postStoredYoutube() {
      axios({
        method: "post",
        url: "http://localhost:8080/youtube/bookmark",
        data: { userId: this.userId, youtubeUrl: this.videoInfo.videoId },
      })
        .then((res) => {
          if (res.data !== 200) {
            console.log("something wrong");
          }
        })
        .catch((res) => {
          console.log(this.userId);
          console.log(this.videoInfo.videoId);
          console.log(res);
        });
    },
    deleteStoredYoutube() {
      axios({
        method: "delete",
        url: "http://localhost:8080/youtube/bookmark",
        data: { userId: this.userId, youtubeUrl: this.videoInfo.videoId },
      }).then((res) => {
        if (res.data !== 200) {
          console.log("something wrong");
        }
      });
    },
    isStoreYoutube() {
      axios({
        method: "post",
        url: "http://localhost:8080/youtube/bookmark/status",
        data: { userId: this.userId, youtubeUrl: this.videoInfo.videoId },
      })
        .then(() => {
          this.isBookmarkClicked = 1;
        })
        .catch((res) => {
          console.log(res);
        });
    },
  },
  created() {
    this.isStoreYoutube();
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

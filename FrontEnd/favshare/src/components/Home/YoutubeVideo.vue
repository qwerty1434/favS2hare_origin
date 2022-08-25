<template>
  <div>
    <youtube
      :video-id="this.videoInfo.videoId"
      :player-vars="playerVars"
      @playing="onPlaying"
      @ready="onPlayerReady"
      ref="youtube"
      :width="330"
      :height="186"
    ></youtube>
    <div class="videoinfo__icons">
      <div class="videoinfo">
        {{ this.videoInfo.channelName }}
      </div>
      <div v-if="isSignin">
        <div class="editicons">
          <router-link
            :to="{
              name: 'youtubeedit',
              query: {
                youtubeId: this.videoInfo.youtubeId,
                youtubeUrl: this.videoInfo.videoId,
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
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";
import { mapGetters } from "vuex";
import axios from "axios";
import api from "@/api/springRestAPI";

Vue.use(VueYoutube);

export default {
  name: "YoutubeVideo",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        controls: 0,
        diablekb: 1,
      },
      isBookmarkClicked: 0,
    };
  },
  computed: {
    ...mapGetters(["videoInfo", "userId", "isSignin"]),
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {},
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
        url: api.youtubeBookmark.youtubeBookmark(),
        data: { userId: this.userId, youtubeUrl: this.videoInfo.videoId },
      })
        .then((res) => {
          if (res.data !== 200) {
            console.log("something wrong");
          }
        })
        .catch((res) => {
          console.log(res);
        });
    },
    deleteStoredYoutube() {
      axios({
        method: "delete",
        url: api.youtubeBookmark.youtubeBookmark(),
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
        url: api.youtube.detail(),
        data: {
          userId: this.userId,
          youtubeUrl: this.videoInfo.videoId,
        },
      })
        .then((res) => {
          this.isBookmarkClicked = res.data.bookmarked;
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

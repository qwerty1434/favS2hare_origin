<template>
  <div>
    <div class="video" style="text-align: center">
      <div class="video__details">
        <div class="author">
          <router-link
            :to="{ name: 'feed', params: { userPk: feedPop.userId } }"
          >
            <img :src="this.feedPop.profileImage" alt="" />
          </router-link>
        </div>
        <div class="title">
          <h3>
            {{ this.feedPop.userName }}
          </h3>
        </div>
      </div>
      <div class="video__thumbnail">
        <youtube
          :video-id="this.videoId"
          :player-vars="this.playerVars"
          ref="youtube"
          @playing="playing"
          :width="360"
          :height="202"
        ></youtube>
      </div>
    </div>
  </div>
</template>

<script>
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "NewsFeedListItem",
  props: {
    feedPop: Object, // {userId: ?, profileImage: ?, userName: ?, popsId: ?, likeCount: ?}
  },
  data() {
    return {
      videoId: this.feedPop.youtubeVideoId,
      playerVars: {
        autoplay: 1,
        contros: 0,
      },
    };
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {
      console.log("we are watching!!!");
    },
  },
  created() {
    this.playVideo();
  },
};
</script>

<style>
.video {
  width: 330px;
  margin-bottom: 10px;
}

.video__thumbnail {
  width: 100%;
  height: 170px;
  border-radius: 30%;
  object-fit: cover;
  margin-top: 10px;
}

.video__thumbnail img {
  object-fit: cover;
  height: 100%;
  width: 100%;
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

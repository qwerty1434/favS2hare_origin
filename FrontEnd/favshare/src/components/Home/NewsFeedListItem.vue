<template>
  <div>
    <div class="video" style="text-align: center">
      <div class="video__thumbnail">
        <youtube
          :video-id="youtubeId"
          :player-vars="playerVars"
          :ref="'pops' + this.feedPop.popsId"
          @ready="onPlayerReady"
          @playing="onPlaying"
          :width="360"
          :height="202"
          style="pointer-events: none"
        ></youtube>
      </div>
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
    // newsFeedPops: Object, // {}
  },
  data() {
    return {
      playerVars: {
        autoplay: 1,
        mute: 1,
        contros: 0,
        disablekb: 1,
      },
      section: {
        start: this.feedPop.startSecond,
        end: this.feedPop.endSecond,
      },
    };
  },
  computed: {
    youtubeId() {
      return this.feedPop.youtubeUrl.slice(-11);
    },
    player() {
      return this.$refs[`pops${this.feedPop.popsId}`].player;
    },
  },
  methods: {
    onPlayerReady() {
      this.player.seekTo(this.section.start);
      this.player.playVideo();
    },
    onPlaying() {
      const duration = this.section.end - this.section.start;
      setTimeout(this.restartVideoSection, duration * 1000);
    },
    restartVideoSection() {
      this.player.seekTo(this.section.start);
    },
    // playVideo() {
    //   this.player.playVideo();
    // },
    // playing() {
    //   console.log("we are watching!!!");
    // },
  },
  // created() {
  //   this.playVideo();
  // },
};
</script>

<style scoped>
.video {
  width: 330px;
  margin-bottom: 10px;
}

.video__thumbnail {
  width: 100%;
  height: 170px;
  border-radius: 30%;
  object-fit: cover;
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

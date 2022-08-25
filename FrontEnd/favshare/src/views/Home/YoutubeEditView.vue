<template>
  <div>
    <v-app-bar color="white" dense flat>
      <v-icon @click="moveToYoutube">mdi-close</v-icon>
      <v-spacer></v-spacer>
      <div class="post-button" @click="postPops">게시</div>
    </v-app-bar>
    <!-- eslint-disable-next-line -->
    <youtube-edit-tool
      ref="youtubeEditTool"
      :youtubeId="youtubeId"
      :youtubeUrl="youtubeUrl"
    ></youtube-edit-tool>
    <upload-form ref="uploadForm" :feedList="feedList"></upload-form>
  </div>
</template>

<script>
import YoutubeEditTool from "@/components/Home/YoutubeEditTool.vue";
import UploadForm from "@/components/Home/UploadForm.vue";

import axios from "axios";
import { mapGetters } from "vuex";
import api from "@/api/springRestAPI";

export default {
  name: "YoutubeEditView",
  components: { YoutubeEditTool, UploadForm },
  data() {
    return {
      youtubeId: null,
      youtubeUrl: null,
      feedList: [],
    };
  },
  computed: {
    ...mapGetters(["userId", "videoInfo"]),
  },
  created() {
    this.youtubeId = this.$route.query.youtubeId;
    this.youtubeUrl = this.$route.query.youtubeUrl;
    this.getFeedList();
  },
  methods: {
    getFeedList() {
      axios({
        method: "post",
        url: api.youtubeEdit.info(),
        data: {
          userId: this.userId,
          youtubeUrl: this.youtubeUrl,
        },
      })
        .then((res) => {
          this.feedList = res.data.feedList;
        })
        .catch((res) => {
          console.log(res);
        });
    },
    postPops() {
      axios({
        method: "post",
        url: api.youtubeEdit.youtubeEdit(),
        data: {
          userId: this.userId,
          youtubeUrl: this.youtubeUrl,
          feedId: Number(this.$refs.uploadForm.selectedFeedId),
          name: this.$refs.uploadForm.title,
          content: this.$refs.uploadForm.description,
          startSecond: this.$refs.youtubeEditTool.range[0],
          endSecond: this.$refs.youtubeEditTool.range[1],
        },
      })
        .then(() => {
          this.moveToYoutube();
        })
        .catch((res) => {
          console.log(res);
        });
    },
    moveToYoutube() {
      this.$router.push({
        name: "youtube",
        params: {
          videoInfo: {
            videoId: this.videoInfo.videoId,
            channelName: this.videoInfo.channelName,
            channelProfilePic: this.videoInfo.channelProfilePic,
            videoTitle: this.videoInfo.videoTitle,
          },
        },
      });
    },
  },
};
</script>

<style scoped>
.post-button {
  color: #ff5d5d;
}
</style>

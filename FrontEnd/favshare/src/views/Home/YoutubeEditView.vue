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

export default {
  name: "YoutubeEditView",
  components: { YoutubeEditTool, UploadForm },
  // youtubeId: DB에서 영상을 분류하는 번호
  // youtubeUrl: youtube에서 영상을 불러오는 11자리 key
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
    this.getDummyFeedList();
    // this.getFeedList();
  },
  methods: {
    // 해당 유저의 피드리스트 가져오기
    getFeedList() {
      axios({
        method: "post",
        url: "http://localhost:8080/youtube/edit/info",
        data: {
          userId: this.userId,
          youtubeUrl: this.youtubeUrl,
        },
      })
        .then((res) => {
          console.log(res);
          this.feedList = res.data.feedList;
        })
        .catch((res) => {
          console.log(res);
        });
    },
    getDummyFeedList() {
      this.feedList = [
        { feedId: 1, name: "대표" },
        { feedId: 2, name: "아이돌1" },
        { feedId: 3, name: "아이돌2" },
        { feedId: 4, name: "아이돌3" },
      ];
    },
    // Pops 게시
    postPops() {
      axios({
        method: "post",
        url: "http://localhost:8080/youtube/edit",
        data: {
          userId: this.userId,
          youtubeUrl: this.youtubeUrl,
          feedId: Number(this.$refs.uploadForm.feedId),
          name: this.$refs.uploadForm.title,
          content: this.$refs.uploadForm.description,
          startSecond: this.$refs.youtubeEditTool.range[0],
          endSecond: this.$refs.youtubeEditTool.range[1],
        },
      })
        .then(() => {
          // 게시 성공 후엔 YoutubeView로 이동
          this.moveToYoutube();
        })
        .catch((res) => {
          console.log(res);
        });
    },
    // YoutubeView로 이동하기
    moveToYoutube() {
      this.$router.push({
        name: "youtube",
        params: {
          videoInfo: {
            //여기부분 youtubeId아니고 videoId
            videoId: this.videoInfo.videoId, // 유튜브 id -> 실제로 쓸 때는 this.videoId => homeYoutube로 바꾸기
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

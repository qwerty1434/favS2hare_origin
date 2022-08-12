<template>
  <div>
    <v-icon>mdi-close</v-icon>
    <div @click="postPops">게시</div>
    <!-- eslint-disable-next-line -->
    <youtube-edit-tool
      ref="youtubeEditTool"
      :youtubePk="youtubePk"
      :youtubeId="youtubeId"
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
  // youtubePk: DB에서 영상을 분류하는 번호
  // youtubeId: youtube에서 영상을 불러오는 ID
  data() {
    return {
      youtubePk: null,
      youtubeId: null,
      feedList: [],
    };
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  created() {
    this.youtubePk = this.$route.query.youtubePk;
    this.youtubeId = this.$route.query.youtubeId;
    // this.getDummyFeedList();
    this.getFeedList();
  },
  methods: {
    // 해당 유저의 피드리스트 가져오기
    getFeedList() {
      axios({
        method: "post",
        url: "http://localhost:8080/youtube/edit/info",
        data: {
          userId: this.userId,
          youtubeUrl: this.youtubeId,
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
    // input 서버에 전송
    postPops() {
      console.log({
        // + userId
        youtubeId: this.youtubeId,
        title: this.$refs.uploadForm.title,
        content: this.$refs.uploadForm.description,
        startSecond: this.$refs.youtubeEditTool.range[0],
        endSecond: this.$refs.youtubeEditTool.range[1],
        feedId: this.$refs.uploadForm.feedId,
      });

      axios({
        method: "post",
        url: "http://localhost:8080/youtube/edit",
        data: {
          userId: this.userId,
          youtubeUrl: this.youtubeId,
          feedId: this.$refs.uploadForm.feedId,
          name: this.$refs.uploadForm.title,
          content: this.$refs.uploadForm.description,
          startSecond: this.$refs.youtubeEditTool.range[0],
          endSecond: this.$refs.youtubeEditTool.range[1],
        },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((res) => {
          console.log(res);
        });
    },
  },
};
</script>

<style></style>

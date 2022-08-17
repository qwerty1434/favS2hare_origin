<template>
  <!-- eslint-disable -->
  <div>
    <!-- <my-youtube-list-item
      v-for="youtubeItem in myVideoList"
      :youtubeId="youtubeItem.youtubeId"
      :youtubeUrl="youtubeItem.youtubeUrl"
      :key="youtubeItem.youtubeId"
    ></my-youtube-list-item> -->
    <div v-for="myYoutubeVideo in myYoutubeVideos" :key="myYoutubeVideo.id">
      <my-youtube-list-item
        @delete-video="refreshCur"
        :my-youtube-video="myYoutubeVideo"
      ></my-youtube-list-item>
    </div>
  </div>
</template>
<script>
import MyYoutubeListItem from "./MyYoutubeListItem.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "MyYoutubeList",
  components: { MyYoutubeListItem },
  data() {
    return {
      myYoutubeVideos: Array,
    };
  },
  created() {
    this.getMyVideo({ userId: this.userId });
    this.myYoutubeVideos = this.myVideoList;
    console.log(this.myYoutubeVideos);
  },
  computed: {
    ...mapGetters(["userId", "myVideoList"]),
  },
  methods: {
    ...mapActions(["getMyVideo"]),
    refreshCur() {
      console.log("삭제 이벤트 발생 " + this.myVideoList.length);
      this.$router.go();
    },
  },
};
</script>
<style></style>

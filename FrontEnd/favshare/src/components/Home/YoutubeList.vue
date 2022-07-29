<template>
  <div class="videos">
    <div
      v-for="youtubeVideo in youtubeVideos"
      :key="youtubeVideo.id"
      class="videos__container"
    >
      <youtube-list-item :youtube-video="youtubeVideo"></youtube-list-item>
    </div>
    <!-- 밑에 꺼가 진짜 위에 꺼는 하드코딩용 -->
    <!-- <div
      v-for="homeYoutube in homeYoutubes"
      :key="homeYoutube.youtubeId"
      class="videos__container"
    >
      <youtube-list-item :home-youtube="homeYoutube"></youtube-list-item>
    </div> -->
  </div>
</template>

<script>
import YoutubeListItem from "./YoutubeListItem.vue";
import { mapActions, mapGetters } from "vuex";
// import axios from "axios";

export default {
  components: { YoutubeListItem },
  name: "YoutubeList",
  data() {
    return {
      youtubeVideos: Array,
    };
  },
  computed: {
    ...mapGetters(["homeYoutubes"]), // 3. homeYoutubes가 변경되면 감지하여 다시 랜더링, 그 값을 가져옴
  },
  methods: {
    ...mapActions(["fetchHomeYoutubes"]),
    setDummyYoutubes() {
      this.youtubeVideos = [
        { id: 1, videoId: "f6YDKF0LVWw" },
        { id: 2, videoId: "lw-1QX47ocA" },
        { id: 3, videoId: "EEWxd4bj6f4" },
        { id: 4, videoId: "p25b_ZgvbV4" },
        { id: 5, videoId: "foo8805uLhc" },
      ];
    },
  },
  created() {
    this.setDummyYoutubes();
    // this.fetchHomeYoutubes();  // 1. 랜더링 되면 fetchHomeYoutube실행 => 2. SET_HOMEYOUTUBES mutation을 통해 homeYoutues변경
  },
};
</script>

<style>
.videos {
  background-color: #f9f9f9;
  width: 100%;
  height: 100%;
  padding: 15px 15px;
  border-top: 1px solid #ddd;
  overflow-y: scroll;
  padding-bottom: 56px;
}

.videos__container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  flex-wrap: wrap;
}
</style>

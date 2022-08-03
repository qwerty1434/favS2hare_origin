<template>
  <div class="videos">
    <div
      v-for="youtubeVideo in youtubeVideos"
      :key="youtubeVideo.youtubeid"
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
    // homeYoutubes -> [{ youtubeId: ?, youtubeUrl: ? }, { youtubeId: ?, youtubeUrl: ? }, ... ]
    ...mapGetters(["homeYoutubes"]), // 3. homeYoutubes가 변경되면 감지하여 다시 랜더링, 그 값을 가져옴
  },
  methods: {
    ...mapActions(["fetchHomeYoutubes"]),
    setDummyYoutubes() {
      this.youtubeVideos = [
        {
          youtubeid: 1,
          youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
        },
        {
          youtubeid: 2,
          youtubeUrl: "https://www.youtube.com/watch?v=lw-1QX47ocA",
        },
        {
          youtubeid: 3,
          youtubeUrl: "https://www.youtube.com/watch?v=EEWxd4bj6f4",
        },
        {
          youtubeid: 4,
          youtubeUrl: "https://www.youtube.com/watch?v=p25b_ZgvbV4",
        },
        {
          youtubeid: 5,
          youtubeUrl: "https://www.youtube.com/watch?v=foo8805uLhc",
        },
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
  background-color: white;
  width: 100%;
  height: 100%;
  padding-left: 10px;
  padding: 15px 15px;
  overflow-y: scroll;
  padding-bottom: 56px;
}

.videos__container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  flex-wrap: wrap;
  border: 10px;
}
</style>

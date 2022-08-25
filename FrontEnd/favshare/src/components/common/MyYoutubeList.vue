<template>
  <div>
    <div v-for="myYoutubeVideo in myVideoList" :key="myVideoList.id">
      <my-youtube-list-item
        @delete-video="refreshCur"
        :my-youtube-video="myYoutubeVideo"
      ></my-youtube-list-item>
    </div>
  </div>
</template>
<script>
import MyYoutubeListItem from "./MyYoutubeListItem.vue";
import { mapGetters } from "vuex";

export default {
  name: "MyYoutubeList",
  components: { MyYoutubeListItem },
  data() {
    return {
      myYoutubeVideos: Array,
    };
  },
  created() {
    this.reload();
  },
  computed: {
    ...mapGetters(["myVideoList"]),
  },
  methods: {
    // ...mapActions(["getMyVideo"]),
    refreshCur() {
      this.$router.go();
    },
  },
};
</script>
<style></style>

<template>
  <div>
    <v-icon>mdi-close</v-icon>
    <div>게시</div>
    <!-- eslint-disable-next-line -->
    <youtube-edit-tool :youtubePk="youtubePk" :youtubeId="youtubeId"></youtube-edit-tool>
    <upload-form></upload-form>
  </div>
</template>

<script>
import YoutubeEditTool from "@/components/Home/YoutubeEditTool.vue";
import UploadForm from "@/components/Home/UploadForm.vue";

export default {
  name: "YoutubeEditView",
  components: { YoutubeEditTool, UploadForm },
  // youtubePk: DB에서 영상을 분류하는 번호
  // youtubeId: youtube에서 영상을 불러오는 ID
  data() {
    return {
      youtubePk: null,
      youtubeId: null,
    };
  },
  mounted() {
    this.youtubePk = this.$route.query.youtubePk;
    this.youtubeId = this.$route.query.youtubeId;
  },
};
</script>

<style></style>

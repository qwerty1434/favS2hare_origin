<template>
  <!-- eslint-disable -->
  <v-container>
    <v-row class="pb-2">
      <v-col cols="1">
        <v-icon @click="goBack">mdi-arrow-left</v-icon>
      </v-col>
      <v-col class="ml-2"><h4>저장된 영상</h4></v-col>
    </v-row>
    <v-divider></v-divider>
    <my-youtube-list></my-youtube-list>
  </v-container>
</template>
<script>
import MyYoutubeList from "@/components/common/MyYoutubeList.vue";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "MyYoutubeView",
  components: { MyYoutubeList },
  data() {
    return {
      youtubeList: [],
    };
  },
  created() {
    this.getMyVideo({ userId: this.userId });
    // this.myYoutubeVideos = this.myVideoList;
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  methods: {
    ...mapActions(["getMyVideo"]),
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style>
.v-input__slot {
  min-height: 35px !important;
}
</style>

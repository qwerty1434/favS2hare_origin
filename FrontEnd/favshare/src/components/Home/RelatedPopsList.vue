<template>
  <!-- eslint-disable -->
  <div>
    <v-row class="related-font">
      <v-col cols="4"> 관련 Pops </v-col>
      <v-col class="ml-n9">
        <h5 class="red--text">({{ popsList.length }})</h5>
      </v-col>
    </v-row>
    <!-- <v-sheet class="mx-auto" max-width="360">
      <v-slide-group multiple>
        <v-slide-item v-for="popsItem in popsList" :key="popsItem.id">
          <related-pops-list-item
            :pops-item="popsItem"
            :url="url"
            class="mx-2"
          ></related-pops-list-item>
        </v-slide-item>
      </v-slide-group>
    </v-sheet> -->
    <v-row class="mt-n5 mb-14">
      <v-col cols="6" align="center" v-for="popsItem in popsList" :key="popsItem.id">
        <related-pops-list-item :pops-item="popsItem" :url="url"></related-pops-list-item>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import RelatedPopsListItem from "./RelatedPopsListItem.vue";
import { mapGetters } from "vuex";

export default {
  components: { RelatedPopsListItem },
  name: "RelatedPopsList",
  // props: {
  //   youtubeId: Number, // 서버 id
  // },
  data() {
    return {
      popsList: Array,
      url: String,
    };
  },
  computed: {
    ...mapGetters(["videoInfo", "userId"]),
  },
  methods: {
    getPopsList() {
      axios({
        method: "post",
        url: "http://13.124.112.241:8080/youtube/detail",
        data: {
          userId: this.userId,
          youtubeUrl: this.videoInfo.videoId,
        },
      })
        .then((res) => {
          console.log("릴레이티드 팝스 리스트", res.data);
          this.popsList = res.data.popList;
          this.url = res.data.url;
          console.log(this.popsList);
          console.log(this.url);
        })
        .catch((res) => {
          console.log(this.userId);
          console.log(this.videoInfo.videoId);
          console.log(res);
        });
    },
    setDummyData() {
      this.popsList = [
        {
          popsId: 1,
          youtubeId: this.youtubePk,
          youtubeUrl: "rWHwQVgGke8",
          startSecond: 1,
          endSecond: 5,
          view: 100,
        },
        {
          popsId: 2,
          youtubeId: this.youtubePk,
          youtubeUrl: "1RGmbVhGZLQ",
          startSecond: 2,
          endSecond: 10,
          view: 100,
        },
        {
          popsId: 3,
          youtubeId: this.youtubePk,
          youtubeUrl: "vPwaXytZcgI",
          startSecond: 20,
          endSecond: 25,
          view: 100,
        },
      ];
    },
  },
  created() {
    // this.setDummyData();
    this.getPopsList();
  },
};
</script>

<style>
.popsitem {
  margin-top: -25px;
}
.related-font {
  padding-top: 5px;
  padding-bottom: 6px;
  font-weight: bold;
}
</style>

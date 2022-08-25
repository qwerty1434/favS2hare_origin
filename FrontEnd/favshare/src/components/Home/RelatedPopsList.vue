<template>
  <div>
    <v-row class="related-font">
      <v-col cols="4"> 관련 Pops </v-col>
      <v-col class="ml-n9">
        <h5 class="red--text">({{ popsList.length }})</h5>
      </v-col>
    </v-row>
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
import api from "@/api/springRestAPI";

export default {
  components: { RelatedPopsListItem },
  name: "RelatedPopsList",
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
        url: api.youtube.detail(),
        data: {
          userId: this.userId,
          youtubeUrl: this.videoInfo.videoId,
        },
      }).then((res) => {
        this.popsList = res.data.popList;
        this.url = res.data.url;
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

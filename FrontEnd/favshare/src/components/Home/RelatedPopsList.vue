<template>
  <div>
    <!-- <v-sheet class="mx-auto" max-width="360">
      <v-slide-group multiple>
        <v-slide-item v-for="popsItem in popsList" :key="popsItem.popsId">
          <related-pops-list-item
            :pops-item="popsItem"
            class="mx-2"
          ></related-pops-list-item>
        </v-slide-item>
      </v-slide-group>
    </v-sheet> -->
    <div v-for="popsItem in popsList" :key="popsItem.popId" class="popsitem">
      <related-pops-list-item :pops-item="popsItem"></related-pops-list-item>
    </div>
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
    };
  },
  computed: {
    ...mapGetters(["videoInfo"]),
  },
  methods: {
    getPopsList() {
      axios({
        method: "get",
        url: `http://localhost:8080/youtube/detail/${this.videoInfo.youtubeId}`,
      }).then((res) => {
        this.popsList = res.data.popsList;
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
    this.setDummyData();
  },
};
</script>

<style>
.popsitem {
  padding-right: 10px;
}
</style>

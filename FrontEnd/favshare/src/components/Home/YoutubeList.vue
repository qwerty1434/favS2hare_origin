<template>
  <div>
    <div
      v-for="homeYoutube in scrollTestYoutubes"
      :key="homeYoutube.youtubeId"
      class="videos__container"
    >
      <youtube-list-item :home-youtube="homeYoutube"></youtube-list-item>
    </div>
    <div v-if="scrollTestYoutubes.length" v-observe-visibility="handleScrolledToBottom"></div>
  </div>
</template>

<script>
import YoutubeListItem from "./YoutubeListItem.vue";
import { mapActions, mapGetters } from "vuex";
import Vue from "vue";
import VueObserveVisibility from "vue-observe-visibility";

Vue.use(VueObserveVisibility);

export default {
  components: { YoutubeListItem },
  name: "YoutubeList",
  data() {
    return {
      scrollTestYoutubes: [],
      page: 1,
    };
  },
  computed: {
    ...mapGetters(["homeYoutubes"]),
  },
  methods: {
    ...mapActions(["fetchHomeYoutubes"]),
    handleScrolledToBottom(isVisible) {
      if (!isVisible) {
        return;
      }
      this.page++;
      this.homeToScroll();
    },
    homeToScroll() {
      const nextPush = this.homeYoutubes.slice(5 * (this.page - 1), 5 * this.page);
      this.scrollTestYoutubes.push(...nextPush);
    },
  },
  created() {
    this.fetchHomeYoutubes();
    this.homeToScroll();
  },
  watch: {
    "$store.state.home.homeYoutubes": function () {
      console.log(this.$store.state.home.homeYoutubes);
    },
  },
};
</script>

<style scoped>
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

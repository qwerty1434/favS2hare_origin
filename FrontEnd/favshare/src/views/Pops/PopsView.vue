<template>
  <!-- eslint-disable -->
  <div>
    <div class="top">
      <search-bar @input-change="searchOnEnter"></search-bar>
      <div class="pops-view">
        <pops-type-list @click-type="searchOnEnter"></pops-type-list>
      </div>
    </div>
    <v-divider></v-divider>
    <div v-if="searchSuccess" class="pops-body">
      <pops-list :popsList="popsList"></pops-list>
    </div>
    <div class="bottom">
      <bottom-navigation-bar class="bottom"></bottom-navigation-bar>
    </div>
  </div>
</template>

<script>
import SearchBar from "@/components/common/SearchBar.vue";
import PopsTypeList from "@/components/Pops/PopsTypeList.vue";
import PopsList from "@/components/Pops/PopsList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "PopsView",
  components: {
    SearchBar,
    PopsTypeList,
    PopsList,
    BottomNavigationBar,
  },
  data() {
    return {
      searchSuccess: false,
      popsList: [],
    };
  },
  created() {
    this.getDummyOriginalAndPops();
  },
  methods: {
    searchOnEnter: function (keyword) {
      console.log(keyword);
      this.searchSuccess = true;
    },
    getDummyOriginalAndPops() {
      (this.youtube = {
        youtubeId: 1,
        youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
        youtubeContent: "?",
      }),
        (this.popsList = [
          {
            popsId: 1,
            youtubeId: 1,
            youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
            startSecond: 10,
            endSecond: 15,
            view: 30,
            count: 2,
          },
          {
            popsId: 2,
            youtubeId: 1,
            youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
            startSecond: 100,
            endSecond: 110,
            view: 45,
            count: 17,
          },
        ]);
    },
  },
};
</script>

<style scoped>
.pops-body {
  padding-top: 130px;
}
.top {
  position: fixed;
  width: 100%;
}
.pops-view {
  padding-top: 45px;
}
.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}

.bottom {
  position: fixed;
  width: 100%;
  bottom: 0;
}

.top {
  position: fixed;
  width: 100%;
  top: 0;
}
</style>

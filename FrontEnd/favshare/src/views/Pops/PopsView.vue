<template>
  <v-container>
    <div>
      <search-bar @input-change="searchOnEnter"></search-bar>
    </div>
    <div>
      <pops-type-list @click-type="searchOnEnter"></pops-type-list>
    </div>
    <div v-if="searchSuccess">
      <linked-pops-list :popsList="popsList"></linked-pops-list>
    </div>
    <div>
      <bottom-navigation-bar-vue></bottom-navigation-bar-vue>
    </div>
  </v-container>
</template>

<script>
import SearchBar from "@/components/common/SearchBar.vue";
import PopsTypeList from "@/components/Pops/PopsTypeList.vue";
import LinkedPopsList from "@/components/Pops/LinkedPopsList.vue";
import BottomNavigationBarVue from "@/components/BottomNavigationBar.vue";

export default {
  name: "PopsView",
  components: {
    SearchBar,
    PopsTypeList,
    LinkedPopsList,
    BottomNavigationBarVue,
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

<style></style>

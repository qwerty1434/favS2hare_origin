<template>
  <div>
    <v-row class="mr-1 ml-1">
      <v-subheader>POPS</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader class="more-font">
        <v-card class="blue--text" elevation="0" @click="goSearchedPopsMore">더보기</v-card>
      </v-subheader>
    </v-row>
    <div class="mt-n7">
      <search-pops-list :pops-list="popsList"></search-pops-list>
    </div>
    <v-divider></v-divider>
    <v-row class="mr-1 ml-1 mt-1">
      <v-subheader>동영상</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader class="more-font">
        <router-link
          :to="{
            name: 'searchmoreyoutube',
            params: { keyword: this.searchedKeyword },
          }"
          >더보기</router-link
        >
      </v-subheader>
    </v-row>
    <div class="mt-n7">
      <search-video-list :youtube-list="youtubeList"></search-video-list>
    </div>
    <v-divider></v-divider>
    <v-row class="mr-1 ml-1 mt-1">
      <v-subheader>친구</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader class="more-font">
        <router-link
          :to="{
            name: 'searchmorefollowing',
            params: { keyword: this.searchedKeyword },
          }"
          >더보기</router-link
        >
      </v-subheader>
    </v-row>
    <div class="mt-n7">
      <search-following-list :user-list="userList"> </search-following-list>
    </div>
  </div>
</template>
<script>
import SearchPopsList from "@/components/Home/search/SearchPopsList.vue";
import SearchVideoList from "./SearchVideoList.vue";
import SearchFollowingList from "./SearchFollowingList.vue";

export default {
  name: "SearchResult",
  components: { SearchPopsList, SearchVideoList, SearchFollowingList },
  props: {
    popsList: Array,
    youtubeList: Array,
    userList: Array,
    searchedKeyword: String,
  },
  created() {},
  methods: {
    goSearchedPopsMore() {
      this.$router.push({
        name: "searchmorepops",
        params: { keyword: this.searchedKeyword },
      });
    },
  },
};
</script>
<style>
.more-font {
  font-size: 11px;
}
</style>

<template>
  <div>
    <v-row class="mr-1 ml-1 mt-1">
      <v-subheader>POPS</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader class="more-font">
        <router-link to="/searchmorepops">더보기</router-link>
      </v-subheader>
    </v-row>
    <div>
      <search-pops-list :pops-list="popsList"></search-pops-list>
    </div>
    <v-divider></v-divider>
    <v-row class="mr-1 ml-1 mt-1">
      <v-subheader>동영상</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader class="more-font">
        <router-link to="/searchmoreyoutube">더보기</router-link>
      </v-subheader>
    </v-row>
    <div>
      <search-video-list :youtube-list="youtubeList"></search-video-list>
    </div>
    <v-divider></v-divider>
    <v-row class="mr-1 ml-1 mt-1">
      <v-subheader>친구</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader class="more-font">
        <router-link to="/searchmorefollowing">더보기</router-link>
      </v-subheader>
    </v-row>
    <div>
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
  },
  created() {},
};
</script>
<style>
.more-font {
  font-size: 11px;
}
</style>

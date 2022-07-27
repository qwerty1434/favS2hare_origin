<template>
  <v-list two-line subheader>
    <v-row>
      <v-subheader>searchpopslist</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader>
        <router-link to="/searchmorepops">더보기</router-link>
      </v-subheader>
    </v-row>
    <v-list-item>
      <search-pops-list></search-pops-list>
    </v-list-item>
    <v-row>
      <v-subheader>searchvideolist</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader>
        <router-link to="/searchmoreyoutube">더보기</router-link>
      </v-subheader>
    </v-row>
    <v-list-item>
      <search-video-list></search-video-list>
    </v-list-item>
    <v-row>
      <v-subheader>searchfollowinglist</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader>
        <router-link to="/searchmorefollowing">더보기</router-link>
      </v-subheader>
    </v-row>
    <v-list-item>
      <search-following-list></search-following-list>
    </v-list-item>
  </v-list>
</template>
<script>
import SearchPopsList from "@/components/Home/search/SearchPopsList.vue";
import SearchVideoList from "./SearchVideoList.vue";
import SearchFollowingList from "./SearchFollowingList.vue";

export default {
  name: "SearchResult",
  components: { SearchPopsList, SearchVideoList, SearchFollowingList },
};
</script>
<style></style>

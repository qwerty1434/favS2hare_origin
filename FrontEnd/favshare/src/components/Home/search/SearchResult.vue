<template>
  <v-list two-line subheader>
    <v-row>
      <v-subheader>searchpopslist</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader>
        <router-link to="/searchmorepops">더보기</router-link>
      </v-subheader>
    </v-row>
    <v-list-item class="mb-2">
      <search-pops-list></search-pops-list>
    </v-list-item>
    <v-divider></v-divider>
    <v-row class="mt-1">
      <v-subheader>searchvideolist</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader>
        <router-link to="/searchmoreyoutube">더보기</router-link>
      </v-subheader>
    </v-row>
    <v-list-item class="mb-2">
      <search-video-list></search-video-list>
    </v-list-item>
    <v-divider></v-divider>
    <v-row class="mt-1">
      <v-subheader>searchfollowinglist</v-subheader>
      <v-spacer></v-spacer>
      <v-subheader>
        <router-link to="/searchmorefollowing">더보기</router-link>
      </v-subheader>
    </v-row>
    <v-list-item class="mb-2">
      <search-following-list> </search-following-list>
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

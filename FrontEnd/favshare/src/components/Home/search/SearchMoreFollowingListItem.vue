<template>
  <!-- eslint-disable -->
  <v-hover>
    <v-row @click="">
      <v-col align="center" cols="3">
        <v-avatar color="orange lighten-4" size="55">
          <img
            class="following-size"
            :src="searchedFollowingListItem.profileImageUrl"
            alt="image"
          />
        </v-avatar>
      </v-col>
      <v-col class="ml-n4">
        <h5 class="mt-3">
          {{ searchedFollowingListItem.nickname }}
        </h5>
        <h5 class="grey--text">
          {{ searchedFollowingListItem.content }}
        </h5>
      </v-col>
    </v-row>
  </v-hover>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  name: "SearchMoreFollowingListItem",
  data() {
    return {};
  },
  props: {
    searchedFollowingListItem: Object,
  },
  computed: {
    ...mapGetters([""]),
  },
};
</script>
<style>
.following-size {
  height: 100px;
  width: 100px;
}
.v-row:hover {
  color: red;
}
</style>

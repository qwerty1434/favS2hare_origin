<template>
  <v-card class="following-size">
    <v-img class="following-size" src="@/assets/sample/popssample.jpg" />
  </v-card>
</template>
<script>
export default {
  name: "SearchFollowingListItem",
  data() {
    return {
      searchedFollowing: [],
    };
  },
};
</script>
<style>
.following-size {
  height: 100px;
  width: 100px;
}
</style>

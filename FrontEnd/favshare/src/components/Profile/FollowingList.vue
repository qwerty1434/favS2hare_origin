<template>
  <v-container>
    <div v-for="(item, index) in 10" :key="index">
      <following-list-item></following-list-item>
    </div>
  </v-container>
</template>
<script>
import FollowingListItem from "./FollowingListItem.vue";

export default {
  name: "FollowingList",
  components: { FollowingListItem },
};
</script>
<style></style>

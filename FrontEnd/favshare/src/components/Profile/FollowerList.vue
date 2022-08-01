<template>
  <v-container>
    <div v-for="(item, index) in 10" :key="index">
      <follower-list-item></follower-list-item>
    </div>
  </v-container>
</template>
<script>
import FollowerListItem from "./FollowerListItem.vue";
export default {
  name: "FollowerLsit",
  components: { FollowerListItem },
};
</script>
<style></style>

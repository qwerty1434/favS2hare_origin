<template>
  <!-- eslint-disable -->
  <div class="mr-4">
    <v-row>
      <v-col align="center">
        <v-avatar color="orange lighten-4" size="65">
          <img class="following-size" :src="userListItem.profileImageUrl" alt="image" />
        </v-avatar>
        <h6 class="mt-3">
          {{ userListItem.nickname }}
        </h6>
      </v-col>
    </v-row>
  </div>
</template>
<script>
export default {
  name: "SearchFollowingListItem",
  data() {
    return {};
  },
  props: {
    userListItem: Object,
  },
};
</script>
<style>
.following-size {
  height: 70px;
  width: 70px;
}
</style>

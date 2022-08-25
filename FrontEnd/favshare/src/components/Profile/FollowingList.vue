<template>
  <v-container class="following">
    <div v-for="(followingItem, index) in followingList" :key="index">
      <following-list-item :following-item="followingItem"></following-list-item>
    </div>
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
import FollowingListItem from "./FollowingListItem.vue";

export default {
  name: "FollowingList",
  components: { FollowingListItem },
  computed: {
    ...mapGetters(["followingList"]),
  },
  methods: {
    ...mapActions(["fetchFollowingList"]),
  },
  created() {
    this.fetchFollowingList();
  },
};
</script>

<style scoped>
.following {
  padding-top: 100px;
}
</style>

<template>
  <v-container>
    <div v-for="(follwerItem, index) in follwerList" :key="index">
      <follower-list-item :follower-item="followerItem"></follower-list-item>
    </div>
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
import FollowerListItem from "./FollowerListItem.vue";
export default {
  name: "FollowerLsit",
  components: { FollowerListItem },
  computed: {
    ...mapGetters(["followerList"]),
  },
  methods: {
    ...mapActions(["fetchFollowerList"]),
  },
  created() {
    this.fetchFollowerList();
  },
};
</script>
<style></style>

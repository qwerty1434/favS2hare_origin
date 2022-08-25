<template>
  <v-container class="follower">
    <div v-for="(followerItem, index) in followerList" :key="index">
      <follower-list-item :follower-item="followerItem"></follower-list-item>
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import FollowerListItem from "./FollowerListItem.vue";
export default {
  name: "FollowerList",
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

<style scoped>
.follower {
  padding-top: 100px;
}
</style>

<template>
  <!-- eslint-disable -->
  <div class="rpops">
    <div v-for="userListItem in userList">
      <search-following-list-item :user-list-item="userListItem"></search-following-list-item>
    </div>
  </div>
</template>
<script>
import SearchFollowingListItem from "./SearchFollowingListItem.vue";

export default {
  name: "SearchFollowingList",
  components: { SearchFollowingListItem },
  data() {
    return {};
  },
  props: {
    userList: Array,
  },
};
</script>
<style></style>

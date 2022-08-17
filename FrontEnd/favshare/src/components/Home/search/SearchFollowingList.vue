<template>
  <!-- eslint-disable -->
  <div class="friend-search mt-7">
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
<style>
.friend-search {
  /* 가로 스크롤 */
  height: 150px;
  overflow: scroll;
  white-space: nowrap;
  display: flex;
}
</style>

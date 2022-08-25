<template>
  <div class="pops-search mt-5">
    <div v-for="popsListItem in popsList" :key="popsListItem.id">
      <search-pops-list-item
        :pops-list-item="popsListItem"
      ></search-pops-list-item>
    </div>
  </div>
</template>
<script>
import SearchPopsListItem from "./SearchPopsListItem.vue";
import { mapGetters } from "vuex";

export default {
  name: "SearchPopsList",
  components: { SearchPopsListItem },
  data() {
    return {};
  },
  props: {
    popsList: Array,
  },
  computed: {
    ...mapGetters(["searchedPopsList"]),
  },
  created() {},
};
</script>
<style>
.pops-search {
  height: 250px;
  overflow: scroll;
  white-space: nowrap;
  display: flex;
}
</style>

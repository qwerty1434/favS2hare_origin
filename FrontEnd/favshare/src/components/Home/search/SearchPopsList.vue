<template>
  <!-- eslint-disable -->
  <div class="rpops">
    <div class="wrap-vertical" v-for="popsListItem in popsList">
      <search-pops-list-item :pops-list-item="popsListItem"></search-pops-list-item>
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
<style></style>

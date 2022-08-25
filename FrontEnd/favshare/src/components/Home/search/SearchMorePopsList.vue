<template>
  <!-- eslint-disable -->
  <v-container>
    <v-row class="mt-1">
      <v-col
        align="center"
        v-for="searchedPopsListItem in searchedPopsList"
        :key="searchedPopsListItem.id"
      >
        <search-more-pops-list-item
          :searched-pops-list-item="searchedPopsListItem"
        ></search-more-pops-list-item>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import SearchMorePopsListItem from "./SearchMorePopsListItem.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "SearchMorePopsList",
  components: { SearchMorePopsListItem },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["searchedPopsList"]),
  },
  methods: {
    ...mapActions([]),
  },
};
</script>
<style></style>

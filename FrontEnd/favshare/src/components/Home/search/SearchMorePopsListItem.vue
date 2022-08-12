<template>
  <!-- eslint-disable -->
  <v-hover v-slot="{ hover }">
    <v-card :elevation="hover ? 16 : 2" class="pops-size">
      <v-img height="170" class="pops-size" src="@/assets/sample/popssample.jpg"> </v-img>
      <v-card-actions class="mb-n3">
        <h5>
          {{ searchedPopsListItem.name }}
        </h5>
      </v-card-actions>
      <v-card-actions class="ma-1">
        <h5 class="grey--text">
          {{ searchedPopsListItem.id }}
        </h5>
        <v-spacer></v-spacer>
        <h6>
          <v-icon x-small> mdi-eye </v-icon>
          {{ searchedPopsListItem.views }}&nbsp;&nbsp;
          <v-icon x-small> mdi-heart </v-icon>
          {{ searchedPopsListItem.likeCount }}
        </h6>
      </v-card-actions>
    </v-card>
  </v-hover>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  name: "SearchMorePopsListItem",
  data() {
    return {};
  },
  props: {
    searchedPopsListItem: Object,
  },
  computed: {
    ...mapGetters([""]),
  },
  methods: {},
};
</script>
<style lang="sass" scoped>
.v-card.on-hover.theme--dark
  background-color: rgba(#FFF, 0.8)
  >.v-card__text
    color: #000
</style>

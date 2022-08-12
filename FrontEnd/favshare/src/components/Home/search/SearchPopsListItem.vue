<template>
  <!-- eslint-disable -->
  <div>
    <v-card class="pops-size mr-4">
      <v-img height="170" class="pops-size" src="@/assets/sample/popssample.jpg"> </v-img>
      <v-card-actions class="mb-n3">
        <h5>
          {{ popsListItem.name }}
        </h5>
      </v-card-actions>
      <v-card-actions class="ma-1">
        <h5 class="grey--text">
          {{ popsListItem.id }}
        </h5>
        <v-spacer></v-spacer>
        <h6>
          <v-icon x-small> mdi-eye </v-icon>
          {{ popsListItem.views }}&nbsp;&nbsp;
          <v-icon x-small> mdi-heart </v-icon>
          {{ popsListItem.likeCount }}
        </h6>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
export default {
  name: "SearchPopsListItem",

  data() {
    return {
      searchedPops: [],
    };
  },
  props: {
    popsListItem: Object,
  },
  created() {},
};
</script>
<style>
.pops-size {
  height: 230px;
  width: 150px;
}
</style>

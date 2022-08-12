<template>
  <div>
    <v-card class="pops-size mr-4">
      <v-img class="pops-size" src="@/assets/sample/popssample.jpg">
        <br />
        <br />
        {{ popsListItem.content }}
        {{ popsListItem.id }}
        {{ popsListItem.views }}
      </v-img>
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
  height: 250px;
  width: 150px;
}
</style>

<template>
  <v-container style="display: flex">
    <v-row>
      <v-card class="pops-size">
        <v-img class="pops-size" src="@/assets/sample/popssample.jpg" />
      </v-card>
      <v-card class="pops-size">
        <v-img class="pops-size" src="@/assets/sample/popssample.jpg" />
      </v-card>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "SearchMorePopsListItem",

  data() {
    return {};
  },
};
</script>
<style>
.pops-size {
  height: 250px;
  width: 150px;
}
</style>

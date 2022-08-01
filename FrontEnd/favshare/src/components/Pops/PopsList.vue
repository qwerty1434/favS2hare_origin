<template>
  <v-continer>
    <div>
      <pops-list-item></pops-list-item>
    </div>
  </v-continer>
</template>
<script>
import PopsListItem from "./PopsListItem.vue";

export default {
  name: "PopsList",
  components: { PopsListItem },
};
</script>
<style></style>

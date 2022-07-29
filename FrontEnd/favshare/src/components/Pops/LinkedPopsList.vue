<template>
  <div>
    <linked-pops-list-item
      v-for="popsListItem in popsList"
      :popsId="popsListItem.popsId"
      :youtubeUrl="popsListItem.youtubeUrl"
      :startSecond="popsListItem.startSecond"
      :endSecond="popsListItem.endSecond"
      :view="popsListItem.view"
      :count="popsListItem.count"
      :key="popsListItem.popsId"
    ></linked-pops-list-item>
  </div>
</template>

<script>
import LinkedPopsListItem from "@/components/Pops/LinkedPopsListItem.vue";

export default {
  name: "LinkedPopsList",
  components: { LinkedPopsListItem },
  props: ["popsList"],
};
</script>

<style></style>

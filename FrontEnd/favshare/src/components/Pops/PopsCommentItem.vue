<template>
  <v-container>
    <div v-for="(item, idx) in items" :key="idx">
      {{ item.content }}
      <v-spacer></v-spacer>
      {{ item.createDate }}
      <v-divider></v-divider>
    </div>
  </v-container>
</template>
<script>
export default {
  name: "PopsCommentItem",
  props: {
    items: { type: Array, default: () => [] },
  },
};
</script>
<style></style>

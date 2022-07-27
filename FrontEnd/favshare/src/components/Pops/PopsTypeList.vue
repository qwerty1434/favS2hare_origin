<template>
  <div>
    <div>
      <pops-type-list-item @click-type="searchPopsType"></pops-type-list-item>
    </div>
  </div>
</template>

<script>
import PopsTypeListItem from "./PopsTypeListItem.vue";

export default {
  name: "PopsTypeList",
  components: { PopsTypeListItem },
  methods: {
    searchPopsType: function (type) {
      this.$emit("click-type", type);
    },
  },
};
</script>

<style></style>

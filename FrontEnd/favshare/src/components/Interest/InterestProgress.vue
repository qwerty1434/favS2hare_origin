<template>
  <div>
    <v-progress-linear
      :value="value"
      background-color="#EFEFF0"
      color="#FFE3A9"
      height="8"
      rounded
    ></v-progress-linear>
  </div>
</template>

<script>
export default {
  name: "InterestProgress",
  props: ["currentPage", "totalPage"],
  computed: {
    value() {
      return (this.currentPage / this.totalPage) * 100;
    },
  },
};
</script>

<style></style>

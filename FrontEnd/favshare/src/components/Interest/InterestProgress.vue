<template>
  <div>
    <v-progress-linear
      class="progress-bar"
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
  data() {
    return {
      // progress bar를 채울 퍼센트(%)
      value: 0,
    };
  },
  mounted() {
    this.value = (this.currentPage / this.totalPage) * 100;
  },
};
</script>

<style>
.progress-bar {
  width: 90%;
}
</style>

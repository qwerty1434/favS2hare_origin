<template>
  <div>
    <div v-for="popItem in popList" :key="popItem.id" class="videos__container">
      <pops-list-item :pop-item="popItem"></pops-list-item>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import PopsListItem from "./PopsListItem.vue";

export default {
  name: "PopsList",
  components: { PopsListItem },
  computed: {
    ...mapGetters(["userId", "popList"]),
  },
  watch: {
    "$store.state.pop.popList": function () {
      console.log(this.$store.state.pop.popList);
    },
  },
};
</script>
<style>
.videos__container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex-wrap: wrap;
  border: 10px;
}
</style>

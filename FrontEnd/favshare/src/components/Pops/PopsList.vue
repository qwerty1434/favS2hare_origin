<template>
  <!-- eslint-disable -->
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
  // data() {
  //   return {
  //     window: 0,
  //   };
  // },
  watch: {
    "$store.state.pop.popList": function () {
      console.log(this.$store.state.pop.popList);
    },
  },
  // methods: {
  //   fetchPopList() {
  //     axios({
  //       method: "post",
  //       url: "http://13.124.112.241:8080/pop",
  //       data: {
  //         idolId: 0,
  //         userId: this.userId,
  //       },
  //     })
  //       .then((res) => {
  //         this.popList = res.data;
  //       })
  //       .catch((err) => console.log("popsList", err));
  //   },
  // },
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

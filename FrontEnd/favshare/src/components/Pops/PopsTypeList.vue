<template>
  <div class="pops-type mt-2">
    <div v-for="popsTypeItem in popsTypeList" :key="popsTypeItem.id">
      <pops-type-list-item
        @click-type="searchPopsType"
        :pops-type-item="popsTypeItem"
      ></pops-type-list-item>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import PopsTypeListItem from "./PopsTypeListItem.vue";

export default {
  name: "PopsTypeList",
  components: { PopsTypeListItem },
  // data() {
  //   return {
  //     popsTypeList: Array,
  //   };
  // },
  computed: {
    ...mapGetters(["userId", "popsTypeList"]),
  },
  methods: {
    searchPopsType: function (type) {
      this.$emit("click-type", type);
    },
    ...mapActions(["fetchInterestIdol"]),
  },
  created() {
    this.fetchInterestIdol();
  },
};
</script>

<style>
.pops-type {
  /* 가로 스크롤 */
  height: 100px;
  overflow: auto;
  white-space: nowrap;
}
</style>

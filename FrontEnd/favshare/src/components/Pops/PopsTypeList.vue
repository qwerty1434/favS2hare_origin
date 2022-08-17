<template>
  <!-- eslint-disable -->
  <!-- <div class="pops-type mt-2">
    <div v-for="popsTypeItem in popsTypeList" :key="popsTypeItem.id">
      <pops-type-list-item
        @click-type="searchPopsType"
        :pops-type-item="popsTypeItem"
      ></pops-type-list-item>
    </div>
  </div> -->
  <div class="pops-type no-scroll">
    <div class="pops-type-size" v-for="popsTypeItem in popsTypeList" :key="popsTypeItem.id">
      <pops-type-list-item
        class="ma-3"
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
  height: 102px;
  overflow: scroll;
  white-space: nowrap;
  display: flex;
}

/* 스크롤바 제거 */
.no-scroll::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
</style>

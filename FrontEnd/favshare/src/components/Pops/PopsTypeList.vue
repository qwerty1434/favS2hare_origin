<template>
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
  height: 102px;
  overflow: scroll;
  white-space: nowrap;
  display: flex;
}

.no-scroll::-webkit-scrollbar {
  display: none;
}
</style>

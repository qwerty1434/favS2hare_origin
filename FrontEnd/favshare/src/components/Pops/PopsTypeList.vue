<template>
  <!-- <div class="pops-type mt-2">
    <div v-for="popsTypeItem in popsTypeList" :key="popsTypeItem.id">
      <pops-type-list-item
        @click-type="searchPopsType"
        :pops-type-item="popsTypeItem"
      ></pops-type-list-item>
    </div>
  </div> -->
  <div>
    <v-sheet class="mx-auto" max-width="330">
      <v-slide-group multiple>
        <v-slide-item
          v-for="popsTypeItem in popsTypeList"
          :key="popsTypeItem.id"
        >
          <pops-type-list-item
            @click-type="searchPopsType"
            :pops-type-item="popsTypeItem"
          ></pops-type-list-item>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
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

<style scoped>
.pops-type {
  margin: auto;
}
</style>

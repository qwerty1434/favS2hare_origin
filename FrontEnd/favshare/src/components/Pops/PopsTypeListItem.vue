<template>
  <!-- eslint-disable -->
  <div class="mt-n1" align="center">
    <v-avatar size="60px" @click="fetchPopList(popsTypeItem.id)">
      <img :src="popsTypeItem.idolImageUrl" alt="" />
    </v-avatar>
    <div class="mt-2">
      <h5>{{ popsTypeItem.name }}</h5>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "PopsTypeListItem",
  data() {
    return {
      type1: "type1",
    };
  },
  props: {
    popsTypeItem: Object,
  },
  methods: {
    ...mapActions(["fetchPopList"]),
    SearchPopsByType() {
      console.log("type click");
      this.$emit("click-type", this.type1);
    },
  },
};
</script>

<style>
.pops-type-size {
  margin-top: 15px;
  height: 90px;
  width: 90px;
}

.pops-type {
  width: 330px;
  margin: auto;
}

.pops-row {
  padding-top: 16px;
  padding-left: 15px;
}
</style>

<template>
  <div class="pops-type">
    <v-avatar @click="fetchPopList(popsTypeItem.id)">
      <img :src="popsTypeItem.idolImageUrl" alt="" />
    </v-avatar>
    <div>{{ popsTypeItem.name }}</div>
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
  height: 80px;
  width: 80px;
  margin: auto;
}

.pops-type {
  width: 50px;
  padding-right: 20px;
  margin: auto;
}

.pops-row {
  padding-top: 16px;
  padding-left: 15px;
}
</style>

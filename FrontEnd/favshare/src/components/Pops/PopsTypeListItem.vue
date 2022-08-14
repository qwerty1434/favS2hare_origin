<template>
  <div class="pops-type">
    <v-row class="pops-row">
      <div>
        <v-avatar>
          <img :src="popsTypeItem.idolImageUrl" alt="" />
        </v-avatar>
        <div>{{ popsTypeItem.name }}</div>
      </div>
    </v-row>
  </div>
</template>
<script>
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
  width: 330px;
  margin: auto;
}

.pops-row {
  padding-top: 16px;
  padding-left: 15px;
}
</style>

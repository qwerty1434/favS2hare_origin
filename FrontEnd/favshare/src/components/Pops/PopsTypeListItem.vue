<template>
  <v-row class="mt-1">
    <v-sheet
      v-model="type1"
      color="red lighten-3"
      class="pops-type-size"
      :rounded="'xl'"
      @click="SearchPopsByType()"
    ></v-sheet>
    <v-sheet
      :rounded="'xl'"
      color="yellow lighten-3"
      class="pops-type-size"
      @click="SearchPopsByType()"
    ></v-sheet>
    <v-sheet
      :rounded="'xl'"
      color="teal lighten-3"
      class="pops-type-size"
      @click="SearchPopsByType()"
    ></v-sheet>
    <v-sheet
      :rounded="'xl'"
      color="green lighten-3"
      class="pops-type-size"
      @click="SearchPopsByType()"
    ></v-sheet>
    <!-- <v-sheet
      :rounded="'xl'"
      color="blue lighten-3"
      class="pops-type-size"
      @click="SearchPopsByType()"
    ></v-sheet> -->
  </v-row>
</template>
<script>
export default {
  name: "PopsTypeListItem",
  data() {
    return {
      type1: "type1",
    };
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
</style>

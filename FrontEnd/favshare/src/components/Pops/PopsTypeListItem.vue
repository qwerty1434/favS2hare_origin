<template>
  <div>
    <v-row class="ma-n3">
      <v-sheet
        v-model="type1"
        color="red lighten-3"
        class="mx-auto"
        height="70"
        width="70"
        rounded
        @click="SearchPopsByType()"
      ></v-sheet>
      <v-sheet
        rounded
        color="yellow lighten-3"
        class="mx-auto"
        height="70"
        width="70"
        @click="SearchPopsByType()"
      ></v-sheet>
      <v-sheet
        rounded
        color="teal lighten-3"
        class="mx-auto"
        height="70"
        width="70"
        @click="SearchPopsByType()"
      ></v-sheet>
      <v-sheet
        rounded
        color="green lighten-3"
        class="mx-auto"
        height="70"
        width="70"
        @click="SearchPopsByType()"
      ></v-sheet>
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
  methods: {
    SearchPopsByType() {
      console.log("type click");
      this.$emit("click-type", this.type1);
    },
  },
};
</script>
<style></style>

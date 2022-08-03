<template>
  <v-container>
    <div v-for="(item, index) in 10" :key="index">
      <alarm-list-item></alarm-list-item>
    </div>
  </v-container>
</template>
<script>
import AlarmListItem from "./AlarmListItem.vue";

export default {
  name: "AlarmList",
  components: { AlarmListItem },
};
</script>
<style></style>

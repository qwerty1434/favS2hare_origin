<template>
  <v-container>
    <v-row class="pb-2">
      <v-col cols="2">
        <v-icon @click="goBack">mdi-arrow-left</v-icon>
      </v-col>
      <v-col>알림</v-col>
    </v-row>
    <v-divider></v-divider>
    <div>
      <alarm-list></alarm-list>
    </div>
  </v-container>
</template>
<script>
import AlarmList from "@/components/common/AlarmList.vue";

export default {
  name: "AlarmView",
  components: { AlarmList },
  data() {
    return {};
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style></style>

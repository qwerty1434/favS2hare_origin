<template>
  <v-container>
    <div class="text-box">
      <h3 style="display: inline">관련 Pops</h3>
      <span class="count-pops">{{ countPops }}</span>
    </div>
    <v-row no-gutters space-between>
      <linked-pops-list-item
        v-for="linkedPopsListItem in linkedPopsList"
        :popsId="linkedPopsListItem.popsId"
        :youtubeUrl="linkedPopsListItem.youtubeUrl"
        :startSecond="linkedPopsListItem.startSecond"
        :endSecond="linkedPopsListItem.endSecond"
        :view="linkedPopsListItem.view"
        :count="linkedPopsListItem.count"
        :key="linkedPopsListItem.popsId"
      ></linked-pops-list-item>
    </v-row>
  </v-container>
</template>

<script>
import LinkedPopsListItem from "@/components/Pops/LinkedPopsListItem.vue";

export default {
  name: "LinkedPopsList",
  components: { LinkedPopsListItem },
  props: ["linkedPopsList"],
  computed: {
    countPops() {
      return this.linkedPopsList.length;
    },
  },
};
</script>

<style scoped>
.text-box {
  margin-bottom: 20px;
}

.count-pops {
  margin-left: 10px;
  color: #929292;
}
</style>

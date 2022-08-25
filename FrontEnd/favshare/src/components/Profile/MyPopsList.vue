<template>
  <v-container>
    <div class="text-box">
      <h3>전체 영상</h3>
    </div>
    <v-row no-gutters space-between>
      <my-pops-list-item
        ref="myPopsListItem"
        v-for="myPopsListItem in myPopsList"
        :id="myPopsListItem.id"
        :youtubeUrl="myPopsListItem.youtubeUrl"
        :startSecond="myPopsListItem.startSecond"
        :endSecond="myPopsListItem.endSecond"
        :key="myPopsListItem.id"
      ></my-pops-list-item>
    </v-row>
  </v-container>
</template>

<script>
import MyPopsListItem from "@/components/Profile/MyPopsListItem.vue";

export default {
  name: "MyPopsList",
  components: { MyPopsListItem },
  props: ["myPopsList"],
};
</script>

<style scoped>
.text-box {
  margin-bottom: 20px;
}
</style>

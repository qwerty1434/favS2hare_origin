<template>
  <div>
    <my-pops-list-item
      ref="myPopsListItem"
      v-for="myPopsListItem in myPopsList"
      :popsId="myPopsListItem.popsId"
      :youtubeUrl="myPopsListItem.youtubeUrl"
      :startSecond="myPopsListItem.startSecond"
      :endSecond="myPopsListItem.endSecond"
      :key="myPopsListItem.popsId"
    ></my-pops-list-item>
  </div>
</template>

<script>
import MyPopsListItem from "@/components/Profile/MyPopsListItem.vue";

export default {
  name: "MyPopsList",
  components: { MyPopsListItem },
  props: ["myPopsList"],
};
</script>

<style></style>

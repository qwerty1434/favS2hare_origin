<template>
  <div>
    <singer-choice-list-item
      v-for="singerListItem in singerList"
      :singerId="singerListItem.singerId"
      :name="singerListItem.name"
      :picture="singerListItem.picture"
      :key="singerListItem.id"
      @selectSinger="emitSelectSinger"
      @unselectSinger="emitUnselectSinger"
    ></singer-choice-list-item>
  </div>
</template>

<script>
import SingerChoiceListItem from "@/components/Interest/SingerChoiceListItem.vue";

export default {
  name: "SingerChoiceList",
  components: { SingerChoiceListItem },
  props: ["singerList"],
  methods: {
    emitSelectSinger(singerId) {
      this.$emit("emitSelectSinger", singerId);
    },
    emitUnselectSinger(singerId) {
      this.$emit("emitUnselectSinger", singerId);
    },
  },
};
</script>

<style></style>

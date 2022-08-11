<template>
  <v-row justify="space-between" no-gutters>
    <v-col cols="6" align="center">
      <div id="non-choice" @click="select" class="option unselected-option">
        선택 안함
      </div>
    </v-col>
    <singer-choice-list-item
      ref="singerChoiceListItem"
      v-for="singerListItem in singerList"
      :singerId="singerListItem.singerId"
      :name="singerListItem.name"
      :picture="singerListItem.picture"
      :key="singerListItem.id"
      @selectSinger="emitSelectSinger"
      @unselectSinger="emitUnselectSinger"
    ></singer-choice-list-item>
  </v-row>
</template>

<script>
import SingerChoiceListItem from "@/components/Interest/SingerChoiceListItem.vue";

export default {
  name: "SingerChoiceList",
  components: { SingerChoiceListItem },
  props: ["singerList"],
  data() {
    return {
      isSelected: false,
    };
  },
  watch: {
    isSelected(newVal) {
      const item = document.getElementById("non-choice");
      if (newVal) {
        item.setAttribute("class", "option selected-option");
        // 선택안함이 선택되면 다른 선택지들 선택 취소
        this.$refs.singerChoiceListItem.forEach((item) => {
          item.isSelected = false;
        });
      } else {
        item.setAttribute("class", "option unselected-option");
      }
    },
  },
  methods: {
    // 다른 선택지가 선택되면
    emitSelectSinger(singerId) {
      // 선택안함 버튼은 선택 취소
      this.isSelected = false;
      this.$emit("emitSelectSinger", singerId);
    },
    emitUnselectSinger(singerId) {
      this.$emit("emitUnselectSinger", singerId);
    },
    select() {
      // 이미 선택되어 있을 때
      if (this.isSelected) {
        this.isSelected = false;
      } else {
        this.isSelected = true;
      }
    },
  },
};
</script>

<style scoped>
.option {
  width: 150px;
  height: 120px;
  line-height: 120px;
  background-size: 150px 120px;
  margin-top: 16px;
  border: 2px solid;
  border-radius: 8px;
  font-size: 16px;
  color: #787878;
}

.unselected-option {
  background-color: #efeff0;
  border-color: #afb1b6;
}

.selected-option {
  background-color: #faf0f1;
  border-color: #ff5d5d;
}
</style>

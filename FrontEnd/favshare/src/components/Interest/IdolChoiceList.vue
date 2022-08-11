<template>
  <v-row justify="space-between" no-gutters>
    <v-col v-if="!isSearching" cols="6" align="center">
      <div id="non-choice" @click="select" class="option unselected-option">
        선택 안함
      </div>
    </v-col>
    <idol-choice-list-item
      ref="idolChoiceListItem"
      v-for="idolListItem in idolList"
      :id="idolListItem.id"
      :name="idolListItem.name"
      :picture="idolListItem.picture"
      :key="idolListItem.id"
      @selectIdol="emitSelectIdol"
      @unselectIdol="emitUnselectIdol"
    ></idol-choice-list-item>
  </v-row>
</template>

<script>
import IdolChoiceListItem from "@/components/Interest/IdolChoiceListItem.vue";

export default {
  name: "IdolChoiceList",
  components: { IdolChoiceListItem },
  props: ["idolList"],
  data() {
    return {
      // 검색 중일 때는 보이지 않도록
      isSearching: false,
      isSelected: false,
    };
  },
  watch: {
    isSelected(newVal) {
      const item = document.getElementById("non-choice");
      if (newVal) {
        item.setAttribute("class", "option selected-option");
        // 다른 선택지들 선택 취소
        if (this.$refs.idolChoiceListItem) {
          this.$refs.idolChoiceListItem.forEach((item) => {
            item.isSelected = false;
          });
        }
      } else {
        item.setAttribute("class", "option unselected-option");
      }
    },
  },
  methods: {
    // 다른 선택지가 선택되면
    emitSelectIdol(id) {
      // 선택안함 버튼은 선택 취소
      this.isSelected = false;
      this.$emit("emitSelectIdol", id);
    },
    emitUnselectIdol(id) {
      this.$emit("emitUnselectIdol", id);
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

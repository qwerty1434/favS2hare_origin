<template>
  <div>
    <div
      @click="select"
      :id="singerId"
      class="unselected-option"
      :style="{ 'background-image': 'url(' + picture + ')' }"
    >
      <div :id="singerId + name" class="unselected-name">
        {{ name }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SingerChoiceListItem",
  props: ["singerId", "name", "picture"],
  data() {
    return {
      isSelected: false,
    };
  },
  methods: {
    select() {
      const item = document.getElementById(this.singerId);
      const nameInItem = document.getElementById(this.singerId + this.name);
      // 이미 선택되었을 때
      if (this.isSelected) {
        // 스타일 변경
        item.setAttribute("class", "unselected-option");
        nameInItem.setAttribute("class", "unselected-name");
        // 선택취소 사실 전달
        this.$emit("unselectSinger", this.singerId);
        this.isSelected = false;
        // 선택되지 않았을 때
      } else {
        // 스타일 변경
        item.setAttribute("class", "selected-option");
        nameInItem.setAttribute("class", "selected-name");
        // 선택 사실 전달
        this.$emit("selectSinger", this.singerId);
        this.isSelected = true;
        console.log(this.picture);
      }
    },
  },
};
</script>

<style scoped>
.unselected-option {
  width: 150px;
  height: 120px;
  background-size: 150px 120px;
  padding: 15px;
  border: 2px solid;
  border-radius: 8px;
  background-color: #efeff0;
  border-color: #afb1b6;
}

.selected-option {
  width: 150px;
  height: 120px;
  background-size: 150px 120px;
  padding: 15px;
  border: 2px solid;
  border-radius: 8px;
  background-color: #faf0f1;
  border-color: #ff5d5d;
}

.unselected-name {
  width: 80px;
  height: 23px;
  color: white;
  text-align: center;
  background-color: #787878;
  border-radius: 16px;
}

.selected-name {
  width: 80px;
  height: 23px;
  color: white;
  text-align: center;
  background-color: #ff5d5d;
  border-radius: 16px;
}
</style>

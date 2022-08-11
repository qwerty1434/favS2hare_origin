<template>
  <v-col cols="6" align="center">
    <div
      @click="select"
      :id="singerId"
      class="option unselected-option"
      :style="{ 'background-image': 'url(' + picture + ')' }"
    >
      <div :id="singerId + name" class="name unselected-name">
        {{ name }}
      </div>
    </div>
  </v-col>
</template>

<script>
export default {
  name: "SingerChoiceListItem",
  props: ["index", "singerId", "name", "picture"],
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
        item.setAttribute("class", "option unselected-option");
        nameInItem.setAttribute("class", "name unselected-name");
        // 선택취소 사실 전달
        this.$emit("unselectSinger", this.singerId);
        this.isSelected = false;
        // 선택되지 않았을 때
      } else {
        // 스타일 변경
        item.setAttribute("class", "option selected-option");
        nameInItem.setAttribute("class", "name selected-name");
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
.option {
  width: 150px;
  height: 120px;
  background-size: 150px 120px;
  margin-top: 16px;
  border: 2px solid;
  border-radius: 8px;
  padding-top: 87px;
}

.unselected-option {
  background-color: #efeff0;
  border-color: #afb1b6;
}

.selected-option {
  background-color: #faf0f1;
  border-color: #ff5d5d;
}

.name {
  font-size: 10px;
  text-align: center;
  width: 100px;
  height: 20px;
  line-height: 23px;
  color: white;
  border-radius: 16px;
}

.unselected-name {
  background-color: #787878;
}

.selected-name {
  background-color: #ff5d5d;
}
</style>

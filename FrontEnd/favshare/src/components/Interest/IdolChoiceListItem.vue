<template>
  <v-col cols="6" align="center">
    <div
      @click="select"
      :id="id"
      class="option unselected-option"
      :style="{ 'background-image': 'url(' + picture + ')' }"
    >
      <div :id="id + name" class="name unselected-name">
        {{ name }}
      </div>
    </div>
  </v-col>
</template>

<script>
export default {
  name: "IdolChoiceListItem",
  props: ["index", "id", "name", "picture"],
  data() {
    return {
      isSelected: false,
    };
  },
  watch: {
    isSelected(newVal) {
      const item = document.getElementById(this.id);
      const nameInItem = document.getElementById(this.id + this.name);
      if (newVal) {
        item.setAttribute("class", "option selected-option");
        nameInItem.setAttribute("class", "name selected-name");
        this.$emit("selectIdol", this.id);
      } else {
        item.setAttribute("class", "option unselected-option");
        nameInItem.setAttribute("class", "name unselected-name");
        this.$emit("unselectIdol", this.id);
      }
    },
  },
  methods: {
    select() {
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

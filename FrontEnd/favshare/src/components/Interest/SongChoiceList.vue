<template>
  <v-row justify="space-between" no-gutters>
    <v-col cols="6" align="center">
      <div id="non-choice" @click="select" class="option unselected-option">
        선택 안함
      </div>
    </v-col>
    <song-choice-list-item
      ref="songChoiceListItem"
      v-for="songListItem in songList"
      :songId="songListItem.songId"
      :name="songListItem.name"
      :key="songListItem.id"
      @selectSong="emitSelectSong"
      @unselectSong="emitUnselectSong"
    ></song-choice-list-item>
  </v-row>
</template>

<script>
import SongChoiceListItem from "@/components/Interest/SongChoiceListItem.vue";

export default {
  name: "SongChoiceList",
  components: { SongChoiceListItem },
  props: ["songList"],
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
        this.$refs.songChoiceListItem.forEach((item) => {
          item.isSelected = false;
        });
      } else {
        item.setAttribute("class", "option unselected-option");
      }
    },
  },
  methods: {
    // 다른 선택지가 선택되면
    emitSelectSong(songId) {
      // 선택안함 버튼은 선택 취소
      this.isSelected = false;
      this.$emit("emitSelectSong", songId);
    },
    emitUnselectSong(songId) {
      this.$emit("emitUnselectSong", songId);
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

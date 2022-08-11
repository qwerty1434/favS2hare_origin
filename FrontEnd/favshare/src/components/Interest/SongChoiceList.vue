<template>
  <v-row justify="space-between" no-gutters>
    <v-col cols="6" align="center">
      <div id="non-choice" @click="select" class="option unselected-option">
        선택 안함
      </div>
    </v-col>
    <song-choice-list-item
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
  methods: {
    emitSelectSong(songId) {
      this.$emit("emitSelectSong", songId);
    },
    emitUnselectSong(songId) {
      this.$emit("emitUnselectSong", songId);
    },
    select() {
      const item = document.getElementById("non-choice");
      // 이미 선택되어 있을 때
      if (this.isSelected) {
        // 스타일 변경
        item.setAttribute("class", "option unselected-option");
        this.isSelected = false;
      } else {
        item.setAttribute("class", "option selected-option");
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

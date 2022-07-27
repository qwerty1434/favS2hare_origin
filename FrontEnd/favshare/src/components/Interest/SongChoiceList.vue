<template>
  <div>
    <song-choice-list-item
      v-for="songListItem in songList"
      :songId="songListItem.songId"
      :name="songListItem.name"
      :key="songListItem.id"
      @selectSong="emitSelectSong"
      @unselectSong="emitUnselectSong"
    ></song-choice-list-item>
  </div>
</template>

<script>
import SongChoiceListItem from "@/components/Interest/SongChoiceListItem.vue";

export default {
  name: "SongChoiceList",
  components: { SongChoiceListItem },
  props: ["songList"],
  methods: {
    emitSelectSong(songId) {
      this.$emit("emitSelectSong", songId);
    },
    emitUnselectSong(songId) {
      this.$emit("emitUnselectSong", songId);
    },
  },
};
</script>

<style></style>

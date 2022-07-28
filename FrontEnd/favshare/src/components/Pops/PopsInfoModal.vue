<template>
  <!-- eslint-disable -->
  <v-bottom-sheet
    class="chat-dialog"
    :value="dialogInfo"
    @input="dialogInfo = $event"
    hide-overlay
    transition="dialog-bottom-transition"
    scrollable
  >
    <v-card>
      <v-card-title>
        <v-row>
          <v-icon large>mdi-information-outline</v-icon>
          <v-col> 정보 </v-col>
          <v-spacer></v-spacer>
          <v-btn text @click.stop="dialogInfo = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-row>
      </v-card-title>
      <v-card-text>
        <h2>
          {{ sampleInfo.content }}
        </h2>
        {{ sampleInfo.name }}
        <h5>
          {{ sampleInfo.createDate }}
        </h5>
      </v-card-text>
      <v-card-text>
        <h3>좋아요 {{ sampleInfo.popsLike }}</h3>
        <h3>조회수 {{ sampleInfo.views }}</h3>
      </v-card-text>
      <v-divider class="mx-4"></v-divider>
    </v-card>
  </v-bottom-sheet>
</template>
<script>
export default {
  name: "PopsInfoModal",
  components: {},
  data() {
    return {
      sampleInfo: {
        id: 1,
        name: "사용자",
        content: "컨텐츠 명",
        createDate: "2022-05-19",
        popsLike: "1,900,000",
        views: "2,400,000",
      },
    };
  },
  props: {
    value: {
      type: Boolean,
      required: true,
    },
  },
  computed: {
    dialogInfo: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      },
    },
  },
};
</script>
<style>
.chat-dialog {
  position: absolute;
  width: 100%;
  height: calc(100% - 200px);
  margin-bottom: 0;
  padding-bottom: 0;
  bottom: 0;
}
</style>

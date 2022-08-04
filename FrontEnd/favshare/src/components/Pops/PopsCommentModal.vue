<template>
  <!-- eslint-disable -->
  <v-bottom-sheet
    class="chat-dialog"
    :value="dialogComment"
    @input="dialogComment = $event"
    hide-overlay
    transition="dialog-bottom-transition"
    scrollable
  >
    <v-card>
      <v-btn text @click.stop="dialogComment = false">
        <v-icon>mdi-drag-horizontal-variant</v-icon>
      </v-btn>
      <pops-comment-item :items="sampleComments"></pops-comment-item>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <v-text-field dense></v-text-field>
        </v-col>
        <v-col cols="2">
          <v-btn icon text small>게시</v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-bottom-sheet>
</template>
<script>
import PopsCommentItem from "./PopsCommentItem.vue";

export default {
  name: "PopsCommentModal",
  components: { PopsCommentItem },
  data() {
    return {
      sampleComments: [
        {
          id: 1,
          content: "첫 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 2,
          content: "두 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 3,
          content: "세 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 4,
          content: "네 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 5,
          content: "다섯 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 6,
          content: "여섯 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 7,
          content: "일곱 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 8,
          content: "여덟 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
        {
          id: 9,
          content: "아홉 번째 댓글입니다.",
          createDate: "2022-05-19",
        },
      ],
    };
  },
  props: {
    value: {
      type: Boolean,
      required: true,
    },
  },
  computed: {
    dialogComment: {
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

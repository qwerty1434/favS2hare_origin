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
      <div v-for="commentListItem in commentList" :key="commentListItem.id">
        <pops-comment-item :comment-list-item="commentListItem"></pops-comment-item>
      </div>
      <v-row>
        <v-col cols="2"
          ><v-avatar class="ml-4 mt-1" color="orange lighten-4" size="35">
            <img :src="userImgInPopsTab" alt="image" /> </v-avatar
        ></v-col>
        <v-col>
          <v-text-field class="text-font" v-model="comment" dense></v-text-field>
        </v-col>
        <v-col cols="2">
          <v-btn class="mt-2" icon text small @click="btnInsertComment">게시</v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-bottom-sheet>
</template>
<script>
/* eslint-disable */
import PopsCommentItem from "./PopsCommentItem.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "PopsCommentModal",
  components: { PopsCommentItem },
  data() {
    return {
      comment: "",
      // sampleComments: [
      //   {
      //     id: 1,
      //     content: "첫 번째 댓글입니다.",
      //     createDate: "2022-05-19",
      //     likeCount: 4,
      //   },
      //   {
      //     id: 2,
      //     content: "두 번째 댓글입니다.",
      //     createDate: "2022-05-19",
      //     likeCount: 4,
      //   },
      //   {
      //     id: 3,
      //     content: "세 번째 댓글입니다.",
      //     createDate: "2022-05-19",
      //     likeCount: 4,
      //   },
      // ],
    };
  },
  props: {
    value: {
      type: Boolean,
      required: true,
    },
  },
  created() {
    this.getComment({ popsIdInPopsTab: this.popsIdInPopsTab });
  },
  computed: {
    ...mapGetters(["userImgInPopsTab", "userIdInPopsTab", "popsIdInPopsTab", "commentList"]),
    dialogComment: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      },
    },
  },
  methods: {
    ...mapActions(["getComment", "insertComment"]),
    btnInsertComment() {
      if (this.comment === "") {
        alert("댓글을 입력하세요.");
      } else {
        this.insertComment({
          content: this.comment,
          popId: this.popsIdInPopsTab,
          userId: this.userIdInPopsTab,
        });
        this.comment = "";
      }
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
.text-font {
  font-size: 14px;
}
</style>

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
      <v-btn text @click.stop="closeDialogComment">
        <v-icon>mdi-drag-horizontal-variant</v-icon>
      </v-btn>
      <v-card-text class="comment-size">
        <div v-for="commentListItem in commentList" :key="commentListItem.id">
          <pops-comment-item
            :pops-id="popsId"
            :comment-list-item="commentListItem"
          ></pops-comment-item>
        </div>
      </v-card-text>
      <v-row class="input-back">
        <v-col cols="2"
          ><v-avatar class="ml-4 mt-2" color="orange lighten-4" size="40">
            <img :src="userImgInPopsTab" alt="image" /> </v-avatar
        ></v-col>
        <v-col>
          <v-text-field
            class="text-font"
            v-model="comment"
            dense
            @keydown.enter="btnInsertComment"
          ></v-text-field>
        </v-col>
        <v-col cols="2">
          <v-btn class="mt-3" icon text small @click="btnInsertComment">게시</v-btn>
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
    popsId: {
      type: Number,
    },
  },
  created() {
    this.getComment({ popId: this.popsId, userId: this.userId });
  },
  computed: {
    ...mapGetters(["isSignin", "userImgInPopsTab", "userId", "commentList"]),
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
    ...mapActions(["getComment", "insertComment", "closeDialogComment"]),
    btnInsertComment() {
      if (this.isSignin) {
        if (this.comment === "") {
          alert("댓글을 입력하세요.");
        } else {
          this.insertComment({
            content: this.comment,
            popId: this.popsId,
            userId: this.userId,
          });
          this.comment = "";
        }
      } else {
        alert("로그인이 필요합니다.");
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
.comment-size {
  height: 400px;
  padding: 0 0 0 !important;
  color: black !important;
}
</style>

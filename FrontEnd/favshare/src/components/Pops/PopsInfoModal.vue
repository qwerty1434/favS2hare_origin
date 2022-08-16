<template>
  <!-- eslint-disable -->
  <v-bottom-sheet
    class="chat-dialog-info"
    :value="dialogInfo"
    @input="dialogInfo = $event"
    hide-overlay
    transition="dialog-bottom-transition"
    scrollable
  >
    <v-card>
      <v-card-title>
        <v-row align="center">
          <v-icon class="ml-2">mdi-information-outline</v-icon>
          <v-col class="ml-n2"><h5>정보</h5></v-col>
          <v-spacer></v-spacer>
          <v-col cols="2">
            <v-btn text @click.stop="closeDialogInfo">
              <v-icon class="mr-2">mdi-close</v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text class="mt-2">
        <h2>
          {{ popsInfo.name }}
        </h2>
        <h5 class="mt-2">
          {{ popsInfo.id }}
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ createDate }}
        </h5>
        <v-row class="mt-3">
          <v-col align="center">
            <h5>좋아요 수</h5>
            <h3>{{ popsInfo.likeCount }}</h3>
          </v-col>
          <v-col align="center">
            <h5>조회수</h5>
            <h3>{{ popsInfo.views }}</h3>
          </v-col>
        </v-row>
      </v-card-text>
      <v-divider class="mx-4"></v-divider>
      <v-card-text>
        <h3 class="mt-4">
          {{ popsInfo.content }}
        </h3>
        <br />
        <br />
        <br />
        <br />
      </v-card-text>
      <v-card-text class="red--text">#나연&nbsp;&nbsp;&nbsp;#트와이스</v-card-text>
      <v-divider class="mx-4"></v-divider>
      <v-card-text class="mt-3">
        <router-link
          :to="{
            name: 'originallinkedpops',
            query: {
              popsId: this.popsInfo.id,
            },
          }"
        >
          <h5>원본영상</h5>
        </router-link>
      </v-card-text>
    </v-card>
  </v-bottom-sheet>
</template>
<script>
/* eslint-disable */
import { mapGetters, mapActions } from "vuex";
import dayjs from "dayjs";

export default {
  name: "PopsInfoModal",
  component: {
    dayjs,
  },
  data() {
    return {
      createDate: "",
      // sampleInfo: {
      //   id: 1,
      //   name: "사용자",
      //   content: "컨텐츠 명",
      //   createDate: "2022-05-19",
      //   popsLike: "1,900,000",
      //   views: "2,400,000",
      // },
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
    userId: {
      type: Number,
    },
  },
  created() {
    console.log("popsInfo " + this.popsId);
    this.getPopsInfo({
      popId: this.popsId,
      userId: this.userId,
    });
    this.createDate = dayjs(this.popsInfo.createDate).format("YYYY-MM-DD HH:mm");
  },
  computed: {
    ...mapGetters(["popsInfo"]),
    dialogInfo: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      },
    },
  },
  methods: {
    ...mapActions(["getPopsInfo", "closeDialogInfo"]),
  },
};
</script>
<style>
.chat-dialog-info {
  position: absolute;
  width: 100%;
  height: calc(100% - 200px);
  margin-bottom: 0;
  padding-bottom: 0;
  bottom: 0;
}
</style>

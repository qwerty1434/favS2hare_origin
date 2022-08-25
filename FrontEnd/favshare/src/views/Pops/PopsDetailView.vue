<template>
  <!-- eslint-disable -->
  <v-container class="pt-3">
    <v-card color="black" height="720px">
      <div class="mt-3">
        <v-row class="pl-2 pr-2" justify="end">
          <v-btn class="ma-2" text icon color="white">
            <v-icon @click="goBack">mdi-arrow-left</v-icon>
          </v-btn>
          <v-spacer></v-spacer>
          <v-menu transition="slide-y-transition" bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                v-if="popsInfo.userId == this.userId"
                class="ma-2"
                text
                icon
                color="white"
                v-bind="attrs"
                v-on="on"
              >
                <v-icon>mdi-dots-horizontal</v-icon>
              </v-btn>
            </template>
            <v-list dense>
              <v-list-item @click="btnDeletePops">
                <v-list-item-title>pops 삭제</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-row>
        <div class="pops-detail-body">
          <div class="pops-title">
            <h2>{{ popsInfo.name }}</h2>
          </div>
          <v-sheet class="pops-play">
            <div>
              <youtube
                :video-id="youtubePk"
                :player-vars="playerVars"
                :ref="'pops' + this.popsInfo.id"
                @ready="onPlayerReady"
                @playing="onPlaying"
                :width="336"
                :height="189"
              ></youtube>
            </div>
          </v-sheet>
          <div class="pops-editer">
            <h3>{{ popsInfo.content }}</h3>
          </div>
        </div>
        <v-row class="mt-550">
          <v-spacer></v-spacer>
          <div v-if="isSignin">
            <h6 class="like-count">{{ popsInfo.likeCount }}</h6>
          </div>
        </v-row>
        <v-row class="pa-2 mt-600">
          <v-spacer></v-spacer>
          <div v-if="isSignin">
            <v-btn
              class="mt-2"
              text
              icon
              color="white"
              v-if="!isLiked"
              @click="btnLikePops"
            >
              <v-icon>mdi-heart-outline</v-icon>
            </v-btn>
            <v-btn
              class="mt-2"
              text
              icon
              color="red"
              v-else
              @click="btnLikePops"
            >
              <v-icon>mdi-heart</v-icon>
            </v-btn>
          </div>
          <v-btn
            class="mt-2"
            text
            icon
            color="white"
            @click.stop="dialogComment = true"
          >
            <v-icon>mdi-comment-text-outline</v-icon>
          </v-btn>
          <pops-comment-modal
            :pops-id="popsId"
            :editor-id="editorId"
            :value="dialogComment"
            @input="dialogComment = $event"
          ></pops-comment-modal>
          <v-btn
            class="mt-2"
            text
            icon
            color="white"
            @click.stop="dialogInfo = true"
          >
            <v-icon>mdi-information-outline</v-icon>
          </v-btn>
          <pops-info-modal
            :pops-id="popsId"
            :editor-id="editorId"
            :value="dialogInfo"
            @input="dialogInfo = $event"
          ></pops-info-modal>
        </v-row>
      </div>
    </v-card>
    <bottom-navigation-bar></bottom-navigation-bar>
  </v-container>
</template>

<script>
/* eslint-disable */
import PopsCommentModal from "@/components/Pops/PopsCommentModal.vue";
import PopsInfoModal from "@/components/Pops/PopsInfoModal.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import { mapActions, mapGetters } from "vuex";
import VueYoutube from "vue-youtube";
import Vue from "vue";

Vue.use(VueYoutube);

export default {
  name: "PopsDetailView",
  components: { PopsCommentModal, PopsInfoModal, BottomNavigationBar },
  data() {
    return {
      isLiked: false,
      dialogComment: false,
      dialogInfo: false,
    };
  },
  props: {
    popsId: {
      type: Number,
    },
    editorId: {
      type: String,
    },
  },
  computed: {
    ...mapGetters(["isSignin", "userId", "popsInfo"]),
    player() {
      return this.$refs[`pops${this.popsInfo.id}`].player;
    },
    youtubePk() {
      return this.popsInfo.url;
    },
    playerVars() {
      return {
        autoplay: 1,
        mute: 1,
        controls: 0,
        disablekb: 1,
      };
    },
    section() {
      return {
        start: this.popsInfo.startSecond,
        end: this.popsInfo.endSecond,
      };
    },
  },
  created() {
    this.countView({ popId: this.popsId });
    this.getPopsInfo({ popId: this.popsId, userId: this.userId });
    if (this.popsInfo.liked) {
      this.isLiked = true;
    } else {
      this.isLiked = false;
    }
  },
  methods: {
    ...mapActions([
      "getPopsInfo",
      "likePops",
      "unLikePops",
      "deletePopsOne",
      "countView",
    ]),
    onPlayerReady() {
      this.player.seekTo(this.section.start);
      this.player.playVideo();
    },
    onPlaying() {
      const duration = this.section.end - this.section.start;
      setTimeout(this.restartVideoSection, duration * 1000);
    },
    restartVideoSection() {
      this.player.seekTo(this.section.start);
    },
    btnLikePops() {
      if (!this.isLiked) {
        this.likePops({
          popId: this.popsId,
          userId: this.userId,
        });
        this.isLiked = true;
      } else {
        this.unLikePops({
          popId: this.popsId,
          userId: this.userId,
        });
        this.isLiked = false;
      }
    },
    btnDeletePops() {
      if (this.popsInfo.userId == this.userId) {
        if (confirm("해당 pop을 삭제하시겠습니까?")) {
          this.deletePopsOne({ popId: this.popsId });
          alert("삭제되었습니다.");
          this.$router.push({ name: "feed" });
        }
      } else {
        alert("본인이 편집한 pop만 삭제할 수 있습니다.");
      }
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.mt-550 {
  margin-top: 195px;
}
.mt-600 {
  margin-top: 1px !important;
  margin-right: auto;
}
.pops-detail-body {
  margin-top: 160px;
}
.pops-play {
  height: 189px;
  width: 336px;
}
.pops-title {
  margin: 10px;
  color: aliceblue;
  padding-left: 130px;
}
.pops-editer {
  margin: 10px;
  color: aliceblue;
}
.like-count {
  margin-right: 106px;
  margin-bottom: -14px;
  color: aliceblue;
}
</style>

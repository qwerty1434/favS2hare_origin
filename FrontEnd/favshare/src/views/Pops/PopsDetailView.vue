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
              <v-btn class="ma-2" text icon color="white" v-bind="attrs" v-on="on">
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
          <div class="pops-title"><h2>pops제목</h2></div>
          <!-- pops 재생화면 -->
          <v-sheet class="pops-play">pops 재생</v-sheet>
          <div class="pops-editer"><h2>user</h2></div>
        </div>
        <v-row class="pa-2 mt-600" justify="end">
          <v-spacer></v-spacer>
          <v-btn class="mt-2" text icon color="white" v-if="!isLiked" @click="btnLikePops">
            <v-icon>mdi-heart-outline</v-icon>
          </v-btn>
          <v-btn class="mt-2" text icon color="red" v-else @click="btnLikePops">
            <v-icon>mdi-heart</v-icon>
          </v-btn>
          <v-btn class="mt-2" text icon color="white" @click.stop="dialogComment = true">
            <v-icon>mdi-comment-text-outline</v-icon>
          </v-btn>
          <pops-comment-modal
            :value="dialogComment"
            @input="dialogComment = $event"
          ></pops-comment-modal>
          <v-btn class="mt-2" text icon color="white" @click.stop="dialogInfo = true">
            <v-icon>mdi-information-outline</v-icon>
          </v-btn>
          <pops-info-modal :value="dialogInfo" @input="dialogInfo = $event"></pops-info-modal>
        </v-row>
      </div>
    </v-card>
    <bottom-navigation-bar></bottom-navigation-bar>
  </v-container>
</template>

<script>
import PopsCommentModal from "@/components/Pops/PopsCommentModal.vue";
import PopsInfoModal from "@/components/Pops/PopsInfoModal.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import { mapActions, mapGetters } from "vuex";

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
  computed: {
    ...mapGetters(["userIdInPopsTab", "popsIdInPopsTab"]),
  },
  created() {},
  methods: {
    ...mapActions(["likePops", "unLikePops"]),
    btnLikePops() {
      if (!this.isLiked) {
        this.likePops({
          popId: this.popsIdInPopsTab,
          userId: this.userIdInPopsTab,
        });
        this.isLiked = true;
      } else {
        this.unLikePops({
          popId: this.popsIdInPopsTab,
          userId: this.userIdInPopsTab,
        });
        this.isLiked = false;
      }
    },
    btnDeletePops() {},
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.mt-600 {
  margin-top: 185px !important;
  margin-right: auto;
}
.pops-detail-body {
  margin-top: 160px;
}
.pops-play {
  margin-left: 8px;
  height: 180px;
  width: 320px;
}
.pops-title {
  margin: 10px;
  color: aliceblue;
}
.pops-editer {
  margin: 10px;
  color: aliceblue;
}
</style>

<template>
  <!-- eslint-disable -->
  <v-container class="pt-3">
    <v-card height="720px">
      <v-img height="100%" src="@/assets/sample/popssample.jpg">
        <v-row class="pa-2" justify="end">
          <v-btn class="ma-2" text icon color="white">
            <v-icon>mdi-arrow-left</v-icon>
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn class="ma-2" text icon color="white">
            <v-icon>mdi-dots-horizontal</v-icon>
          </v-btn>
        </v-row>
        <v-row class="pa-2 mt-600" justify="end">
          <v-btn class="mt-2" text icon color="white" v-if="!isLiked" @click="likePops">
            <v-icon>mdi-heart-outline</v-icon>
          </v-btn>
          <v-btn class="mt-2" text icon color="red" v-else @click="likePops">
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
      </v-img>
    </v-card>
    <bottom-navigation-bar></bottom-navigation-bar>
  </v-container>
</template>

<script>
import PopsCommentModal from "@/components/Pops/PopsCommentModal.vue";
import PopsInfoModal from "@/components/Pops/PopsInfoModal.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

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
  methods: {
    likePops() {
      if (!this.isLiked) {
        this.isLiked = true;
      } else {
        this.isLiked = false;
      }
    },
  },
};
</script>

<style>
.mt-600 {
  margin-top: 615px !important;
  margin-right: auto;
}
</style>

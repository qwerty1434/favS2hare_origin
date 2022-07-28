<template>
  <!-- eslint-disable -->
  <v-container class="pt-3">
    <v-card height="710px">
      <v-img height="100%" src="@/assets/sample/popssample.jpg">
        <v-row class="pa-2" justify="end">
          <v-btn class="ma-2" text icon color="white">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-row>
        <v-row class="pa-2 mt-600" justify="end">
          <v-btn class="ma-2" text icon color="white">
            <v-icon large>mdi-heart-outline</v-icon>
          </v-btn>
          <v-btn class="ma-2" text icon color="white" @click.stop="dialogComment = true">
            <v-icon large>mdi-comment-text-outline</v-icon>
          </v-btn>
          <pops-comment-modal
            :value="dialogComment"
            @input="dialogComment = $event"
          ></pops-comment-modal>
          <v-btn class="ma-2" text icon color="white" @click.stop="dialogInfo = true">
            <v-icon large>mdi-information-outline</v-icon>
          </v-btn>
          <pops-info-modal :value="dialogInfo" @input="dialogInfo = $event"></pops-info-modal>
        </v-row>
      </v-img>
    </v-card>
  </v-container>
</template>

<script>
import PopsCommentModal from "@/components/Pops/PopsCommentModal.vue";
import PopsInfoModal from "@/components/Pops/PopsInfoModal.vue";

export default {
  name: "PopsDetailView",
  components: { PopsCommentModal, PopsInfoModal },
  data() {
    return {
      dialogComment: false,
      dialogInfo: false,
    };
  },
  methods: {},
};
</script>

<style>
.mt-600 {
  margin-top: 600px !important;
  margin-right: auto;
}
</style>

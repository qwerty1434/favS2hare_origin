<template>
  <!-- eslint-disable -->
  <v-container>
    <router-link to="/popsdetail">
      <v-card class="mt-3" height="555px">
        <v-img height="100%" src="@/assets/sample/popssample.jpg">
          <v-row class="pa-2" justify="end">
            <v-btn class="ma-2" text icon color="white">
              <v-icon>mdi-dots-horizontal</v-icon>
            </v-btn>
          </v-row>
          <v-row class="pa-2 mt-300" justify="end">
            <!-- userId 영상제목 -->
            <v-btn
              class="mt-2"
              text
              icon
              color="white"
              v-if="!isLiked"
              @click="likePops"
            >
              <v-icon>mdi-heart-outline</v-icon>
            </v-btn>
            <v-btn
              class="mt-2"
              text
              icon
              color="white"
              v-else
              @click="likePops"
            >
              <v-icon>mdi-heart</v-icon>
            </v-btn>
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
              :value="dialogInfo"
              @input="dialogInfo = $event"
            ></pops-info-modal>
          </v-row>
        </v-img>
      </v-card>
    </router-link>
  </v-container>
</template>

<script>
import PopsInfoModal from "@/components/Pops/PopsInfoModal.vue";

export default {
  name: "PopsListItem",
  components: { PopsInfoModal },
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
.mt-300 {
  margin-top: 455px !important;
  margin-right: 1px;
}
</style>

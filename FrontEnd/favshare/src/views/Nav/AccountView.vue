<template>
  <v-container>
    <v-row class="pb-2">
      <v-col cols="2">
        <v-icon>mdi-arrow-left</v-icon>
      </v-col>
      <v-col>계정 정보</v-col>
    </v-row>
    <v-divider></v-divider>
    <div>
      <component :is="currentComponent" @input-change="confirmPw"></component>
    </div>
  </v-container>
</template>
<script>
import PwConfirm from "@/components/common/PwConfirm.vue";
import AccountEdit from "@/components/common/AccountEdit.vue";

export default {
  name: "AccountView",
  components: {
    "pw-confirm": PwConfirm,
    "account-edit": AccountEdit,
  },
  data() {
    return {
      currentComponent: "pw-confirm",
      isConfirmed: false,
    };
  },
  methods: {
    confirmPw() {
      this.isConfirmed = true;
      //유저정보가져오기
      this.currentComponent = "account-edit";
    },
  },
};
</script>
<style></style>

<template>
  <v-container>
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

<template>
  <v-container class="pt-3">
    <router-link to="/popsdetail">
      <v-card>
        <v-img src="@/assets/sample/popssample.jpg" />
        <v-btn class="ma-2" text icon color="red lighten-2">
          <v-icon large>mdi-heart-outline</v-icon>
        </v-btn>
      </v-card>
    </router-link>
  </v-container>
</template>

<script>
export default {
  name: "PopsDetailView",
};
</script>

<style></style>

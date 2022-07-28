<template>
  <v-bottom-navigation :value="value" color="indigo" grow absolute>
    <v-btn>
      <span>Home</span>

      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn>
      <span>Pops</span>

      <v-icon>mdi-party-popper</v-icon>
    </v-btn>

    <v-btn>
      <span>Make</span>

      <v-icon>mdi-ticket-confirmation-outline</v-icon>
    </v-btn>

    <v-btn>
      <span>Profile</span>

      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: "BottomNavigationBar",
  data() {
    return {
      value: 1,
    };
  },
};
</script>

<style></style>

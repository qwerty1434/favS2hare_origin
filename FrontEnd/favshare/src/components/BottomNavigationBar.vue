<template>
  <v-bottom-navigation :value="value" color="indigo" grow absolute>
    <router-link :to="{ name: 'home' }" class="button">
      <v-btn>
        <span>Home</span>

        <v-icon>mdi-home</v-icon>
      </v-btn>
    </router-link>

    <router-link :to="{ name: 'pops' }" class="button">
      <v-btn>
        <span>Pops</span>

        <v-icon>mdi-party-popper</v-icon>
      </v-btn>
    </router-link>

    <router-link :to="{ name: 'search' }" class="button">
      <v-btn>
        <span>Search</span>

        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </router-link>

    <router-link :to="{ name: 'feed' }" class="button">
      <v-btn>
        <span>Profile</span>

        <v-icon>mdi-account</v-icon>
      </v-btn>
    </router-link>
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

<style>
.button {
  padding-top: 8px;
}
</style>

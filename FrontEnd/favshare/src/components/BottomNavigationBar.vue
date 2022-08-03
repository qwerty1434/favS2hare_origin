<template>
  <v-bottom-navigation grow absolute class="b-nav">
    <router-link
      :to="{ name: 'home' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn>
        <span>Home</span>

        <v-icon>mdi-home</v-icon>
      </v-btn>
    </router-link>

    <router-link
      :to="{ name: 'pops' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn>
        <span>Pops</span>

        <v-icon>mdi-party-popper</v-icon>
      </v-btn>
    </router-link>

    <router-link
      :to="{ name: 'search' }"
      class="button"
      exact-active-class="bnav-active"
    >
      <v-btn>
        <span>Search</span>

        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </router-link>

    <router-link
      :to="{ name: 'feed' }"
      class="button"
      exact-active-class="bnav-active"
    >
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
      value: 5,
    };
  },
};
</script>

<style>
.button {
  padding-top: 8px;
  color: #ff5d5d;
}

.b-nav {
  color: #ff5d5d;
}

.bnav-active {
  color: #ff5d5d;
}
</style>

<template>
  <div>
    <v-app-bar color="indigo" dense dark>
      <router-link :to="{ name: 'home' }">
        <v-toolbar-title>FavS2hare</v-toolbar-title>
      </router-link>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-alarm</v-icon>
      </v-btn>

      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="n in 5" :key="n" @click="() => {}">
            <v-list-item-title>Option {{ n }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "NavBar",
};
</script>

<style>
.class {
  margin: 0;
}

a {
  text-decoration: none;
  color: black;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 8px 12px;
}

.navbar__logo {
  font-size: 24px;
  color: black;
}

.navbar__icons {
  padding: 8px 10px;
}
</style>

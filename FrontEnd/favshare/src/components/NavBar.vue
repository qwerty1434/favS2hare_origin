<template>
  <div>
    <v-app-bar dense>
      <router-link :to="{ name: 'home' }" active-class="navbar-active">
        <v-toolbar-title color="black" class="nav-font"
          >Fav<span style="color: red">S2</span>hare</v-toolbar-title
        >
      </router-link>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-alarm</v-icon>
      </v-btn>

      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-format-list-bulleted</v-icon>
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

.navbar-active {
  color: black !important;
}

.nav-font {
  font-family: "Bingrae", sans-serif !important;
  text-align: center;
  font-size: 20px;
}
@font-face {
  font-family: "Bingrae";
  src: url("@/assets/fonts/Binggrae-Bold.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}
</style>

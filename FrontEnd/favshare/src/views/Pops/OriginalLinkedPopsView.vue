<template>
  <div>
    <v-app-bar color="white" dense flat>
      <router-link to="/popsdetail">
        <v-icon>mdi-keyboard-backspace</v-icon>
      </router-link>
    </v-app-bar>
    <original-info :youtubeUrl="linkedPopsList[0].youtubeUrl"></original-info>
    <v-divider></v-divider>
    <linked-pops-list
      :linkedPopsList="linkedPopsList"
      :countLinkedPopsList="countLinkedPopsList"
    ></linked-pops-list>
    <bottom-navigation-bar></bottom-navigation-bar>
  </div>
</template>

<script>
import axios from "axios";

import OriginalInfo from "@/components/Pops/OriginalInfo.vue";
import LinkedPopsList from "@/components/Pops/LinkedPopsList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";
import api from "@/api/springRestAPI";
import { mapGetters } from "vuex";

export default {
  name: "OriginalLinkedPopsView",
  components: { OriginalInfo, LinkedPopsList, BottomNavigationBar },
  data() {
    return {
      linkedPopsList: [],
      countLinkedPopsList: 0,
    };
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  created() {
    this.getOriginalAndPops();
  },
  methods: {
    getOriginalAndPops() {
      axios
        .post(
          // `http://13.124.112.241:8080/pop/youtube/${this.$route.query.popsId}`
          api.pop.youtube(),
          {
            userId: this.userId,
            popId: this.$route.query.popsId,
          }
        )
        .then((response) => {
          this.linkedPopsList = response.data.popInfo;
          this.countLinkedPopsList = response.data.countPopList;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>

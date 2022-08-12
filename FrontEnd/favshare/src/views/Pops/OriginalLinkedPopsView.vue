<template>
  <div>
    <v-app-bar color="white" dense flat>
      <router-link to="/popsdetail">
        <v-icon>mdi-keyboard-backspace</v-icon>
      </router-link>
    </v-app-bar>
    <original-info :youtube="youtube"></original-info>
    <v-divider></v-divider>
    <linked-pops-list :linkedPopsList="linkedPopsList"></linked-pops-list>
    <bottom-navigation-bar></bottom-navigation-bar>
  </div>
</template>

<script>
import OriginalInfo from "@/components/Pops/OriginalInfo.vue";
import LinkedPopsList from "@/components/Pops/LinkedPopsList.vue";
import BottomNavigationBar from "@/components/BottomNavigationBar.vue";

export default {
  name: "OriginalLinkedPopsView",
  components: { OriginalInfo, LinkedPopsList, BottomNavigationBar },
  data() {
    return {
      youtube: {},
      linkedPopsList: [],
    };
  },
  created() {
    this.getDummyOriginalAndPops();
  },
  methods: {
    getOriginalAndPops() {
      console.log("요청 보내자");
    },
    getDummyOriginalAndPops() {
      (this.youtube = {
        youtubeId: 1,
        youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
        youtubeContent: "?",
      }),
        (this.linkedPopsList = [
          {
            popsId: 1,
            youtubeId: 1,
            youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
            startSecond: 10,
            endSecond: 15,
            view: 30,
            count: 2,
          },
          {
            popsId: 2,
            youtubeId: 1,
            youtubeUrl: "https://www.youtube.com/watch?v=f6YDKF0LVWw",
            startSecond: 100,
            endSecond: 110,
            view: 45,
            count: 17,
          },
        ]);
    },
  },
};
</script>

<style></style>

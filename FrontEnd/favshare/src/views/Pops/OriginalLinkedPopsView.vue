<template>
  <div>
    <original-info :youtube="youtube" :popsList="popsList"></original-info>
    <linked-pops-list :popsList="popsList"></linked-pops-list>
  </div>
</template>

<script>
import OriginalInfo from "@/components/Pops/OriginalInfo.vue";
import LinkedPopsList from "@/components/Pops/LinkedPopsList.vue";

export default {
  name: "OriginalLinkedPopsView",
  components: { OriginalInfo, LinkedPopsList },
  data() {
    return {
      youtube: {},
      popsList: [],
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
        (this.popsList = [
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

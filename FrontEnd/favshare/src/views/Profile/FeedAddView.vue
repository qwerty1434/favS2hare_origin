<template>
  <div>
    <v-app-bar color="white" dense flat>
      <router-link to="/feed">
        <v-icon>mdi-keyboard-backspace</v-icon>
      </router-link>
    </v-app-bar>
    <my-pops-list ref="myPopsList" :myPopsList="myPopsList"></my-pops-list>
    <!-- 영상 추가 버튼 -->
    <v-footer fixed padless>
      <v-btn
        @click.prevent="addPopsInFeed"
        color="#FF5D5D"
        height="50"
        dark
        block
        tile
        >영상 추가</v-btn
      >
    </v-footer>
  </div>
</template>

<script>
import MyPopsList from "@/components/Profile/MyPopsList.vue";

export default {
  name: "FeedAddView",
  components: { MyPopsList },
  data() {
    return {
      myPopsList: [],
    };
  },
  created() {
    this.getDummymyPopsList();
  },
  methods: {
    getDummymyPopsList() {
      this.myPopsList = [
        {
          popsId: 1,
          youtubeId: 1,
          youtubeUrl: "https://www.youtube.com/watch?v=gFb1TftvdoM",
          startSecond: 120,
          endSecond: 128,
          view: 42,
          count: 3,
        },
        {
          popsId: 2,
          youtubeId: 2,
          youtubeUrl: "https://www.youtube.com/watch?v=9kaCAbIXuyg",
          startSecond: 39,
          endSecond: 45,
          view: 135976046,
          count: 1770000,
        },
        {
          popsId: 3,
          youtubeId: 1,
          youtubeUrl: "https://www.youtube.com/watch?v=gFb1TftvdoM",
          startSecond: 279,
          endSecond: 308,
          view: 202,
          count: 88,
        },
        {
          popsId: 4,
          youtubeId: 3,
          youtubeUrl: "https://www.youtube.com/watch?v=Jh4QFaPmdss",
          startSecond: 78,
          endSecond: 88,
          view: 25,
          count: 1,
        },
      ];
    },
    addPopsInFeed() {
      // MyPopsListItem에서 isSelected가 true인 것만 배열에 담기
      let selectedList = this.$refs.myPopsList.$refs.myPopsListItem.filter(
        (myPopsListItem) => {
          return myPopsListItem.isSelected;
        }
      );
      // 각 MyPopsListItem 객체를 담고 있는 배열을 popsId만 뽑아 재할당
      selectedList = selectedList.map((selectedListItem) => {
        return selectedListItem.popsId;
      });
      // 선택된 Pops들 요청 보내기
      console.log(selectedList);
    },
  },
};
</script>

<style></style>

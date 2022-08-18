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
import axios from "axios";
import { mapGetters } from "vuex";
import api from "@/api/springRestAPI";

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
    this.getMyPopsList();
  },
  computed: {
    ...mapGetters(["userId", "currentFeedId"]),
  },
  methods: {
    getMyPopsList() {
      axios
        .post(
          // "http://13.124.112.241:8080/user/profile/popList"
          api.userProfile.profilePopList(),
          {
            // 전체 피드 조회는 feedId에 0 값
            feedId: 0,
            userId: this.userId,
          }
        )
        .then((response) => {
          this.myPopsList = response.data;
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addPopsInFeed() {
      // MyPopsListItem에서 isSelected가 true인 것만 배열에 담기
      let selectedList = this.$refs.myPopsList.$refs.myPopsListItem.filter(
        (myPopsListItem) => {
          return myPopsListItem.isSelected;
        }
      );
      // 각 MyPopsListItem 객체를 담고 있는 배열을 id만 뽑아 재할당
      selectedList = selectedList.map((selectedListItem) => {
        return selectedListItem.id;
      });
      // 선택된 Pops들 요청 보내기
      axios
        .post(
          // "http://13.124.112.241:8080/feed/pop"
          api.feed.pop(),
          {
            feedId: this.currentFeedId,
            popId: selectedList,
          }
        )
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>

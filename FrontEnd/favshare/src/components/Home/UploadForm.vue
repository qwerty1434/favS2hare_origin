<template>
  <v-container>
    <v-form class="form">
      <v-row no-gutters>
        <v-col cols="12">
          <v-text-field
            v-model="title"
            label="제목"
            placeholder="제목을 입력해주세요"
            height="55"
            outlined
          ></v-text-field>
        </v-col>
        <v-col>
          <v-textarea
            v-model="description"
            label="설명"
            placeholder="설명을 입력해주세요"
            outlined
          ></v-textarea>
        </v-col>
      </v-row>
    </v-form>
    <v-row no-gutters>
      <v-col cols="12">
        <p>피드 선택</p>
      </v-col>
    </v-row>
    <div class="scroll-horizontal" no-gutters>
      <div v-for="feedListItem in feedList" :key="feedListItem.id" align="center">
        <div
          class="unselected feed"
          :id="feedListItem.id"
          @click="selectFeed"
          :style="`background-image: url(${feedListItem.feedImageUrl})`"
        >
          {{ feedListItem.name }}
        </div>
      </div>
      <div align="center">
        <div class="unselected feed" id="0" @click="selectFeed">전체</div>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "UploadForm",
  props: ["feedList"],
  data() {
    return {
      title: "",
      description: "",
      selectedFeedId: 0,
    };
  },
  watch: {
    selectedFeedId(newVal, oldVal) {
      const selectedFeed = document.getElementById(newVal);
      selectedFeed.setAttribute("class", "selected feed");

      if (oldVal !== 0) {
        const unselectedFeed = document.getElementById(oldVal);
        unselectedFeed.setAttribute("class", "unselected feed");
      }
    },
  },
  methods: {
    selectFeed(event) {
      this.selectedFeedId = event.target.getAttribute("id");
    },
  },
};
</script>

<style scoped>
.form {
  margin-bottom: 20px;
}

.scroll-horizontal {
  overflow-x: auto;
  overflow-y: hidden;
  white-space: nowrap;
  display: flex;
  height: 100px;
}

.feed {
  height: 70px;
  width: 70px;
  line-height: 70px;
  background-color: #efeff0;
  border: 2px solid;
  border-radius: 50%;
  margin-right: 15px;
  padding-top: 45px;
  font-size: 12px;
}

.unselected {
  border-color: #afb1b6;
}

.selected {
  border-color: #ff5d5d;
}
</style>

<template>
  <div>
    <form>
      <label for="title">제목</label><br />
      <input
        type="text"
        id="title"
        name="title"
        placeholder="Message"
        v-model="title"
      /><br />
      <label for="description">설명</label><br />
      <input
        type="text"
        id="description"
        name="description"
        placeholder="Message"
        v-model="description"
      />
      <p>피드 선택</p>
      <div
        class="unselected-feed"
        v-for="feedListItem in feedList"
        :id="feedListItem.feedId"
        :key="feedListItem.feedId"
        @click="selectFeed"
      >
        {{ feedListItem.name }}
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "UploadForm",
  props: ["feedList"],
  data() {
    return {
      title: "",
      description: "",
      feedId: 0,
    };
  },
  watch: {
    feedId(newVal, oldVal) {
      const selectedFeed = document.getElementById(newVal);
      selectedFeed.setAttribute("class", "selected-feed");

      if (oldVal !== 0) {
        const unselectedFeed = document.getElementById(oldVal);
        unselectedFeed.setAttribute("class", "unselected-feed");
      }
    },
  },
  methods: {
    selectFeed(event) {
      this.feedId = event.target.getAttribute("id");
    },
  },
};
</script>

<style scoped>
.unselected-feed {
  height: 64px;
  width: 64px;
  border: 2px solid #afb1b6;
  border-radius: 50%;
  background-color: #efeff0;
}

.selected-feed {
  height: 64px;
  width: 64px;
  border: 2px solid #ff5d5d;
  border-radius: 50%;
  background-color: #efeff0;
}
</style>

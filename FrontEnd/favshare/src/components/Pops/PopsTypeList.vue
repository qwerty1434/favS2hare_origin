<template>
  <div class="pops-type mt-2">
    <div v-for="popsTypeItem in popsTypeList" :key="popsTypeItem.id">
      <pops-type-list-item
        @click-type="searchPopsType"
        :pops-type-item="popsTypeItem"
      ></pops-type-list-item>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import PopsTypeListItem from "./PopsTypeListItem.vue";
import axios from "axios";

export default {
  name: "PopsTypeList",
  components: { PopsTypeListItem },
  data() {
    return {
      popsTypeList: Array,
    };
  },
  computed: {
    ...mapGetters(["userId"]),
  },
  methods: {
    searchPopsType: function (type) {
      this.$emit("click-type", type);
    },
    fetchInterestIdol() {
      axios({
        method: "get",
        url: `http://localhost:8080/pop/idolList/${this.userId}`,
      }).then((res) => {
        this.popsTypeList = res.data;
      });
    },
  },
  created() {
    this.fetchInterestIdol();
  },
};
</script>

<style></style>

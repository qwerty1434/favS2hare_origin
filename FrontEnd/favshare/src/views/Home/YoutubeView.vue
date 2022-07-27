<template>
  <div>
    <nav-bar class="top"></nav-bar>
    <youtube-video></youtube-video>
  </div>
</template>

<script>
import YoutubeVideo from "../../components/Home/YoutubeVideo.vue";
import NavBar from "../../components/NavBar.vue";

export default {
  components: { NavBar, YoutubeVideo },
  name: "YoutubeView",
};
</script>

<style>
.top {
  position: fixed;
  width: 100%;
}
</style>

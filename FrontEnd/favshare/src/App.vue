<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/youtube">Youtube</router-link> |
      <router-link to="/youtubeedit">YoutubeEdit</router-link> |
      <router-link to="/search">Search</router-link> |
      <router-link to="/searchmorepops">SearchMorePops</router-link> |
      <router-link to="/searchmoreyoutube">SearchMoreYoutube</router-link> |
      <router-link to="/searchmorefollowing">SearchMoreFollowing</router-link> |
      <router-link to="/signin">Signin</router-link> |
      <router-link to="/signup">Signup</router-link> |
      <router-link to="/findpw">FindPw</router-link> |
      <router-link to="/interest">Interest</router-link> |
      <router-link to="/make">Make</router-link> |
      <router-link to="/pops">Pops</router-link> |
      <router-link to="/popsdetail">PopsDetail</router-link> |
      <router-link to="/originallinkedpops">OriginalLinkedPops</router-link> |
      <router-link to="/feed">Feed</router-link> |
      <router-link to="/follow">Follow</router-link> |
      <router-link to="/profileedit">ProfileEdit</router-link> |
      <router-link to="/feededit">FeedEdit</router-link> |
      <router-link to="/feedadd">FeedAdd</router-link> |
      <router-link to="/popsinfeed">PopsInFeed</router-link> |
    </nav>
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

import Vue from "vue";
import VueRouter from "vue-router";

// Home
import HomeView from "../views/Home/HomeView.vue";
import YoutubeView from "../views/Home/YoutubeView.vue";
import YoutubeEditView from "../views/Home/YoutubeEditView.vue";
import SearchView from "../views/Home/SearchView.vue";
import SearchMorePopsView from "../views/Home/SearchMorePopsView.vue";
import SearchMoreYoutubeView from "../views/Home/SearchMoreYoutubeView.vue";
import SearchMoreFollowingView from "../views/Home/SearchMoreFollowingView.vue";

// Account
import SigninView from "../views/Account/SigninView.vue";
import SignupView from "../views/Account/SignupView.vue";
import FindPwView from "../views/Account/FindPwView.vue";

// Interest
import InterestView from "../views/Interest/InterestView.vue";

// Make
import MakeView from "../views/Make/MakeView.vue";

// Pops
import PopsView from "../views/Pops/PopsView.vue";
import PopsDetailView from "../views/Pops/PopsDetailView.vue";
import OriginalLinkedPopsView from "../views/Pops/OriginalLinkedPopsView.vue";

// Profile
import FeedView from "../views/Profile/FeedView.vue";
import FollowView from "../views/Profile/FollowView.vue";
import ProfileEditView from "../views/Profile/ProfileEditView.vue";
import FeedEditView from "../views/Profile/FeedEditView.vue";
import FeedAddView from "../views/Profile/FeedAddView.vue";
import PopsInFeedView from "../views/Profile/PopsInFeedView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/youtube/:youtubePk",
    name: "youtube",
    component: YoutubeView,
    props: true,
  },
  {
    path: "/youtubeedit/:youtubePk",
    name: "youtubeedit",
    component: YoutubeEditView,
    props: true,
  },
  {
    path: "/search",
    name: "search",
    component: SearchView,
  },
  {
    path: "/searchmorepops",
    name: "searchmorepops",
    component: SearchMorePopsView,
  },
  {
    path: "/searchmoreyoutube",
    name: "searchmoreyoutube",
    component: SearchMoreYoutubeView,
  },
  {
    path: "/searchmorefollowing",
    name: "searchmorefollowing",
    component: SearchMoreFollowingView,
  },

  // Sign-in 화면
  {
    path: "/signin",
    name: "signin",
    component: SigninView,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
  },
  {
    path: "/findpw",
    name: "findpw",
    component: FindPwView,
  },

  // 취향 선택 화면
  {
    path: "/interest",
    name: "interest",
    component: InterestView,
  },

  // Make 화면
  {
    path: "/make",
    name: "make",
    component: MakeView,
  },

  // Pops 화면
  {
    path: "/pops",
    name: "pops",
    component: PopsView,
  },
  {
    path: "/popsdetail",
    name: "popsdetail",
    component: PopsDetailView,
  },
  {
    path: "/originallinkedpops",
    name: "originallinkedpops",
    component: OriginalLinkedPopsView,
  },

  // 마이프로필 화면
  {
    path: "/feed/:userPk",
    name: "feed",
    component: FeedView,
  },
  {
    path: "/follow",
    name: "follow",
    component: FollowView,
  },
  {
    path: "/profileedit",
    name: "profileedit",
    component: ProfileEditView,
  },
  {
    path: "/feededit",
    name: "feededit",
    component: FeedEditView,
  },
  {
    path: "/feedadd",
    name: "feedadd",
    component: FeedAddView,
  },

  // 친구 피드 화면
  {
    path: "/popsinfeed",
    name: "popsinfeed",
    component: PopsInFeedView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

import router from "@/router";
import axios from "axios";

export default {
  state: {
    // 마이프로필 상단 유저 정보 state
    feedUserInfo: {},
    // 마이프로필 중단 유저 Feed List state
    feedList: [],
    freindInfo: {},
    feedPops: [],
    feedPop: {},
    followtab: 0, // 팔로워 클릭이면 0, 팔로잉 클릭이면 1
    isDelete: true,
    editUserInfo: {},
    currentFeedId: 0,
  },
  getters: {
    feedUserInfo: (state) => state.feedUserInfo,
    freindInfo: (state) => state.freindInfo,
    followtab: (state) => state.followtab,
    isDelete: (state) => state.isDelete,
    deleteFeedPopsList: (state) => state.isDelete,
    feedList: (state) => state.feedList,
    feedPops: (state) => state.feedPops,
    feedPop: (state) => state.feedPop,
    editUserInfo: (state) => state.editUserInfo,
    currentFeedId: (state) => state.currentFeedId,
  },
  mutations: {
    SET_FEEDUSERINFO: (state, feedUserInfo) => (state.feedUserInfo = feedUserInfo),
    SET_FEEDLIST: (state, feedList) => (state.feedList = feedList),
    SET_FEEDPOPS: (state, feedPops) => (state.feedPops = feedPops),
    SET_FRIENDINFO: (state, freindInfo) => (state.freindInfo = freindInfo),
    SET_FOLLOWTAB: (state, followtab) => (state.followtab = followtab),
    SET_ISDELETE: (state, isDelete) => (state.isDelete = isDelete),
    SPLICE_FEEDPOPS: function (state, id) {
      state.feedPops.forEach((value, index, array) => {
        console.log(array);
        if (value.id === id) {
          state.feedPops.spilce(index, 1);
        }
      });
    },
    SET_FEEDPOP: (state, feedPop) => (state.feedPop = feedPop),
    SET_EDITUSERINFO: (state, editUserInfo) => (state.editUserInfo = editUserInfo),
    SET_CURRENTFEEDID: (state, currentFeedId) => (state.currentFeedId = currentFeedId),
  },
  actions: {
    // *마이* 프로필 화면을 갈 때 상단에 유저 정보(게시글 수 등) 받는 함수
    // 어떤 유저인지를 입력변수로 받음
    fetchFeedUserInfo({ commit }, userId) {
      console.log("우선 여기", userId);
      axios({
        method: "get",
        url: `http://localhost:8080/user/profile/${userId}`,
        // url: `http://localhost:8080/user/profile/1`,
      }).then((res) => {
        commit("SET_FEEDUSERINFO", res.data);
        router.push({ name: "feed" });
      });
    },

    // 마이 프로필 화면 중간 피드 목록 받는 함수
    // 받자마자 대표피드 id 찾아서 popsInFeed 받는 함수 실행시켜야 함
    // 어떤 유저인지를 입력변수로 받음
    // fetchFeedList({ commit, getters }, userId) {
    fetchFeedList({ commit, getters }, userId) {
      console.log("2번");
      axios({
        method: "get",
        url: `http://localhost:8080/user/profile/feed/${userId}`,
        // url: `http://localhost:8080/user/profile/feed/1`,
      }).then((res) => {
        console.log("피드리스트", res.data);
        if (res.data.length == 0) {
          commit("SET_FEEDPOPS", []);
        }
        for (const feed of res.data) {
          if (feed.first) {
            console.log("3번");
            axios({
              method: "post",
              url: "http://localhost:8080/user/profile/popList",
              data: {
                userId: getters.userId,
                feedId: feed.id,
              },
            }).then((res) => {
              console.log(getters.userId);
              console.log(getters.userId);
              console.log("피드 팝스", res.data);
              commit("SET_FEEDPOPS", res.data);
            });
          }
        }
        commit("SET_FEEDLIST", res.data);
      });
    },

    // 피드 클릭 시 피드의 팝스들을 리스트로 받는 함수
    fetchFeedPops({ commit, getters }, feedId) {
      console.log("3번");
      axios({
        method: "post",
        url: `http://localhost:8080/user/profile/popList`,
        data: {
          userId: getters.feedUserInfo.id,
          feedId: feedId,
        },
      }).then((res) => {
        console.log("피드팝스", res.data);
        console.log(res.data);
        commit("SET_FEEDPOPS", res.data);
      });
    },

    fetchFriendInfo({ commit }, fromUserId, toUserId) {
      axios({
        method: "post",
        url: `http://localhost:8080/user/profile/freind`,
        data: {
          fromUserId: fromUserId,
          toUserId: toUserId,
        },
      }).then((res) => {
        commit("SET_FRIENDINFO", res.data);
      });
    },

    setTabFollower({ commit }) {
      commit("SET_FOLLOWTAB", 0);
    },

    setTabFollowing({ commit }) {
      commit("SET_FOLLOWTAB", 1);
    },

    fetchIsDelete({ commit }, isDelete) {
      commit("SET_ISDELETE", isDelete);
    },

    fetchIsNotDelete({ commit }, isDelete) {
      commit("SET_ISDELETE", isDelete);
    },

    spliceFeedPops({ commit }, id) {
      commit("SPLICE_FEEDPOPS", id);
    },
    getProfileInfo({ commit }, userId) {
      axios({
        method: "get",
        url: `http://localhost:8080/user/profile/edit/${userId}`,
      }).then((res) => {
        console.log(res);
        commit("SET_EDITUSERINFO", res.data);
      });
    },
    updateProfileInfo({ commit }, { userId, nickname, content }) {
      console.log(userId);
      console.log(nickname);
      console.log(content);
      axios({
        method: "put",
        url: `http://localhost:8080/user/profile`,
        data: {
          id: userId,
          nickname: nickname,
          content: content,
        },
      })
        .then((res) => {
          console.log(res);
          commit("RESET_FORM");
        })
        .catch((err) => {
          console.log(err);
          commit(err);
        });
    },

    fetchFeedPop({ commit }, pop) {
      commit("SET_FEEDPOP", pop);
    },

    fetchCurrentFeedId({ commit }, currentFeedId) {
      commit("SET_CURRENTFEEDID", currentFeedId);
    },
  },
};

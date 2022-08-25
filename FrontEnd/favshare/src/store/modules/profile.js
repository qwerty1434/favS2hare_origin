import router from "@/router";
import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  state: {
    feedUserInfo: {},
    feedList: [],
    freindInfo: {},
    feedPops: [],
    feedPop: {},
    followtab: 0,
    isDelete: true,
    followerList: [],
    followingList: [],
    editUserInfo: {},
    currentFeedId: 0,
    isFollowing: false,
    followActiveTab: 0,
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
    followerList: (state) => state.followerList,
    followingList: (state) => state.followingList,
    editUserInfo: (state) => state.editUserInfo,
    currentFeedId: (state) => state.currentFeedId,
    isFollowing: (state) => state.isFollowing,
    followActiveTab: (state) => state.followActiveTab,
  },
  mutations: {
    SET_FEEDUSERINFO: (state, feedUserInfo) =>
      (state.feedUserInfo = feedUserInfo),
    SET_FEEDLIST: (state, feedList) => (state.feedList = feedList),
    SET_FEEDPOPS: (state, feedPops) => (state.feedPops = feedPops),
    SET_FRIENDINFO: (state, freindInfo) => (state.freindInfo = freindInfo),
    SET_FOLLOWTAB: (state, followtab) => (state.followtab = followtab),
    SET_ISDELETE: (state, isDelete) => (state.isDelete = isDelete),
    SPLICE_FEEDPOPS: function (state, id) {
      state.feedPops.splice(id, 1);
    },
    SET_FEEDPOP: (state, feedPop) => (state.feedPop = feedPop),
    SET_EDITUSERINFO: (state, editUserInfo) =>
      (state.editUserInfo = editUserInfo),
    SET_CURRENTFEEDID: (state, currentFeedId) =>
      (state.currentFeedId = currentFeedId),
    SET_FOLLOWERLIST: (state, followerList) =>
      (state.followerList = followerList),
    SET_FOLLOWINGLIST: (state, followingList) =>
      (state.followingList = followingList),
    SET_ISFOLLOWING: (state, isFollowing) => (state.isFollowing = isFollowing),
    SET_FOLLOWACTIVETAB: (state, followActiveTab) =>
      (state.followActiveTab = followActiveTab),
  },
  actions: {
    fetchFeedUserInfo({ commit }, userId) {
      if (userId != 0) {
        axios({
          method: "get",
          url: api.userProfile.profileGet(userId),
        }).then((res) => {
          commit("SET_FEEDUSERINFO", res.data);
          router.push({ name: "feed" });
        });
      }
    },

    
    fetchFeedList({ commit, getters }, userId) {
      if (userId != 0) {
        axios({
          method: "get",
          url: api.userProfile.profileFeed(userId),
        }).then((res) => {
          if (res.data.length == 0) {
            commit("SET_FEEDPOPS", []);
          }
          for (const feed of res.data) {
            if (feed.first) {
              axios({
                method: "post",
                url: api.userProfile.profilePopList(),
                data: {
                  userId: getters.userId,
                  feedId: feed.id,
                },
              }).then((res) => {
                commit("SET_FEEDPOPS", res.data);
              });
            }
          }
          commit("SET_FEEDLIST", res.data);
        });
      }
    },

    fetchFeedPops({ commit, getters }, feedId) {
      axios({
        method: "post",
        url: api.userProfile.profilePopList(),
        data: {
          userId: getters.feedUserInfo.id,
          feedId: feedId,
        },
      }).then((res) => {
        commit("SET_FEEDPOPS", res.data);
      });
    },

    fetchFriendInfo({ commit }, fromUserId, toUserId) {
      axios({
        method: "post",
        url: api.userProfile.profileFriend(),
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
      commit("SET_FOLLOWACTIVETAB", 0);
    },

    setTabFollowing({ commit }) {
      commit("SET_FOLLOWTAB", 1);
      commit("SET_FOLLOWACTIVETAB", 1);
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
        url: api.userProfile.profileEdit(userId),
      }).then((res) => {
        commit("SET_EDITUSERINFO", res.data);
      });
    },
    updateProfileInfo(
      { commit },
      { userId, nickname, content, profileImageUrl }
    ) {
      axios({
        method: "put",
        url: api.userProfile.profile(),
        data: {
          id: userId,
          nickname: nickname,
          content: content,
          profileImageUrl: profileImageUrl,
        },
      })
        .then((res) => {
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

    fetchFollowerList({ commit, getters }) {
      axios({
        method: "get",
        url: api.userFollow.followTo(getters.feedUserInfo.id),
      })
        .then((res) => {
          commit("SET_FOLLOWERLIST", res.data);
        })
        .catch((err) => console.log(err));
    },

    fetchFollowingList({ commit, getters }) {
      axios({
        method: "get",
        url: api.userFollow.followFrom(getters.feedUserInfo.id),
      }).then((res) => {
        commit("SET_FOLLOWINGLIST", res.data);
      });
    },

    // 다른 유저 프로필 화면 들어갈 때 팔로우 하고있는지 확인하기 위한 함수
    fetchIsFollowing({ commit, getters }, id) {
      axios({
        method: "get",
        url: api.userFollow.followFrom(getters.userId),
      })
        .then((res) => {
          const len = res.data.length;
          let tmp = 0;
          for (const user of res.data) {
            if (user.toUserId === id) {
              commit("SET_ISFOLLOWING", true);
              break;
            } else {
              console.log("else안에");
              tmp++;
            }
          }
          if (tmp === len) {
            commit("SET_ISFOLLOWING", false);
          }
        })
        .catch((err) => console.log(err));
    },

    // 다른 유저 프로필 화면에서 팔로우를 눌렀을 때 함수
    fetchFollowInProfile({ commit, getters }, id) {
      axios({
        method: "post",
        url: api.userFollow.follow(),
        data: {
          fromUserId: getters.userId,
          toUserId: id,
        },
      }).then((res) => {
        this.dispatch("fetchFeedUserInfo", id);
      });
      commit("SET_ISFOLLOWING", true);
    },
  },
};

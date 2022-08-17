import axios from "axios";

export default {
  state: {
    userIdInPopsTab: 1,
    popsIdInPopsTab: 1,
    userImgInPopsTab: "image",
    commentList: [],
    popsInfo: {},
    popsTypeList: [],
    popList: [],
  },
  getters: {
    userIdInPopsTab: (state) => state.userIdInPopsTab,
    popsIdInPopsTab: (state) => state.popsIdInPopsTab,
    userImgInPopsTab: (state) => state.userImgInPopsTab,
    commentList: (state) => state.commentList,
    popsInfo: (state) => state.popsInfo,
    popsTypeList: (state) => state.popsTypeList,
    popList: (state) => state.popList,
  },
  mutations: {
    SET_COMMENT_LIST: (state, commentList) => (state.commentList = commentList),
    SET_POPS_INFO: (state, popsInfo) => (state.popsInfo = popsInfo),
    SET_POPSTYPELIST(state, popsTypeList) {
      const tmpArray = [
        { id: 0, name: "none", content: "none", idolImage: "none" },
      ];
      tmpArray.push(...popsTypeList);
      state.popsTypeList = tmpArray;
    },
    SET_POPLIST: (state, popList) => (state.popList = popList),
  },
  actions: {
    getPopsInfo({ commit }, { popId, userId }) {
      console.log(popId + " " + userId);
      axios({
        method: "post",
        url: `http://localhost:8080/pop/info`,
        data: {
          popId: popId,
          userId: userId,
        },
      }).then((res) => {
        console.log(res.data.popInfoDto);
        commit("SET_POPS_INFO", res.data.popInfoDto);
      });
    },
    getComment({ commit }, { popId, userId }) {
      console.log(popId + " " + userId);
      axios({
        method: "post",
        url: `http://localhost:8080/pop/comment/list`,
        data: {
          popId: popId,
          userId: userId,
        },
      })
        .then((res) => {
          commit("SET_COMMENT_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    insertComment({ commit }, { content, popId, userId }) {
      console.log(content + " " + popId + " " + userId);
      axios({
        method: "post",
        url: `http://localhost:8080/pop/comment`,
        data: {
          content: content,
          popId: popId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: `http://localhost:8080/pop/comment/list`,
            data: {
              popId: popId,
              userId: userId,
            },
          }).then((res) => {
            commit("SET_COMMENT_LIST", res.data);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteComment({ commit }, { commentId, userId, popId }) {
      console.log(commentId + " " + userId + " " + popId);
      axios({
        method: "delete",
        url: `http://localhost:8080/pop/comment`,
        data: {
          commentId: commentId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: `http://localhost:8080/pop/comment/list`,
            data: {
              popId: popId,
              userId: userId,
            },
          }).then((res) => {
            commit("SET_COMMENT_LIST", res.data);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    likeComment({ commit }, { commentId, userId }) {
      axios({
        method: "post",
        url: `http://localhost:8080/pop/like`,
        data: {
          commentId: commentId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: `http://localhost:8080/pop/comment/list`,
            data: {
              popId: popId,
              userId: userId,
            },
          }).then((res) => {
            commit("SET_COMMENT_LIST", res.data);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    unLikeComment({ commit }, { commentId, userId }) {
      axios({
        method: "delete",
        url: `http://localhost:8080/pop/like`,
        data: {
          commentId: commentId,
          userId: userId,
        },
      }).then(() => {
        axios({
          method: "post",
          url: `http://localhost:8080/pop/comment/list`,
          data: {
            popId: popId,
            userId: userId,
          },
        }).then((res) => {
          commit("SET_COMMENT_LIST", res.data);
        });
      });
    },
    fetchInterestIdol({ commit, getters }) {
      axios({
        method: "get",
        url: `http://localhost:8080/pop/idolList/${getters.userId}`,
      }).then((res) => {
        commit("SET_POPSTYPELIST", res.data);
      });
    },
    fetchPopList({ commit, getters }, idolId) {
      axios({
        method: "post",
        url: "http://localhost:8080/pop",
        data: {
          idolId: idolId,
          userId: getters.userId,
        },
      }).then((res) => {
        commit("SET_POPLIST", res.data);
      });
    },
  },
};

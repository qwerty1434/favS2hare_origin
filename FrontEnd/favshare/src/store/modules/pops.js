import axios from "axios";

export default {
  state: {
    userIdInPopsTab: 1,
    popsIdInPopsTab: 1,
    userImgInPopsTab: "image",
    commentList: [],
    popsInfo: {},
  },
  getters: {
    userIdInPopsTab: (state) => state.userIdInPopsTab,
    popsIdInPopsTab: (state) => state.popsIdInPopsTab,
    userImgInPopsTab: (state) => state.userImgInPopsTab,
    commentList: (state) => state.commentList,
    popsInfo: (state) => state.popsInfo,
  },
  mutations: {
    SET_COMMENT_LIST: (state, commentList) => (state.commentList = commentList),
    SET_POPS_INFO: (state, popsInfo) => (state.popsInfo = popsInfo),
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
    getComment({ commit }, { popsIdInPopsTab }) {
      console.log(popsIdInPopsTab);
      axios({
        method: "get",
        url: `http://localhost:8080/pop/comment/${popsIdInPopsTab}`,
      }).then((res) => {
        console.log(res.data);
        commit("SET_COMMENT_LIST", res.data);
      });
    },
    insertConmment({ commit }, { content, popId, userId }) {
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
            method: "get",
            url: `http://localhost:8080/pop/comment/${popId}`,
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
            method: "get",
            url: `http://localhost:8080/pop/comment/${popId}`,
          }).then((res) => {
            commit("SET_COMMENT_LIST", res.data);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

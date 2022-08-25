import axios from "axios";
import api from "@/api/springRestAPI";

export default {
  state: {
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
        { id: 0, name: "전체", content: "none", idolImage: "none" },
      ];
      tmpArray.push(...popsTypeList);
      state.popsTypeList = tmpArray;
    },
    SET_POPLIST: (state, popList) => (state.popList = popList),
  },
  actions: {
    getPopsInfo({ commit }, { popId, userId }) {
      axios({
        method: "post",
        url: api.pop.info(),
        data: {
          popId: popId,
          userId: userId,
        },
      })
        .then((res) => {
          commit("SET_POPS_INFO", res.data.popInfoDto);
        })
        .catch((err) => console.log("getPopsInfo 에러", err));
    },
    countView({ commit }, { popId }) {
      axios({
        method: "put",
        url: api.pop.detail(popId),
      })
        .then((res) => {
          commit("SET_POPS_INFO");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deletePopsOne({ commit }, { popId }) {
      axios({
        method: "delete",
        url: api.pop.pop(),
        data: {
          popId: popId,
        },
      }).then((res) => {
        commit("SET_POPS_INFO");
      });
    },
    getComment({ commit }, { popId, userId }) {
      axios({
        method: "post",
        url: api.comment.list(),
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
    likePops({ commit }, { popId, userId }) {
      axios({
        method: "post",
        url: api.pop.like(),
        data: {
          popId: popId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: api.pop.info(),
            data: {
              popId: popId,
              userId: userId,
            },
          }).then((res) => {
            commit("SET_POPS_INFO", res.data.popInfoDto);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    unLikePops({ commit }, { popId, userId }) {
      axios({
        method: "delete",
        url: api.pop.like(),
        data: {
          popId: popId,
          userId: userId,
        },
      }).then(() => {
        axios({
          method: "post",
          url: api.pop.info(),
          data: {
            popId: popId,
            userId: userId,
          },
        }).then((res) => {
          commit("SET_POPS_INFO", res.data.popInfoDto);
        });
      });
    },
    insertComment({ commit }, { content, popId, userId }) {
      console.log(content + " " + popId + " " + userId);
      axios({
        method: "post",
        url: api.comment.all(),
        data: {
          content: content,
          popId: popId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: api.comment.list(),
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
      axios({
        method: "delete",
        url: api.comment.all(),
        data: {
          commentId: commentId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: api.comment.list(),
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
    likeComment({ commit }, { popId, commentId, userId }) {
      axios({
        method: "post",
        url: api.likeComment.likeComment(),
        data: {
          commentId: commentId,
          userId: userId,
        },
      })
        .then(() => {
          axios({
            method: "post",
            url: api.comment.list(),
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
    unLikeComment({ commit }, { popId, commentId, userId }) {
      axios({
        method: "delete",
        url: api.likeComment.likeComment(),
        data: {
          commentId: commentId,
          userId: userId,
        },
      }).then(() => {
        axios({
          method: "post",
          url: api.comment.list(),
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
        url: api.pop.idolList(getters.userId),
      }).then((res) => {
        commit("SET_POPSTYPELIST", res.data);
      });
    },
    fetchPopList({ commit, getters }, idolId) {
      axios({
        method: "post",
        url: api.pop.pop(),
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

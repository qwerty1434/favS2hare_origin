export default {
  state: {
    dialogComment: false,
    dialogInfo: false,
    isLiked: false,
    isLikedAny: false,
  },
  getters: {
    dialogComment: (state) => state.dialogComment,
    dialogInfo: (state) => state.dialogInfo,
    isLiked: (state) => state.isLiked,
  },
  mutations: {
    SET_DIALOG_COMMENT: (state, dialogComment) =>
      (state.dialogComment = dialogComment),
    SET_DIALOG_INFO: (state, dialogInfo) => (state.dialogInfo = dialogInfo),
    SET_LIKE_POPS: (state, isLiked) => (state.isLiked = isLiked),
  },
  actions: {
    fetchDialogComment({ commit }) {
      commit("SET_DIALOG_COMMENT", true);
    },
    closeDialogComment({ commit }) {
      commit("SET_DIALOG_COMMENT", false);
    },
    fetchDialogInfo({ commit }) {
      commit("SET_DIALOG_INFO", true);
    },
    closeDialogInfo({ commit }) {
      commit("SET_DIALOG_INFO", false);
    },
  },
};

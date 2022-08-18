export default {
  state: {
    isLiked: false,
    isLikedAny: false,
  },
  getters: {
    isLiked: (state) => state.isLiked,
  },
  mutations: {
    SET_LIKE_POPS: (state, isLiked) => (state.isLiked = isLiked),
  },
  actions: {},
};

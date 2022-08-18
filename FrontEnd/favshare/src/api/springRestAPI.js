// const HOST = "http://localhost:8080/api";
const HOST = "http://localhost:8080";

// controller별로 변수 선언
const COMMENT = "/pop/comment";
const FEED = "/feed";
const LIKE_COMMENT = "/pop/likeComment";
const POP = "/pop";
const SEARCH = "/search";
const USER = "/user";
const USER_FEED = "/user/feed";
const USER_FOLLOW = "/user/follow";
const USER_INFO = "/user/info";
const UESR_INTEREST = "/user/interest";
const USER_PASSWORD = "/user/password";
const USER_PROFILE = "/user/profile";
const YOUTUBE = "/youtube";
const YOUTUBE_BOOKMARK = "/youtube/bookmark";
const YOUTUBE_EDIT = "/youtube/edit";

export default {
  comment: {
    all: () => HOST + COMMENT, // get, post, delete
    list: () => HOST + COMMENT + "/list", // get
  },
  feed: {
    feedDelete: (feedId) => HOST + FEED + `/${feedId}`,
    feedPost: (userId) => HOST + FEED + `/${userId}`,
    imagePut: () => HOST + FEED + "/image",
    namePut: () => HOST + FEED + "/name",
    pop: () => HOST + FEED + "/pop", // post, delete
    star: () => HOST + FEED + "/star",
  },
  likeComment: {
    likeComment: () => HOST + LIKE_COMMENT, // post, delete
  },
  pop: {
    pop: () => HOST + POP, // post, delete
    detail: (popId) => HOST + POP + `/detail/${popId}`,
    friend: (userId) => HOST + POP + `/friend/${userId}`,
    idolList: (userId) => HOST + POP + `/idolList/${userId}`,
    info: () => HOST + POP + "/info", // post
    like: () => HOST + POP + "/like", // post, delete
    youtube: () => HOST + POP + "/youtube", // post
  },
  search: {
    search: () => HOST + SEARCH, // post
  },
  user: {
    login: () => HOST + USER + "/login", // post
    signup: () => HOST + USER + "/signup", // post
    email: (email) => HOST + USER + `/signup/${email}`, // get
  },
  userFeed: {
    pop: () => HOST + USER_FEED + "/pop", // post
  },
  userFollow: {
    follow: () => HOST + USER_FOLLOW, // post,
    followFromDelete: () => HOST + USER_FOLLOW + "/from", //delete
    followFrom: (userId) => HOST + USER_FOLLOW + `/from/${userId}`, // get
    followToDelete: () => HOST + USER_FOLLOW + "/to", // delete
    followTo: (userId) => HOST + USER_FOLLOW + `/to/${userId}`, // get
  },
  userInfo: {
    confirmPw: () => HOST + USER_INFO, // post
    editProfile: () => HOST + USER_INFO, // put
    deleteUser: (userId) => HOST + USER_INFO + `/${userId}`, // delete
  },
  userInterest: {
    selectInterest: () => HOST + UESR_INTEREST, // post
    searchForInterestIdol: (name) => HOST + UESR_INTEREST + `/findIdol/${name}`, // get
    getIdolInfo: () => HOST + UESR_INTEREST + `/idol`, // get
    interestInfo: (userId) => HOST + UESR_INTEREST + `/${userId}`, // get, put, delete
    getInterestSong: () => HOST + UESR_INTEREST + `/song`, //get
  },
  userPassword: {
    updatePw: () => HOST + USER_PASSWORD, // put
    postAuth: () => HOST + USER_PASSWORD + `/auth`, // post
    sendAuthToEmail: (email) => HOST + USER_PASSWORD + `/sendAuth/${email}`, //get
  },
  userProfile: {
    profile: () => HOST + USER_PROFILE, // put
    profileGet: (userId) => HOST + USER_PROFILE + `/${userId}`, // get
    profileEdit: (userId) => HOST + USER_PROFILE + `/edit/${userId}`, // get
    profileFeed: (userId) => HOST + USER_PROFILE + `/feed/${userId}`, // get
    profileFriend: () => HOST + USER_PROFILE + "/friend", //post
    profilePopList: () => HOST + USER_PROFILE + "/popList", //post, get
  },
  youtube: {
    youtube: (userId) => HOST + YOUTUBE + `/${userId}`, // get
    detail: () => HOST + YOUTUBE + "/detail", // post
  },
  youtubeBookmark: {
    youtubeBookmark: () => HOST + YOUTUBE_BOOKMARK, // post, delete
    youtubeBookmarkUser: (userId) => HOST + YOUTUBE_BOOKMARK + `/${userId}`, // get
  },
  youtubeEdit: {
    youtubeEdit: () => HOST + YOUTUBE_EDIT, // post
    info: () => HOST + YOUTUBE_EDIT + "/info", // post
  },
};

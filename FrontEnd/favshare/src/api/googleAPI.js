const GOOGLE = "https://www.googleapis.com/youtube/v3";

export default {
  videos: () => GOOGLE + "/videos",
  channels: () => GOOGLE + "/channels",
};

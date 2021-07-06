export default {
  namespaced: true,
  state: {
    data: [],
    isLoading: false,
  },
  mutations: {
    //state:原来的状态 payload：负荷(欲改变值)
    setIsLoading(state, payload) {
      state.isLoading = payload;
    },
    setData(state, payload) {
      state.data = payload;
    },
  },
  actions: {
    async fetchDatas(context) {
      context.commit("setIsLoading", true);
      //加载数据
      context.commit("setIsLoading", false);
    },
  },
};

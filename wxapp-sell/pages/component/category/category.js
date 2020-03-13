const app = getApp();
Page({
  data: {
    categoryList: [],
    categorySecList: [],
    curIndex: 0,
    isScroll: false,
  },
  onReady() {
    var self = this;
    wx.request({
      url: app.getUrl.url +'/buyer/category/list',
      success(res) {
        console.log(res.data)
        var list = res.data.data.categoryList;
        self.setData({
          categoryList: list,
          categorySecList: list[0].productCategorySecList
        })
      }
    });

  },
  switchTab(e) {
    this.setData({
      toView: e.target.dataset.id,
      curIndex: e.target.dataset.index,
      categorySecList: e.target.dataset.categoryseclist
    })
  },
})
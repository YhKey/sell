const app = getApp();
// pages/component/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {},

  /**
   * 组件的初始数据
   */
  data: {
    imgUrls: [
      '/image/b1.jpg',
      '/image/b2.jpg',
      // '/image/b3.jpg'
    ],
    product: [],
    indicatorDots: false,
    autoplay: false,
    interval: 3000,
    duration: 800,
  },

  /**
   * 组件的方法列表
   */
  methods: {

  },

  onReady() {
    var self = this;
    wx.request({
      url: app.getUrl.url +'/buyer/product/list',
      success(res) {
        console.log(res.data)
        var list = res.data.data.productList;
        self.setData({
          product: list
        })
      }
    });

  },
})
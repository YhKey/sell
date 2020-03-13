// pages/component/orders/orders.js
// const mine = require("../mine/mine,js");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    address: {},
    hasAddress: false,
    total: 0,
    orders: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    // console.log(mine.data);
    // 获取产品展示页保存的缓存数据（购物车的缓存数组，没有数据，则赋予一个空数组）  
    var arr = wx.getStorageSync('cart');
    var isSelect = new Array();
    var total = 0;
    for (var i = 0; i < arr.length; i++) {
      if (arr[i].isSelect == true) {
        total = total + arr[i].count * arr[i].price
        isSelect.push(arr[i]);
      }
    }

    var address = wx.getStorageSync('address') || {};
    var hasAddress;
    if (Object.keys(address).length === 0){
      hasAddress =false
    }else{
      hasAddress =true;
    }
    // 更新数据  
    this.setData({
      orders: isSelect,
      address: address,
      hasAddress: hasAddress,
      total: total,
      iscart: true,
      hidden: false
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  toPay(e) {
    var that = this; 
    if (Object.keys(that.data.address).length === 0){
      wx.showToast({
        title: "请选择收货地址",
        icon: 'none',
        image: '',
        duration: 3000,
      });
      return;
    }
    var items=[];
    var orders = that.data.orders;
    for (var i = 0; i <orders.length;i++){
      var item={
        productId: orders[i].id,
        productQuantity: orders[i].count
      };
      items.push(item);
    }
    var openid = wx.getStorageSync('openid');
    var orderForm={
      name: that.data.address.name,
      phone: that.data.address.phone,
      address: that.data.address.address,
      openId: openid,
      items: JSON.stringify(items)
    };
    wx.showModal({
      title: '提示',
      content: '本系统只做演示，支付系统已屏蔽',
      success: function(sm) {
        //如果成功弹窗 ,sm为窗口 confirm：用户点击确认
        if (sm.confirm) {
          //访问后台
          wx.request({
            url: app.getUrl.url + '/buyer/order/create',
            method: 'POST',
            data: JSON.stringify(orderForm),
            dataType: 'json',
            success: function(res) {
              var result = res.data.code;
              var toastText = '下单成功！';
              if (result != 0) {
                toastText = '下单失败';
              } 
              wx.showToast({
                title: toastText,
                icon: '',
                image: '',
                duration: 3000,
              });
              //跳转订单页面
              wx.navigateTo({
                url: '../orderList/orderList',
              });
            }
          })
        }
      }
    })
  }
})
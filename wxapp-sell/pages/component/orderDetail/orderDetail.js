var app = getApp();
Page({
  data: {
    orderDetail:{},
  },
  onLoad: function () {
    var orderDetail=wx.getStorageSync("orderDetail");
    this.setData({
      orderDetail: orderDetail,
    })
  },

  toCancel:function(e){
    wx.showModal({
      title: '提示',
      content: '是否取消该订单',
      success: function (sm) {
        //如果成功弹窗 ,sm为窗口 confirm：用户点击确认
        if (sm.confirm) {
          var openid=wx.getStorageSync("openid");
          //访问后台取消订单
          wx.request({
            url: app.getUrl.url + '/buyer/order/cancel',
            method: 'POST',
            data: {
              openid: openid,
              orderId: e.target.dataset.orderid,
            },
            header: {
              'content-type': 'application/x-www-form-urlencoded',
            },
            success: function (res) {
              var result = res.data.code;
              var toastText = '取消订单成功！';
              if (result != 0) {
                toastText = '取消订单失败';
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
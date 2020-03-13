// pages/mine/mine.js
const app = getApp()
Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    orderItems: [
      {
        typeId: 0,
        name: '待付款',
        url: 'bill',
        imageurl: '../../../image/person/personal_pay.png',
      },
      {
        typeId: 1,
        name: '待发货',
        url: 'bill',
        imageurl: '../../../image/person/personal_comment.png'
      },
      {
        typeId: 2,
        name: '待收货',
        url: 'bill',
        imageurl: '../../../image/person/personal_receipt.png',
      },
      
      {
        typeId: 3,
        name: '退换/售后',
        url: 'bill',
        imageurl: '../../../image/person/personal_service.png'
      }
    ],
  },
  //事件处理函数
  toOrder: function () {
    wx.navigateTo({
      url: '../order/order'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })

    //获取openid
    let that = this;
    wx.login({
      success: function (res) {
        var d = app.globalData;//这里存储了appid、secret、token串
        console.log(d);
        var l = 'https://api.weixin.qq.com/sns/jscode2session?appid=' + d.appid + '&secret=' + d.secret + '&js_code=' + res.code + '&grant_type=authorization_code';
        //请求后台获取用户openid
        // var openid={};
        wx.request({
          url: l,
          data: {},
          method: 'GET',
          success: function (res) {
            var openid = res.data.openid;
            console.log('请求获取openid:' + openid);
            //将openid发送到服务器
            wx.request({
              url: app.getUrl.url + '/buyer/login',
              method: 'GET',
              data: {
                "openid": openid
              },
              success: function (res) {
                if (res.data.code == 0) {
                  console.log("登录成功！");
                }
              }
            });
            //可以把openid存到本地，方便以后调用
            wx.setStorageSync('openid', openid);
          }
        });
      }
    })
  },

  /*全部订单 */
  myOrderList(){
    if (!this.data.hasUserInfo) {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        image: '',
        duration: 3000,
      })
    } else {
      wx.navigateTo({ url: '../orderList/orderList?'});
    }
  },

  /*收货地址 */
  myAddress:function(e){
    if (!this.data.hasUserInfo) {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        image: '',
        duration: 3000,
      })
    } else {
      wx.navigateTo({ url: '../addressList/addressList?flag=mine' });      
    }
  }
})

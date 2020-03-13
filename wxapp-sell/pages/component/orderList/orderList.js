const  app = getApp()
var ifLoadMore = null;
var sectionData = [];
var page=0;
Page({

  data: {
    // 页面配置  
    winWidth: 0,
    winHeight: 0,

    // tab切换
    currentTab: 0,
    swipertab: [{
      name: '全部',
      index: 0
    }, {
      name: '待发货',
      index: 1
    }, {
      name: '待收货',
      index: 2
    }, {
      name: '已取消',
      index: 3
    }],
  },

  onLoad: function (options) {

  },

  onShow: function (options){
    var that = this;
    // 获取系统信息
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    });
    that.orderShow()
  },

  onReady: function (){

  },

  // 点击tab切换
  swichNav: function(e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },
  // 滑动切换tab
  bindChange: function (e) {
    var that = this;
    that.setData({
      currentTab: e.detail.current
    });
    that.orderShow();
  },


  orderShow: function () {
    let that = this;
    switch (that.data.currentTab) {
      case 0:
        that.alreadyShow()
        break
      case 1:
        that.waitPayShow()
        break
      case 2:
        that.waitReceiveShow()
        break
      case 3:
        that.lostShow()
        break
    }
  },

  /**
  * 页面上拉触底事件的处理函数
  */
  onReachBottom: function () {
    console.log("上拉");
    var that = this;
    console.log('加载更多');
    if (ifLoadMore != null) {
      that.alreadyShow();
    }
  },

  alreadyShow: function () {
    var that=this;
    //访问后台获取用户订单
    var openid = wx.getStorageSync('openid');
    wx.request({
      url: app.getUrl.url +'/buyer/order/list',
      data: { 
        "openid": openid,
        "page":page,
        "size": 3,
        },
      method: 'GET',
      success: function (res) {
        var result = res.data.code;

        var alreadyOrder=res.data.data;
        page += 1;
        if (ifLoadMore) {
          //加载更多
          if (alreadyOrder.length > 0) {
            console.log(alreadyOrder)
            //日期以及title长度处理
            for (var i in alreadyOrder) {
              //商品名称长度处理
              var name = alreadyOrder[i].buyerName;
              if (name.length > 26) {
                alreadyOrder[i].buyerName = name.substring(0, 23) + '...';
              }
            }
            sectionData['newGoods'] = sectionData['newGoods'].concat(alreadyOrder);

          } else {
            ifLoadMore = false;
            that.setData({
              hidden: true
            })
            wx.showToast({
              title: '暂无更多内容！',
              icon: 'loading',
              duration: 2000
            })
          }

        } else {
          if (ifLoadMore == null) {
            ifLoadMore = true;
            //日期以及title长度处理
            for (var i in alreadyOrder) {
              //商品名称长度处理
              var name = alreadyOrder[i].buyerName;
              if (name.length > 26) {
                alreadyOrder[i].buyerName = name.substring(0, 23) + '...';
              }
            }
            sectionData['newGoods'] = alreadyOrder;//刷新
          }

        }
        that.setData({
          alreadyOrder: sectionData['newGoods'],
          // isHideLoadMore: true
        });
        wx.setStorageSync("alreadyOrder", sectionData['newGoods']);
        wx.stopPullDownRefresh();//结束动画
      }
    });

  },

  waitPayShow: function () {
    var that=this;
    var alreadyOrder = wx.getStorageSync("alreadyOrder");
    var waitPayOrder=[];
    for (var i in alreadyOrder){
      if (alreadyOrder[i].orderStatus == 0){
        waitPayOrder.push(alreadyOrder[i]);
      }
    }
    this.setData({
      waitPayOrder: waitPayOrder,
    })
  },

  waitReceiveShow: function(){
    var that=this;
    var alreadyOrder = wx.getStorageSync("alreadyOrder");
    var waitReceiveOrder = [];
    for (var i in alreadyOrder) {
      if (alreadyOrder[i].orderStatus == 1) {
        waitReceiveOrder.push(alreadyOrder[i]);
      }
    }
    this.setData({
      waitReceiveOrder: waitReceiveOrder,
    })
  },

  lostShow: function () {
    var that=this;
    var alreadyOrder = wx.getStorageSync("alreadyOrder");
    var lostOrder = [];
    for (var i in alreadyOrder) {
      if (alreadyOrder[i].orderStatus == 2) {
        lostOrder.push(alreadyOrder[i]);
      }
    }
    this.setData({
      lostOrder: lostOrder,
    })
  },

  toOrderDetail: function(e){
    var orderid=e.currentTarget.dataset.orderid;
    var that = this;
    var alreadyOrder = wx.getStorageSync("alreadyOrder");
    var orderDetail={};
    for (var i in alreadyOrder){
      if (alreadyOrder[i].orderId == orderid){
        orderDetail = alreadyOrder[i];
      }
    }
    wx.setStorageSync("orderDetail", orderDetail);
    wx.navigateTo({
      url: '../orderDetail/orderDetail',
    })
  },

})
// pages/component/cart/cart.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    carts: [], // 购物车列表
    iscart: false, // 列表是否有数据
    hidden: null,
    totalMoney: 0, // 总价，初始为0
    isAllSelect: false // 全选状态，默认全选
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
    var that=this;
    var Allprice=0,i=0;
    // 获取产品展示页保存的缓存数据（购物车的缓存数组，没有数据，则赋予一个空数组）  
    var arr = wx.getStorageSync('cart') || [];
    // 有数据的话，就遍历数据，计算总金额 和 总数量  
    if (arr.length > 0) {
      // 更新数据  
      that.setData({
        carts: arr,
        iscart: true,
        hidden: false
      });
      that.priceCount();
      //是否全选判断
      for (i = 0; i < that.data.carts.length; i++) {
        Allprice = Allprice + (that.data.carts[i].price * that.data.carts[i].count);
      }
      if (Allprice == that.data.totalMoney) {
        that.data.isAllSelect = true;
      } else {
        that.data.isAllSelect = false;
      }
      that.setData({
        isAllSelect: that.data.isAllSelect,
      })

    } else {
      that.setData({
        iscart: false,
        hidden: true,
      });
    }
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
  //勾选事件处理函数  
  switchSelect: function(e) {
    var that = this;
    // 获取item项的id，和数组的下标值  
    var Allprice = 0, i = 0;
    let id = e.target.dataset.id,
      index = parseInt(e.target.dataset.index);
    that.data.carts[index].isSelect = !that.data.carts[index].isSelect; //价钱统计
    if (that.data.carts[index].isSelect) {
      that.data.totalMoney = that.data.totalMoney + (that.data.carts[index].price * that.data.carts[index].count);
    } else {
      that.data.totalMoney = that.data.totalMoney - (that.data.carts[index].price * that.data.carts[index].count);
    }
    //是否全选判断
    for (i = 0; i < that.data.carts.length; i++) {
      Allprice = Allprice + (that.data.carts[i].price * that.data.carts[i].count);
    }
    if (Allprice == that.data.totalMoney) {
      that.data.isAllSelect = true;
    } else {
      that.data.isAllSelect = false;
    }
    console.log("Allprice:" + Allprice);
    console.log("totalMoney:" + that.data.totalMoney);
    that.setData({
      carts: that.data.carts,
      totalMoney: that.data.totalMoney,
      isAllSelect: that.data.isAllSelect,
    })
    wx.setStorageSync('cart', that.data.carts);
  },

  //全选
  allSelect: function(e) {
    //处理全选逻辑
    let i = 0;
    if (!this.data.isAllSelect) {
      this.data.totalMoney = 0;
      for (i = 0; i < this.data.carts.length; i++) {
        this.data.carts[i].isSelect = true;
        this.data.totalMoney = this.data.totalMoney + (this.data.carts[i].price * this.data.carts[i].count);
      }
    } else {
      for (i = 0; i < this.data.carts.length; i++) {
        this.data.carts[i].isSelect = false;
      }
      this.data.totalMoney = 0;
    }
    this.setData({
      carts: this.data.carts,
      isAllSelect: !this.data.isAllSelect,
      totalMoney: this.data.totalMoney,
    })

  },

  // 去结算
  toBuy() {
    //登录校验
    var openid = wx.getStorageSync('openid') || {};
    if (Object.keys(openid).length === 0) {
      wx.showModal({
        title: '提示',
        content: '请先登录',
        success: function(sm) {
          //如果成功弹窗 ,sm为窗口 confirm：用户点击确认
          if (sm.confirm) {
            wx.switchTab({
              url: '../mine/mine',
            });
            return false;
          } else {
            return false;
          }
        }
      });
    } else {
      var isSelect = false;
      for (var i = 0; i < this.data.carts.length; i++) {
        if (this.data.carts[i].isSelect == true) {
          isSelect = true;
        }
      }
      if (isSelect) {
        wx.navigateTo({
          url: '../orders/orders',
        })
      } else {
        wx.showToast({
          title: '未选择商品',
          icon: 'none',
          duration: 2000
        });
      }
    }

  },

  //数量变化处理
  handleQuantityChange(e) {
    var componentId = e.componentId;
    var quantity = e.quantity;
    this.data.carts[componentId].count.quantity = quantity;
    this.setData({
      carts: this.data.carts,
    });

  },

  /* 减数 */
  delCount: function(e) {
    var index = e.target.dataset.index;
    console.log("刚刚您点击了加一");
    var count = this.data.carts[index].count; // 商品总数量-1
    if (count > 1) {
      this.data.carts[index].count--;
    }
    // 将数值与状态写回  
    this.setData({
      carts: this.data.carts
    });
    console.log("carts:" + this.data.carts);
    this.priceCount();
  },

  /* 加数 */
  addCount: function(e) {
    var index = e.target.dataset.index;
    console.log("刚刚您点击了加+");
    var count = this.data.carts[index].count; // 商品总数量+1  
    if (count < 10) {
      this.data.carts[index].count++;
    }

    // 将数值与状态写回  
    this.setData({
      carts: this.data.carts
    });
    console.log("carts:" + this.data.carts);
    this.priceCount();
  },

  //计算总金额
  priceCount: function(e) {
    this.data.totalMoney = 0;
    for (var i = 0; i < this.data.carts.length; i++) {
      if (this.data.carts[i].isSelect == true) {
        this.data.totalMoney = this.data.totalMoney + (this.data.carts[i].price * this.data.carts[i].count);
      }
    }
    this.setData({
      totalMoney: this.data.totalMoney,
    })

  },

  /* 删除item */
  delGoods: function(e) {
    this.data.carts.splice(e.target.id.substring(3), 1); // 更新data数据对象  
    if (this.data.carts.length > 0) {
      this.setData({
        carts: this.data.carts
      })
      wx.setStorageSync('cart', this.data.carts);
      this.priceCount();
    } else {
      this.setData({
        cart: this.data.carts,
        totalMoney: 0,
        iscart: false,
        hidden: true,
      })
      wx.setStorageSync('cart', []);
    }

  }

})
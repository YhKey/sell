const app = getApp();
Page({
 
  /**
   * 页面的初始数据
   */
  data: {
    addressList: []
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // var arr = wx.getStorageSync('addressList') || [];
    // console.info("缓存数据：" + arr);
    // // 更新数据  
    // this.setData({
    //   addressList: arr
    // });
    this.setData({
      flag: options.flag
    });
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function (options) {
    // this.onLoad();
    var that = this;
    var openid = wx.getStorageSync('openid') || {};
    //查询地址信息
    wx.request({
      url: app.getUrl.url + '/buyer/detail/list',
      data: { "openid": openid },
      method: 'GET',
      success: function (res) {
        var result = res.data.code;
        var toastText = '查询地址成功！';
        if (result != 0) {
          toastText = '查询地址失败';
        } else {
          // 更新数据  
          that.setData({
            addressList: res.data.data.addressList
          });
        }
      }
    });
  },
  addAddress: function() {
    wx.navigateTo({
      url: '../address/address'
    });
  },

  /* 删除item */
  delAddress: function(e) {
    var detailid=e.target.dataset.detailid;
    //后台删除
    wx.request({
      url: app.getUrl.url + '/buyer/detail/remove',
      method: 'POST',
      data: {
        detailId: detailid
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      success: function (res) {
        var result = res.data.success;
        var toastText = '删除成功';
        if (result != true) {
          toastText = '删除失败';
        };
        wx.showToast({
          title: toastText,
          icon: 'none',
          duration: 2000,
        });
      }
    });
    var address = wx.getStorageSync('address') || {};
    if(Object.keys(address).length>0){
      if (address.detailId == detailid){
        wx.setStorageSync('address', {});
        console.log("删除"+address);
      }
      console.log("删除" + address);
    }

    this.data.addressList.splice(e.target.id.substring(3), 1);
    // 更新data数据对象  
    if (this.data.addressList.length > 0) {
      this.setData({
        addressList: this.data.addressList
      })
      wx.setStorageSync('addressList', this.data.addressList);
    } else {
      this.setData({
        addressList: this.data.addressList
      })
      wx.setStorageSync('addressList', []);
    }
  },

  /*点击事件*/
  selectAddress(e){
    var that = this;
    if (that.data.flag=='mine'){
      return;
    }
    var addressList= that.data.addressList;
    var detailId= e.target.dataset.detailid;
    for (var i in addressList){
      if (addressList[i].detailId == detailId){
        wx.setStorageSync('address', addressList[i]);
        wx.navigateBack({
          delta: 1
        });
      }
    }
  }
})
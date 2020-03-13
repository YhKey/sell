// pages/component/productList/productList.js
const app = getApp();
var sectionData = [];
var ifLoadMore = null;
var page = 0;//默认第一页
Page({

  /**
   * 页面的初始数据
   */
  data: {
    scrollH: 0,
    imgWidth: 0,
    loadingCount: 0,
    col1: [],
    col2: [],
    productList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //接受页面传递参数
    var categoryId = options.categoryId;
    var categorySecType = options.categorySecType;
    var that = this;

    that.setData({
      categoryId: categoryId,
      categorySecType: categorySecType
    });

    wx.getSystemInfo({
      success: (res) => {
        let ww = res.windowWidth;
        let wh = res.windowHeight;
        let imgWidth = ww * 0.48;
        let scrollH = wh;

        that.setData({
          scrollH: scrollH,
          imgWidth: imgWidth
        });
      }
    });

    this.alreadyShow();
  },

  /**
* 页面上拉触底事件的处理函数
*/
  onReachBottom: function () {
    var that = this;
    console.log('加载更多');
    if (ifLoadMore == true) {
      that.alreadyShow();
    }
  },

  alreadyShow: function () {
    var that = this;
    console.log("page:"+page);
    wx.request({
      url: app.getUrl.url + '/buyer/product/list',
      data: {
        "categoryId": that.data.categoryId,
        "categorySecType": that.data.categorySecType,
        "page":page,
        "size":6
      },
      method: 'GET',
      success(res) {
        console.log(res.data)
        page += 1;
        var productList = res.data.data.productList;
        if (ifLoadMore) {
          //加载更多
          if (productList.length > 0) {
            //日期以及title长度处理
            for (var i in productList) {
              //商品名称长度处理
              var name = productList[i].name;
              if (name.length > 26) {
                productList[i].name = name.substring(0, 23) + '...';
              }
            }
            sectionData['productList'] = sectionData['productList'].concat(productList);

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
            for (var i in productList) {
              //商品名称长度处理
              var name = productList[i].name;
              if (name.length > 26) {
                productList[i].buyerName = name.substring(0, 23) + '...';
              }
            }
            sectionData['productList'] = productList;//刷新
          }

        }
        that.setData({
          productList: productList,
          loadingCount: res.data.data.productList.length
        })
        wx.stopPullDownRefresh();//结束动画
      }
    });
  },

  onImageLoad1: function(e){
    console.log(e);
    var that=this;
    let imageId = e.currentTarget.id;
    // let oImgW = e.detail.width;         //图片原始宽度
    // let oImgH = e.detail.height;        //图片原始高度
    let oImgW = 800;         //图片原始宽度
    let oImgH = 800;        //图片原始高度
    let imgWidth = that.data.imgWidth;  //图片设置的宽度
    let scale = imgWidth / oImgW;        //比例计算
    let imgHeight = oImgH * scale;      //自适应高度

    let productList = that.data.productList;
    let product = null;

    for (let i in productList) {
      let pro = productList[i];
      if (pro.id + "" === imageId) {
        product = pro;
        break;
      }
    }

    product.height = imgHeight;

    let loadingCount = that.data.loadingCount - 1;
    let col1 = that.data.col1;
    let col2 = that.data.col2;

    //判断当前图片添加到左列还是右列
    if (col1.length <= col2.length) {
      col1.push(product);
    } else {
      col2.push(product);
    }

    let data = {
      loadingCount: loadingCount,
      col1: col1,
      col2: col2
    };

    //当前这组图片已加载完毕，则清空图片临时加载区域的内容
    if (!loadingCount) {
      data.images = [];
    }

    this.setData(data);

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    page=0;
    ifLoadMore=null;
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  catchTapCategory: function (e) {
    var that = this;
    var productId = e.currentTarget.dataset.goodsid;
    console.log('productId:' + productId);
    //新增商品用户点击数量
    // that.goodsClickShow(goodsId);
    //跳转商品详情
    wx.navigateTo({ url: '../detail/detail?productId=' + productId })
  },

})
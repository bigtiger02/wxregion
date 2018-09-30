//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    region:[]
  },
  bindRegionChange: function (e) {
    let region = e.detail.value;
    this.setData({ region: region});
    wx.request({
      url: 'http://192.168.31.66:8080/saveRegion', //仅为示例，并非真实的接口地址
      data: {region: region.join(",")},
      header: {
        'content-type': 'application/x-www-form-urlencoded'//默认值
      },
      success(res) {
        console.log(res.data)
      }
    });
  },
  onLoad: function () {
  },
})

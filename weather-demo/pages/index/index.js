const weatherMap = {
  'sunny': '晴天',
  'cloudy': '多云',
  'overcast': '阴',
  'lightrain': '小雨',
  'heavyrain': '大雨',
  'snow': '雪'
}

const weatherColorMap = {
  'sunny': '#cbeefd',
  'cloudy': '#deeef6',
  'overcast': '#c6ced2',
  'lightrain': '#bdd5e1',
  'heavyrain': '#c5ccd0',
  'snow': '#aae1fc'
}

const UNPROMPTED = 0
const UNAUTHORIZED = 1
const AUTHORIZED = 2

const UNPROMPTED_TIPS = "点击获取当前位置"
const UNAUTHORIZED_TIPS = "点击开启位置权限"
const AUTHORIZED_TIPS = ""

// 引入SDK核心类
const QQMapWX = require('../../libs/qqmap-wx-jssdk.js');

Page({
  data: {
    nowtemp: "",
    nowweather: "",
    nowWeatherImage: "",
    hourlyWeather: [],
    todayDate: "",
    todayTemp: "",
    city: "广州市",
    locationTipsText: UNPROMPTED_TIPS,
    locationAuthType: UNPROMPTED
  },

  onLoad() {
    console.log('onLoad')
    // 实例化API核心类
    this.qqmapsdk = new QQMapWX({
      key: 'EAXBZ-33R3X-AA64F-7FIPQ-BY27J-5UF5B'
    })
    this.getNow();
  },
  
  onPullDownRefresh() {
    this.getNow(() => {
      wx.stopPullDownRefresh();
      console.log("stopPullDown");
    })
  },

  getNow(callback) {
    wx.request({
      url: 'https://test-miniprogram.com/api/weather/now',
      data: {
        city: this.data.city
      },
      success: res => {
        // console.log(res);
        let result = res.data.result;
        this.setNow(result);
        this.setHourlyTime(result);
        this.setToday(result);
      },
      complete: () => {
        callback && callback();
      }
    })
  },

setNow(result) {
  let temp = result.now.temp;
  let weather = result.now.weather;
  this.setData({
    nowtemp: temp + "°",
    nowweather: weatherMap[weather],
    nowWeatherImage: '/images/' + weather + '-bg.png'
  })
  wx.setNavigationBarColor({
    frontColor: '#000000',
    backgroundColor: weatherColorMap[weather],
  })
},

setHourlyTime(result) {
  let nowHour = new Date().getHours();
  let hourlyWeather = [];
  let forecast = result.forecast;
  for (var i = 0; i < 7; i++) {
    hourlyWeather.push({
      time: (3 * i + nowHour) % 24 + "时",
      iconPath: "/images/" + forecast[i].weather + "-icon.png",
      temp: forecast[i].temp + '°',
    })
  }
  hourlyWeather[0].time = "现在";
  this.setData({
    hourlyWeather: hourlyWeather
  })
},

setToday(result) {
  let date = new Date();
  this.setData({
    todayDate: `${date.getFullYear()}-${date.getMonth()}-${date.getDate()} 今天`,
    todayTemp: `${result.today.minTemp}° -  ${result.today.maxTemp}°`
  })
},

onTapDayWeather() {
  wx.showToast();
  wx.navigateTo({
    url: '/pages/list/list?city=' + this.data.city,
  })
},

onTapLocation() {
  if (this.data.locationAuthType === UNAUTHORIZED)
    wx.openSetting({
      success: res => {
        let auth = res.authSetting['scope.userLocation']
        if (auth) {
          this.getLocation()
        }
      }
    })
  else
    this.getLocation()
},
getLocation(){
  wx.getLocation({
    success: res=>{
      // console.log(AUTHORIZED)
      this.setData({
        locationAuthType: AUTHORIZED,
        locationTipsText: AUTHORIZED_TIPS
      })
      this.qqmapsdk.reverseGeocoder({
        location: {
          latitude: res.latitude,
          longitude: res.longitude
        },
        success: res=>{
          let city = res.result.address_component.city
          this.setData({
            city: city,
          })
          this.getNow()
        }
      })
    },
    fail: ()=>{
      // console.log(UNAUTHORIZED)
      this.setData({
        locationAuthType: UNAUTHORIZED,
        locationTipsText: UNAUTHORIZED_TIPS
      })
    },
  })
}

})
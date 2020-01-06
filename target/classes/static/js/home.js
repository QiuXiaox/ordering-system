let userId;
let dishId;
var numStudent = 1; //当前页
let tableLength; // 分页长度
let studentObj = {
  "name": null,
  "detail": null,
}

let menuObj = {
  "dishTypeId": null,
  "detail": null,
  "name": null,
  "price": null,
  "img": null,
  "pageSize": 6,
  "pageNum": numStudent
}

$('#home .userName').html('<img src="../images/avatar.jpg" class="layui-nav-img">' + getCookie('uname'));
$.ajax({
  type: "get",
  url: url + port + "/client/getClient",
  xhrFields: {
    withCredentials: true
  },
  success: function (res) {
    console.log(res)
    document.cookie = 'userid=' + res.id;
  }
});

layui.use(['element', 'layer', 'carousel', 'form', 'laydate'], function () {
  element = layui.element;
  layer = layui.layer;
  carousel = layui.carousel;
  form = layui.form;
  laydate = layui.laydate;

  // 图片轮播
  carousel.render({
    elem: '#test10',
    width: '778px',
    height: '440px',
    interval: 5000
  });

  //退出登录
  $('#logout').click(function () {
    $.ajax({
      type: "get",
      url: url + port + "/client/logout",
      xhrFields: {
        withCredentials: true
      },
      success: function (res) {
        layer.msg('退出成功');
        setTimeout(() => {
          setCookie('uname', "", -1);
          setCookie('userid', "", -1);
          window.location.href = '../html/userLogin.html'
        }, 2000);
        $(this).removeClass('layui-this');
      }
    });
  });
  $('#topMenu').children('ul').eq(0).children('li').eq(1).children('a').click();
});

//公告列表滚动
var timeId = setInterval(play, 3000);

function play() {
  $("#notict-list").animate({
      "marginTop": "-45px"
    },
    1000,
    function () {
      $(this).css({
        "marginTop": 0
      }).children("li:first").appendTo(this);
    });
}
$("#notict-list").hover(function () {
  clearInterval(timeId);
}, function () {
  timeId = setInterval(play, 3000);
});

// 获取公告列表
$.ajax({
  type: "post",
  url: url + port + "/notice/queryNotice",
  dataType: "json",
  contentType: "application/json;charset=UTF-8",
  xhrFields: {
    withCredentials: true
  },
  data: JSON.stringify({}),
  success: function (res) {
    console.log(res)
    var data = '';
    for (let i = 0; i < res.data.length; i++) {
      data += '<li value="' + res.data[i].id + '">' + res.data[i].title + '</li>'
    }
    $('#notict-list').append(data)
  }
});

// 公告详情
$('#notict-list').on('click', 'li', function () {
  $('.wrrap').show();
  $('.studentBox .title').attr('value', '');
  $('.studentBox #noticeDetail').text('');
  $('.studentBox .time').attr('value', '');
  $.ajax({
    type: "post",
    url: url + port + "/notice/queryNotice",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    xhrFields: {
      withCredentials: true
    },
    data: JSON.stringify({
      id: $(this).attr('value')
    }),
    success: function (res) {
      $('.studentBox .title').attr('value', res.data[0].title);
      $('.studentBox #noticeDetail').text(res.data[0].context);
      $('.studentBox .time').attr('value', res.data[0].createTime);
    }
  });
});

$('.closeStudentBox').click(function () {
  $('.wrrap').hide();
});

// 获取用户基本信息
$('.detailMsg').click(function () {
  $.ajax({
    type: "get",
    url: url + port + "/client/getClient",
    xhrFields: {
      withCredentials: true
    },
    success: function (res) {
      console.log(res)
      userId = res.id;
      layer.open({
        title: '基本信息',
        content: '<form class="layui-form" action="" lay-filter="example">' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">用户名</label>' +
          '<div class="layui-input-block">' +
          '<input id="detailUsername" type="text" name="username" lay-verify="title" autocomplete="off" class="layui-input">' +
          '</div>' +
          '</div>' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">密码</label>' +
          '<div class="layui-input-block">' +
          '<input id="detailPassword" type="password" name="password" lay-verify="title" autocomplete="new-password" class="layui-input">' +
          '</div>' +
          '</div>' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">姓名</label>' +
          '<div class="layui-input-block">' +
          '<input id="detailName" type="text" name="username" lay-verify="title" autocomplete="off" class="layui-input">' +
          '</div>' +
          '</div>' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">电话</label>' +
          '<div class="layui-input-block">' +
          '<input id="detailTel" type="text" name="username" lay-verify="title" autocomplete="off" class="layui-input">' +
          '</div>' +
          '</div>' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">地址</label>' +
          '<div class="layui-input-block">' +
          '<input id="detailAddress" type="text" name="username" lay-verify="title" autocomplete="off" class="layui-input">' +
          '</div>' +
          '</div>' +
          '</form>',
        btn: '保存',
        btnAlign: 'c',
        yes: function (index, layero) {
          $.ajax({
            type: "post",
            url: url + port + "/client/updateClientById",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            xhrFields: {
              withCredentials: true
            },
            data: JSON.stringify({
              "id": userId,
              "account": $('#detailUsername').val(),
              "pwd": $('#detailPassword').val(),
              "name": $('#detailName').val(),
              "tel": $('#detailTel').val(),
              "address": $('#detailAddress').val(),
            }),
            xhrFields: {
              withCredentials: true
            },
            success: function () {
              layer.msg('修改成功');
              setCookie('username', $('#detailUsername').val(), 30);
              $('#home .userName').html('<img src="../images/avatar.jpg" class="layui-nav-img">' + $('#detailUsername').val());
            }
          });
        },
        cancel: function () {
          //右上角关闭回调

          //return false 开启该代码可禁止点击该按钮关闭
        }
      });
      $('#detailUsername').val(res.account);
      $('#detailPassword').val(res.pwd);
      $('#detailName').val(res.name);
      $('#detailTel').val(res.tel);
      $('#detailAddress').val(res.address);
    }
  });
});

//获取菜品
getCategoryMenu()

function getCategoryMenu() {
  $.ajax({
    type: "post",
    url: url + port + "/dishType/queryDishType",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify(studentObj),
    success: function (res) {
      console.log(res)
      var data = '<span class="menu" value="null">全部</span>';
      for (let index = 0; index < res.data.length; index++) {
        data += '<span class="menu" value="' + res.data[index].id + '">' + res.data[index].name + '</span>'
      }
      $('.menuBox').empty().append(data);
    }
  });
}

// 切换菜品
$('.menuBox').on('click', '.menu', function () {
  $(this).addClass('selectMenu').siblings().removeClass('selectMenu');
  menuObj.dishTypeId = Number($(this).attr('value'));
  getMenu();
});
$('.menuBox span').eq(0).click();

// 菜单分页
function changePage(el) {
  layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage,
      layer = layui.layer;
    laypage.render({
      elem: el,
      count: tableLength,
      limit: 6,
      first: '首页',
      last: '尾页',
      prev: '<em>←</em>',
      next: '<em>→</em>',
      jump: function (obj, first) {
        menuObj.pageNum = obj.curr;
        if (!first) {
          getMenu(1);
        }
      }
    });
  });
}

// 获取菜单
getMenu()

function getMenu(first) {
  $.ajax({
    type: "post",
    url: url + port + "/dish/queryDish",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify(menuObj),
    success: function (res) {
      console.log(res)
      tableLength = res.total
      if (!first) {
        changePage(pagetion);
      }
      var data = '';
      for (let index = 0; index < res.data.length; index++) {
        data += '<a href="javascript:;" class="rstblock" value="' + res.data[index].id + '">\n' +
          '<div class="rstblock-logo">\n' +
          '<img\n' +
          'src="' + url + '' + port + '' + res.data[index].img + '"\n' +
          'alt="' + res.data[index].name + '" class="rstblock-logo-icon">\n' +
          '</div>\n' +
          '<div class="rstblock-content">\n' +
          '<div class="rstblock-title">' + res.data[index].name + '</div>\n' +
          '<div class="rstblock-activity">\n' +
          '<i class="menuBtn1" style="width:40px;background:#fff;color:#999999;border:1px solid;padding:0;" value="' + res.data[index].id + '">投诉</i>\n' +
          '<i class="menuBtn2" style="width:40px;background:#fff;color:#999999;border:1px solid;padding:0;" value="' + res.data[index].id + '">评价</i>\n' +
          '</div>\n' +
          '</div>\n' +
          '</a>'
      }
      $('.foodBox').empty().append(data);
    }
  });
}

// 获取评价
$('body').on('click', '.menuBtn2', function (e) {
  dishId = $(this).attr('value')
  e.stopPropagation();
  $('.dialog2').show();
  getMsg();
});

function getMsg() {
  $.ajax({
    type: "post",
    url: url + port + "/leaveMessage/queryLeaveMessage",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "dishId": dishId,
      "type": "1"
    }),
    success: function (res) {
      console.log(res)
      let data = '';
      for (let index = 0; index < res.data.length; index++) {
        data += '<div class="msgBox">' +
          '<div class="user_name">用户名：' + res.data[index].clientName + '</div>' +
          '<div class="user_msg">内容：' + res.data[index].message + '</div>' +
          '<div class="user_time">评价时间：' + res.data[index].leaveDate + '</div>' +
          '<div class="deleteMsg" value="' + res.data[index].id + '">删除评价</div>' +
          '</div>'
      }
      $('#message-detail').empty().append(data);
    }
  });
}

// 添加评价
$('#addMsg').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/leaveMessage/addLeaveMessage",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "clientId": Number(getCookie('userid')),
      "dishId": Number(dishId),
      "message": $('#addMsgContent').val(),
      "type": "1"
    }),
    success: function (res) {
      $('#addMsgContent').val('');
      getMsg();
    }
  });
});

//删除评价
$('body').on('click', '.deleteMsg', function () {
  $.ajax({
    type: "get",
    url: url + port + "/leaveMessage/delLeaveMessageById?id=" + $(this).attr('value'),
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    success: function () {
      layer.msg('删除成功');
      $('#message-detail').empty();
      getMsg();
    }
  });
});

// 获取投诉
$('body').on('click', '.menuBtn1', function (e) {
  dishId = $(this).attr('value')
  e.stopPropagation();
  $('.dialog3').show();
  getMsgType();
});

function getMsgType() {
  $.ajax({
    type: "post",
    url: url + port + "/leaveMessage/queryLeaveMessage",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "dishId": dishId,
      "type": "2"
    }),
    success: function (res) {
      console.log(res)
      let data = '';
      for (let index = 0; index < res.data.length; index++) {
        data += '<div class="msgBox">' +
          '<div class="user_name">用户名：' + res.data[index].clientName + '</div>' +
          '<div class="user_msg">内容：' + res.data[index].message + '</div>' +
          '<div class="user_time">投诉时间：' + res.data[index].leaveDate + '</div>' +
          '<div class="deleteMsg" value="' + res.data[index].id + '">删除投诉</div>' +
          '</div>'
      }
      $('.dialog3 #message-detail').empty().append(data);
    }
  });
}

// 添加投诉
$('.dialog3 #addMsg').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/leaveMessage/addLeaveMessage",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "clientId": Number(getCookie('userid')),
      "dishId": Number(dishId),
      "message": $('.dialog3 #addMsgContent').val(),
      "type": "2"
    }),
    success: function (res) {
      $('.dialog3 #addMsgContent').val('');
      getMsgType();
    }
  });
});

//删除投诉
$('body').on('click', '.dialog3 .deleteMsg', function () {
  $.ajax({
    type: "get",
    url: url + port + "/leaveMessage/delLeaveMessageById?id=" + $(this).attr('value'),
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    success: function () {
      layer.msg('删除成功');
      $('.dialog3 #message-detail').empty();
      getMsgType();
    }
  });
});

//获取菜单详情
$('body').on('click', '.rstblock', function () {
  $('.dialog').show();
  $.ajax({
    type: "post",
    url: url + port + "/dish/queryDish",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      id: $(this).attr('value')
    }),
    success: function (res) {
      console.log(res)
      var data = '';
      data += '<div class="menu-pic">\n' +
        '<img\n' +
        'src="' + url + '' + port + '' + res.data[0].img + '"\n' +
        'alt="">\n' +
        '</div>\n' +
        '<div class="menu-detail">\n' +
        '<div class="menu-detail-close">x</div>\n' +
        '<div class="menu-title">' + res.data[0].name + '</div>\n' +
        '<div class="menu-main">' + res.data[0].detail + '</div>\n' +
        '<div class="price-box">\n' +
        '<div class="menu-price">\n' +
        '<span class="yen">¥</span>\n' +
        '<span class="price">' + res.data[0].price + '</span>\n' +
        '</div>\n' +
        '<button class="menu-buy" id="menu-buy" value="' + res.data[0].id + '">加入购物车</button>\n' +
        '</div>' +
        '<form class="layui-form" style="margin-top: 20px;" lay-filter="example">' +
        '<div class="layui-form-item">' +
        '<label class="layui-form-label" style="width: 165px;color: #FFB800;">食品抽查记录查询:</label>' +
        '</div>' +
        '</form>' +
        '<div id="flowBox">' +
        '</div>' +
        '</div>'
      $('.menuDetail').empty().append(data);
      //食品抽查记录查询
      $.ajax({
        type: "post",
        url: url + port + "/spotCheck/querySpotCheck",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
          "dishId": Number($('#menu-buy').attr('value')),
        }),
        success: function (res) {
          console.log(res)
          if (res.data.length == 0) {
            layer.msg('商品在此时间段内暂无抽查记录！')
          } else {
            var data = '';
            for (let index = 0; index < res.data.length; index++) {
              data += '<div class="flowDetail">\n' +
                '<span class="flowName">抽查人员：' + res.data[index].empName + '</span>\n' +
                '<span>|</span>\n' +
                '<span class="flowAddress">抽查结果：' + res.data[index].result + '</span>\n' +
                '<span>|</span>\n' +
                '<span class="flowDate">时间：' + res.data[index].checkTime + '</span>\n' +
                '</div>\n'
            }
            $('#flowBox').empty().append(data);
          }
        }
      });
    }
  });
});

// 添加购物车
$('body').on('click', '#menu-buy', function () {
  $.ajax({
    type: "get",
    url: url + port + "/cart/addItem",
    xhrFields: {
      withCredentials: true
    },
    data: {
      "id": $(this).attr('value'),
      "quantity": 1
    },
    success: function (res) {
      layer.msg('添加成功！');
    }
  });
});

$('body').on('click', '.dialog', function () {
  $(this).hide();
});

$('body').on('click', '.menuDetail', function () {
  event.stopPropagation();
  return false;
});

$('body').on('click', '.menu-detail-close', function () {
  $('.dialog').hide();
});

$('body').on('click', '.dialog2', function () {
  $(this).hide();
});

$('body').on('click', '.messageDetail', function () {
  event.stopPropagation();
  return false;
});

$('body').on('click', '.message-detail-close', function () {
  $('.dialog2').hide();
});

$('body').on('click', '.dialog3', function () {
  $(this).hide();
});

$('body').on('click', '.dialog3 .messageDetail', function () {
  event.stopPropagation();
  return false;
});

$('body').on('click', '.dialog3 .message-detail-close', function () {
  $('.dialog3').hide();
});
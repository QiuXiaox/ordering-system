var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let studentObj = {
  "clientId": null,
  "createtime": null,
  "pageSize": 5,
  "pageNum": numStudent
}

layui.use('laydate', function () {
  laydate = layui.laydate;
  laydate.render({
    elem: '#selectTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm:ss',
    done: function (val, data) {
      studentObj.createtime = val;
      $('#studentTable').empty();
      getStudentData();
    }
  });
});

// 进入页面获取表格
getStudentData();

function getStudentData(first) {
  $.ajax({
    type: "post",
    url: url + port + "/order/queryOrder",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify(studentObj),
    success: function (res) {
      console.log(res)
      tableLength = res.total
      if (!first) {
        changePage(pagetion);
      }
      var data = '';
      for (let index = 0; index < res.data.length; index++) {
        data += '<tr>\n' +
          '<td>\n' +
          '<div>' + ((index + 1) + (studentObj.pageNum - 1) * 5) + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].clientName + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].clientAddress + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].createtime + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].totalPrice + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div class="operate">\n' +
          '<span class="checkDetail" value="' + res.data[index].id + '">查看详情</span>\n' +
          '</div>\n' +
          '</td>\n' +
          '</tr>\n'
      }
      $('#studentTable').empty().append(data);
    }
  });
}

// 改变分页函数
function changePage(el) {
  layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage,
      layer = layui.layer;
    laypage.render({
      elem: el,
      count: tableLength,
      limit: 5,
      first: '首页',
      last: '尾页',
      prev: '<em>←</em>',
      next: '<em>→</em>',
      jump: function (obj, first) {
        studentObj.pageNum = obj.curr;
        if (!first) {
          getStudentData(1);
        }
      }
    })
  });
}

// 查看公告详细信息
$('body').on('click', '.checkDetail', function () {
  getDetail($(this).attr('value'));
  $('.wrrap').show();
});

$('.closeStudentBox').click(function () {
  $('.wrrap').hide();
});

// 输入框赋值
function getDetail(e) {
  $.ajax({
    type: "post",
    url: url + port + "/orderItem/queryOrderItem",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      orderId: e
    }),
    success: function (res) {
      let data = '';
      for (let i = 0; i < res.data.length; i++) {
        data += '<div class="layui-form-item">' +
          '<label class="layui-form-label">菜名:</label>' +
          '<div class="layui-input-block">' +
          '<input class="layui-input dishName" value="' + res.data[i].dishName + '" type="text" name="username" lay-verify="title" autocomplete="off" readonly>' +
          '</div>' +
          '</div>' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">数量:</label>' +
          '<div class="layui-input-block">' +
          '<input class="layui-input num" value="' + res.data[i].quantity + '" type="text" name="username" lay-verify="title" autocomplete="off" readonly>' +
          '</div>' +
          '</div>' +
          '<div class="layui-form-item">' +
          '<label class="layui-form-label">单价:</label>' +
          '<div class="layui-input-block">' +
          '<input class="layui-input price" value="' + res.data[i].price + '" type="text" name="username" lay-verify="title" autocomplete="off" readonly>' +
          '</div>' +
          '</div>'
      }
      $('#detailBox').empty().append(data);
    }
  });
}
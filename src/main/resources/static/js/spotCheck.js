var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let studentObj = {
  "empId": null,
  "dishId": null,
  "result": null,
  "pageSize": 5,
  "pageNum": numStudent
}

layui.use('laydate', function () {
  laydate = layui.laydate;
});

// 下拉框函数
$(document).on('click', '.f-push>p', function () {
  event.stopPropagation();
  if ($(this).parent().hasClass('marker')) {
    return false;
  }
  $(this).siblings('div').toggle();
  $(this).parent().addClass('marker');
}).on('click', '.f-push>div>div', function () {
  event.stopPropagation();
  $(this).parent().siblings('p').attr('value', $(this).attr('value')).html($(this).text());
  $(this).parent().hide();
  $(this).parent().parent().removeClass('marker');
}).click(function (event) {
  var _con = $('.f-push');
  if (!_con.is(event.target) && _con.has(event.target).length === 0) {
    $('.f-push>div').hide();
    $('.f-push').removeClass('marker');
  }
});

// 获取菜品下拉框
$.ajax({
  type: "post",
  url: url + port + "/dish/queryDish",
  dataType: "json",
  contentType: "application/json;charset=UTF-8",
  data: JSON.stringify({}),
  success: function (res) {
    var data = '';
    for (let index = 0; index < res.data.length; index++) {
      data += '<div value="' + res.data[index].id + '">' + res.data[index].name + '</div>\n'
    }
    $('.dish').append(data);
  }
});

// 获取人员下拉框
$.ajax({
  type: "post",
  url: url + port + "/employee/queryEmployee",
  dataType: "json",
  contentType: "application/json;charset=UTF-8",
  data: JSON.stringify({}),
  success: function (res) {
    var data = '';
    for (let index = 0; index < res.data.length; index++) {
      data += '<div value="' + res.data[index].id + '">' + res.data[index].name + '</div>\n'
    }
    $('.empList').append(data);
  }
});

// 进入页面获取表格
getStudentData();

function getStudentData(first) {
  $.ajax({
    type: "post",
    url: url + port + "/spotCheck/querySpotCheck",
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
          '<div>' + res.data[index].dishName + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].empName + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].checkTime + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].result + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div class="operate">\n' +
          '<span class="deleteStudent" value="' + res.data[index].id + '">删除</span>\n' +
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

// 删除公告
$('body').on('click', '.deleteStudent', function () {
  $.ajax({
    type: "get",
    url: url + port + "/spotCheck/delSpotCheckById?id=" + $(this).attr('value'),
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    success: function () {
      layer.msg('删除成功');
      $('#studentTable').empty();
      getStudentData(1);
    }
  });
});

// 添加公告
$('.addStudent').click(function () {
  $('.wrrap3').show();
});
$('.addMessage').click(function () {
  console.log($('#result').text())
  $.ajax({
    type: "post",
    url: url + port + "/spotCheck/addSpotCheck",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "empId": $('#empName').attr('value'),
      "dishId": $('#dishName').attr('value'),
      "result": $('#result').text()
    }),
    success: function (res) {
      layer.msg('添加成功');
      $('#result').text('');
      $('#dishName').attr('value', '');
      $('#empName').attr('value', '');
      $('#result').attr('value', '');
      $('.wrrap3').hide();
      $('#studentTable').empty();
      getStudentData();
    }
  });
})

$('.closeStudentBox3').click(function () {
  $('#result').text('');
  $('#dishName').attr('value', '');
  $('#empName').attr('value', '');
  $('#result').attr('value', '');
  $('.wrrap3').hide();
});
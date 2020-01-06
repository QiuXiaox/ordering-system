var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let studentObj = {
  "pageSize": 5,
  "pageNum": numStudent
}

// 进入页面获取表格
getStudentData();

function getStudentData(first) {
  $.ajax({
    type: "post",
    url: url + port + "/leaveMessage/queryLeaveMessage",
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
          '<div>' + res.data[index].clientName + '</div>\n' +
          '</td>\n' +
          '<td style="width:200px;">\n' +
          '<div style="width:200px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">' + res.data[index].message + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].leaveDate + '</div>\n' +
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
    url: url + port + "/leaveMessage/queryLeaveMessage",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      id: e
    }),
    success: function (res) {
      $('.studentBox .dishName').val(res.data[0].dishName);
      $('.studentBox .clientName').val(res.data[0].clientName);
      $('.studentBox .message').val(res.data[0].message);
      $('.studentBox .leaveDate').val(res.data[0].leaveDate);
    }
  });
}
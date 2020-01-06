var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let studentObj = {
  "dishId": null,
  "startTime": null,
  "endTime": null,
  "pageSize": 5,
  "pageNum": numStudent
}

layui.use('laydate', function () {
  laydate = layui.laydate;
  laydate.render({
    elem: '#selectTime',
    type: 'datetime',
    range: '到',
    format: 'yyyy-MM-dd HH:mm:ss',
    done: function(val, data) {
      var startTime = val.split('到')[0];
      var endTime = val.split('到')[1];
      studentObj.startTime = startTime;
      studentObj.endTime = endTime;
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
    url: url + port + "/order/dishFlow",
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
          '<td>\n' +
          '<div>' + res.data[index].address + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].createtime + '</div>\n' +
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
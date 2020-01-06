var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let studentObj = {
  "title": '',
  "content": '',
  "pageSize": 5,
  "pageNum": numStudent
}

layui.use('laydate', function () {
  laydate = layui.laydate;
});

function changeDate(id) {
  laydate.render({
    elem: id,
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm:ss'
  });
}

// 进入页面获取表格
getStudentData();

function getStudentData(first) {
  $.ajax({
    type: "post",
    url: url + port + "/notice/queryNotice",
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
          '<div>' + res.data[index].title + '</div>\n' +
          '</td>\n' +
          '<td style="width:200px;">\n' +
          '<div style="width:200px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">' + res.data[index].context + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].createTime + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div class="operate">\n' +
          '<span class="checkDetail" value="' + res.data[index].id + '">查看详情</span>\n' +
          '<span class="editStudent" value="' + res.data[index].id + '">编辑</span>\n' +
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
    url: url + port + "/notice/delNoticeById?id=" + $(this).attr('value'),
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    success: function () {
      layer.msg('删除成功');
      $('#studentTable').empty();
      getStudentData(1);
    }
  });
});

// 查看公告详细信息
$('body').on('click', '.checkDetail', function () {
  getDetail($(this).attr('value'));
  $('.wrrap').show();
});

$('.closeStudentBox').click(function () {
  $('.wrrap').hide();
});

// 修改公告信息
$('body').on('click', '.editStudent', function () {
  status = false;
  editId = $(this).attr('value');
  getDetail($(this).attr('value'));
  $('.wrrap2').show();
});

$('.submitMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/notice/updateNoticeById",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "id": editId,
      "title": $('.studentBox2 .tittle').val(),
      "context": $('.studentBox2 .content').val()
    }),
    success: function (res) {
      layer.msg('修改成功');
      $('#studentTable').empty();
      getStudentData(1);
      $('.wrrap2').hide();
    }
  });
});

$('.closeStudentBox2').click(function () {
  $('.wrrap2').hide();
});

// 输入框赋值
function getDetail(e) {
  $.ajax({
    type: "post",
    url: url + port + "/notice/queryNotice",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      id: e
    }),
    success: function (res) {
      $('.studentBox .tittle,.studentBox2 .tittle').val(res.data[0].title);
      $('.studentBox .content,.studentBox2 .content').val(res.data[0].context);
    }
  });
}

// 添加公告
$('.addStudent').click(function () {
  $('.wrrap3').show();
});
$('.addMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/notice/addNotice",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "empId": getCookie('id'),
      "title": $('.studentBox3 .tittle').val(),
      "context": $('.studentBox3 .content').val()
    }),
    success: function (res) {
      layer.msg('添加成功');
      $('.studentBox3 .tittle').val('');
      $('.studentBox3 .content').val('');
      $('.wrrap3').hide();
      $('#studentTable').empty();
      getStudentData();
    }
  });
})

$('.closeStudentBox3').click(function () {
  $('.studentBox3 .tittle').val('');
  $('.studentBox3 .content').val('');
  $('.wrrap3').hide();
});
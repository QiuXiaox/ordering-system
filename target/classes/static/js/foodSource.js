var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let healthPic;
let studentObj = {
  "name": null,
  "address": null,
  "stock": null,
  "expireTime": null,
  "pageSize": 5,
  "pageNum": numStudent
}

layui.use(['laydate', 'layer'], function () {
  laydate = layui.laydate;
  layer = layui.layer;

  // 进入页面获取表格
  getStudentData();

  function getStudentData(first) {
    $.ajax({
      type: "post",
      url: url + port + "/food/queryFood",
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
            '<div>' + res.data[index].name + '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div>' + res.data[index].address + '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div>' + res.data[index].stock + '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div>' + res.data[index].expireTime + '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<img src="' + url + '' + port + '' + res.data[index].reportPic + '" />\n' +
            '</td>\n' +
            '<td>\n' +
            '<div>' + res.data[index].empName + '</div>\n' +
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

  // 查看详细信息
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
      url: url + port + "/food/queryFood",
      dataType: "json",
      contentType: "application/json;charset=UTF-8",
      data: JSON.stringify({
        id: e
      }),
      success: function (res) {
        $('.studentBox .name').val(res.data[0].name);
        $('.studentBox .address').val(res.data[0].address);
        $('.studentBox .stock').val(res.data[0].stock);
        $('.studentBox .healthPic').attr('src', url + port + res.data[0].reportPic);
        $('.studentBox .expireTime').val(res.data[0].expireTime);
        $.ajax({
          type: "post",
          url: url + port + "/employee/queryEmployee",
          dataType: "json",
          contentType: "application/json;charset=UTF-8",
          data: JSON.stringify({
            id: res.data[0].empId
          }),
          success: function (e) {
            console.log(e)
            $('.studentBox .empName').attr('value', e.data[0].name);
          }
        });
      }
    });
  }
})
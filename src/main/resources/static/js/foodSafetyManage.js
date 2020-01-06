var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let healthPic;
let addHealthPic;
let studentObj = {
  "name": null,
  "address": null,
  "stock": null,
  "expireTime": null,
  "pageSize": 5,
  "pageNum": numStudent
}

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
    $('.empBox2').append(data);
    $('.empBox3').append(data);
  }
});

layui.use(['laydate', 'layer', 'upload'], function () {
  laydate = layui.laydate;
  layer = layui.layer;
  upload = layui.upload;

  //上传健康证
  upload.render({
    elem: '#test8',
    url: url + port + '/file/uploadFile',
    auto: false,
    multiple: true,
    bindAction: '#test9',
    // data: {
    //   file: '' 
    // },
    choose: function (obj) {
      //将每次选择的文件追加到文件队列
      var files = obj.pushFile();

      //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
      obj.preview(function (index, file, result) {
        console.log(index); //得到文件索引
        console.log(file); //得到文件对象
        $('.studentBox2 .healthPic').attr('src', '../images/' + file.name);
        console.log(result); //得到文件base64编码，比如图片

        //obj.resetFile(index, file, '123.jpg'); //重命名文件名，layui 2.3.0 开始新增

        //这里还可以做一些 append 文件列表 DOM 的操作

        //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
        //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
      });
    },
    done: function (res) {
      console.log(res)
      healthPic = res.data;
      layui.use('layer', function () {
        var layer = layui.layer;
        layer.msg('上传成功！');
      });
    }
  });
});

function changeDate(id) {
  laydate.render({
    elem: id,
    format: 'yyyy-MM-dd'
  });
}

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
        layui.use('layer', function () {
          layer = layui.layer;

          function getBeforeDate(n) {
            var d = new Date();
            var year = d.getFullYear();
            var mon = d.getMonth() + 1;
            var day = d.getDate();
            if (day <= n) {
              if (mon > 1) {
                mon = mon - 1;
              } else {
                year = year - 1;
                mon = 12;
              }
            }
            d.setDate(d.getDate() - n);
            year = d.getFullYear();
            mon = d.getMonth() + 1;
            day = d.getDate();
            s = year + "-" + (mon < 10 ? ('0' + mon) : mon) + "-" + (day < 10 ? ('0' + day) : day);
            return s;
          }

          function DateDiff(sDate, eDate) { //sDate和eDate是yyyy-MM-dd格式
            var date1 = new Date(sDate);
            var date2 = new Date(eDate);
            var date3 = date2.getTime() - date1.getTime();
            var days = Math.floor(date3 / (24 * 3600 * 1000));
            return days;
          }

          if (res.data[index].expireTime < getBeforeDate(0)) {
            layer.open({
              type: 1,
              title: '检验报告过期提醒',
              btn: '确定',
              btnAlign: 'c',
              area: '300px',
              content: '<div style="padding: 8px;"><span style="color: #FF5722;">'+res.data[index].name+'</span>检验报告已过期，请及时更换检验报告</div>'
            });
          }

          if (res.data[index].expireTime >= getBeforeDate(0) && res.data[index].expireTime <= getBeforeDate(-7)) {
            layer.open({
              type: 1,
              title: '检验报告过期提醒',
              btn: '确定',
              btnAlign: 'c',
              area: '300px',
              content: '<div style="padding: 8px;"><span style="color: #FFB800;">'+res.data[index].name+'</span>检验报告于' + DateDiff(getBeforeDate(0), res.data[index].expireTime) + '天后过期</div>'
            });
          }
        });
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
    url: url + port + "/food/delFoodById",
    data: {
      id: $(this).attr('value')
    },
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
  changeDate(expireTime);
});

$('.submitMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/food/updateFoodById",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "id": editId,
      "name": $('.studentBox2 .name').val(),
      "address": $('.studentBox2 .address').val(),
      "stock": $('.studentBox2 .stock').val(),
      "expireTime": $('.studentBox2 .expireTime').val(),
      "reportPic": healthPic,
      "empId": $('.studentBox2 #empName2').attr('value'),
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
    url: url + port + "/food/queryFood",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      id: e
    }),
    success: function (res) {
      $('.studentBox .name,.studentBox2 .name').val(res.data[0].name);
      $('.studentBox .address,.studentBox2 .address').val(res.data[0].address);
      $('.studentBox .stock,.studentBox2 .stock').val(res.data[0].stock);
      $('.studentBox .empName, .studentBox2 #empName2').attr('value', res.data[0].empId);
      $('.studentBox .healthPic,.studentBox2 .healthPic').attr('src', url + port + res.data[0].reportPic);
      $('.studentBox .expireTime,.studentBox2 .expireTime').val(res.data[0].expireTime);
      for (let i = 0; i < $('.empBox2').children('div').length; i++) {
        if ($('#empName2').attr('value') == $('.empBox2').children('div').eq(i).attr('value')) {
          // $('#empName2').attr('value', $('.empBox2').children('div').eq(i).attr('value'));
          $('#empName2').text($('.empBox2').children('div').eq(i).text())
        }
        if ($('.studentBox .empName').attr('value') == $('.empBox2').children('div').eq(i).attr('value')) {
          $('.studentBox .empName').attr('value', $('.empBox2').children('div').eq(i).text());
        }
      }
    }
  });
}

// 添加公告
$('.addStudent').click(function () {
  $('.wrrap3').show();
  changeDate(expireTime2);
  layui.use('upload', function () {
    upload = layui.upload;

    //上传健康证
    upload.render({
      elem: '#test10',
      url: url + port + '/file/uploadFile',
      auto: false,
      multiple: true,
      bindAction: '#test11',
      choose: function (obj) {
        var files = obj.pushFile();
        obj.preview(function (index, file, result) {
          $('.studentBox3 .healthPic').attr('src', '../images/' + file.name);
        });
      },
      done: function (res) {
        addHealthPic = res.data;
      }
    });
  });
});
$('.addMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/food/addFood",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "empId": $('#empName3').attr('value'),
      "name": $('.studentBox3 .name').val(),
      "address": $('.studentBox3 .address').val(),
      "stock": $('.studentBox3 .stock').val(),
      "expireTime": $('.studentBox3 .expireTime').val(),
      "reportPic": healthPic,
    }),
    success: function (res) {
      layer.msg('添加成功');
      $('.studentBox3 .name').val('');
      $('.studentBox3 .address').val('');
      $('.studentBox3 .stock').val('');
      $('.studentBox3 #empName3').attr('value', '');
      $('.studentBox3 #empName3').text('入库人员');
      $('.studentBox3 .healthPic').attr('src', '');
      $('.studentBox3 #expireTime2').val('');
      addHealthPic = ''
      $('.wrrap3').hide();
      $('#studentTable').empty();
      getStudentData();
    }
  });
})

$('.closeStudentBox3').click(function () {
  $('.studentBox3 .name').val('');
  $('.studentBox3 .address').val('');
  $('.studentBox3 .stock').val('');
  $('.studentBox3 #empName3').attr('value', '');
  $('.studentBox3 #empName3').text('入库人员');
  $('.studentBox3 .healthPic').attr('src', '');
  $('.studentBox3 #expireTime2').val('');
  addHealthPic = ''
  $('.wrrap3').hide();
});
var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let healthPic;
let addHealthPic;
let studentObj = {
  "account": null,
  "pwd": null,
  "name": null,
  "healthPic": null,
  "healthExpire": null,
  "pageSize": 5,
  "pageNum": numStudent
}

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
    url: url + port + "/employee/queryEmployee",
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

          if (res.data[index].healthExpire < getBeforeDate(0)) {
            layer.open({
              type: 1,
              title: '健康证过期提醒',
              btn: '确定',
              btnAlign: 'c',
              area: '300px',
              content: '<div style="padding: 8px;"><span style="color: #FF5722;">' + res.data[index].name + '</span>健康证已过期，请及时更换健康证</div>'
            });
          }

          if (res.data[index].healthExpire >= getBeforeDate(0) && res.data[index].healthExpire <= getBeforeDate(-7)) {
            layer.open({
              type: 1,
              title: '健康证过期提醒',
              btn: '确定',
              btnAlign: 'c',
              area: '300px',
              content: '<div style="padding: 8px;"><span style="color: #FFB800;">' + res.data[index].name + '</span>健康证于' + DateDiff(getBeforeDate(0), res.data[index].healthExpire) + '天后过期</div>'
            });
          }
        });
        data += '<tr>\n' +
          '<td>\n' +
          '<div>' + ((index + 1) + (studentObj.pageNum - 1) * 5) + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].account + '</div>\n' +
          '</td>\n' +
          '<td style="width:200px;">\n' +
          '<div>' + res.data[index].name + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].tel + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<img src="' + url + '' + port + '' + res.data[index].healthPic + '" />\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].healthExpire + '</div>\n' +
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
    url: url + port + "/employee/delEmployeeById",
    // dataType: "json",
    // contentType: "application/json;charset=UTF-8",
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
  changeDate(healthExpire)
});

$('.submitMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/employee/updateEmployeeById",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "id": editId,
      "account": $('.studentBox2 .account').val(),
      "pwd": $('.studentBox2 .pwd').val(),
      "name": $('.studentBox2 .name').val(),
      "healthPic": healthPic,
      "tel": $('.studentBox2 .tel').val(),
      "healthExpire": $('.studentBox2 .healthExpire').val(),
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
    url: url + port + "/employee/queryEmployee",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      id: e
    }),
    success: function (res) {
      $('.studentBox .account,.studentBox2 .account').val(res.data[0].account);
      $('.studentBox .pwd,.studentBox2 .pwd').val(res.data[0].pwd);
      $('.studentBox .name,.studentBox2 .name').val(res.data[0].name);
      $('.studentBox .tel,.studentBox2 .tel').val(res.data[0].tel);
      $('.studentBox .healthPic,.studentBox2 .healthPic').attr('src', url + port + res.data[0].healthPic);
      $('.studentBox .healthExpire,.studentBox2 .healthExpire').val(res.data[0].healthExpire);
    }
  });
}

// 添加公告
$('.addStudent').click(function () {
  $('.wrrap3').show();
  changeDate(addHealthExpire)
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
        addHealthPic = res.data
      }
    });
  });
});
$('.addMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/employee/addEmployee",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "empId": getCookie('id'),
      "account": $('.studentBox3 .account').val(),
      "pwd": $('.studentBox3 .pwd').val(),
      "name": $('.studentBox3 .name').val(),
      "healthPic": addHealthPic,
      "tel": $('.studentBox3 .tel').val(),
      "healthExpire": $('.studentBox3 .healthExpire').val(),
    }),
    success: function (res) {
      layer.msg('添加成功');
      $('.studentBox3 .account').val('');
      $('.studentBox3 .pwd').val('');
      $('.studentBox3 .name').val('');
      $('.studentBox3 .tel').val('');
      $('.studentBox3 .healthExpire').val('');
      $('.studentBox2 .healthPic').attr('src', '');
      addHealthPic = ''
      $('.wrrap3').hide();
      $('#studentTable').empty();
      getStudentData();
    }
  });
})

$('.closeStudentBox3').click(function () {
  $('.studentBox3 .account').val('');
  $('.studentBox3 .pwd').val('');
  $('.studentBox3 .name').val('');
  $('.studentBox3 .tel').val('');
  $('.studentBox3 .healthExpire').val('');
  $('.studentBox2 .healthPic').attr('src', '');
  addHealthPic = ''
  $('.wrrap3').hide();
});
var numStudent = 1; //当前页
let tableLength; // 分页长度
let editId;
let status = true;
let healthPic;
let addHealthPic;
let studentObj = {
  "dishTypeId": null,
  "detail": null,
  "name": null,
  "price": null,
  "img": null,
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

// 获取菜品下拉框
$.ajax({
  type: "post",
  url: url + port + "/dishType/queryDishType",
  dataType: "json",
  contentType: "application/json;charset=UTF-8",
  data: JSON.stringify({}),
  success: function (res) {
    var data = '';
    for (let index = 0; index < res.data.length; index++) {
      data += '<div value="' + res.data[index].id + '">' + res.data[index].name + '</div>\n'
    }
    $('.dishType').append(data);
    $('.dishType2').append(data);
    $('.dishType3').append(data);
  }
});

layui.use(['layer', 'upload'], function () {
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

// 进入页面获取表格
getStudentData();

function getStudentData(first) {
  $.ajax({
    type: "post",
    url: url + port + "/dish/queryDish",
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
          '<td style="width:200px;">\n' +
          '<div style="width:200px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">' + res.data[index].detail + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].price + '</div>\n' +
          '</td>\n' +
          '<td>\n' +
          '<img src="'+ url +''+ port +'' + res.data[index].img + '" />\n' +
          '</td>\n' +
          '<td>\n' +
          '<div>' + res.data[index].dishTypeName + '</div>\n' +
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
    url: url + port + "/dish/delDishById",
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
});

$('.submitMessage').click(function () {
  $.ajax({
    type: "post",
    url: url + port + "/dish/updateDishById",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "id": editId,
      "name": $('.studentBox2 .name').val(),
      "detail": $('.studentBox2 .detail').val(),
      "price": $('.studentBox2 .price').val(),
      "img": healthPic,
      "dishTypeId": $('.studentBox2 #dishTypeName2').attr('value'),
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
    url: url + port + "/dish/queryDish",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      id: e
    }),
    success: function (res) {
      $('.studentBox .name,.studentBox2 .name').val(res.data[0].name);
      $('.studentBox .detail,.studentBox2 .detail').val(res.data[0].detail);
      $('.studentBox .price,.studentBox2 .price').val(res.data[0].price);
      $('.studentBox .dishTypeName, .studentBox2 #dishTypeName2').attr('value', res.data[0].dishTypeId);
      $('.studentBox .healthPic,.studentBox2 .healthPic').attr('src', url + port + res.data[0].healthPic);
      for (let i = 0; i < $('.dishType2').children('div').length; i++) {
        if ($('#dishTypeName2').attr('value') == $('.dishType2').children('div').eq(i).attr('value')) {
          // $('#dishTypeName2').attr('value', $('.dishType2').children('div').eq(i).attr('value'));
          $('#dishTypeName2').text($('.dishType2').children('div').eq(i).text())
        }
      }
      for (let i = 0; i < $('.dishType').children('div').length; i++) {
        if ($('.studentBox .dishTypeName').attr('value') == $('.dishType').children('div').eq(i).attr('value')) {
          $('.studentBox .dishTypeName').attr('value', $('.dishType').children('div').eq(i).text());
        }
      }
    }
  });
}

// 添加公告
$('.addStudent').click(function () {
  $('.wrrap3').show();
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
    url: url + port + "/dish/addDish",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    data: JSON.stringify({
      "empId": getCookie('id'),
      "name": $('.studentBox3 .name').val(),
      "detail": $('.studentBox3 .detail').val(),
      "price": $('.studentBox3 .price').val(),
      "img": addHealthPic,
      "dishTypeId": $('.studentBox3 #dishTypeName3').attr('value'),
    }),
    success: function (res) {
      layer.msg('添加成功');
      $('.studentBox3 .name').val('');
      $('.studentBox3 .detail').val('');
      $('.studentBox3 .price').val('');
      $('.studentBox3 #dishTypeName3').attr('value', '');
      $('.studentBox3 #dishTypeName3').text('菜品列表')
      addHealthPic = ''
      $('.wrrap3').hide();
      $('#studentTable').empty();
      getStudentData();
    }
  });
})

$('.closeStudentBox3').click(function () {
  $('.studentBox3 .tittle').val('');
  $('.studentBox3 .content').val('');
  $('.studentBox3 .price').val('');
  $('.studentBox3 #dishTypeName3').attr('value', '');
  $('.studentBox3 #dishTypeName3').text('菜品列表')
  addHealthPic = ''
  $('.wrrap3').hide();
});

// 选取菜品更新表格
$('#dishTypeName').bind('DOMNodeInserted', function () {
  studentObj.dishTypeId = $('#dishTypeName').attr('value');
  getStudentData();
});
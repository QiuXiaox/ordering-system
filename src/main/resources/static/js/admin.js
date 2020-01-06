let userId;
let healthPic;

$('#home .userName').html('<img src="../images/avatar.jpg" class="layui-nav-img">' + getCookie('username'));

layui.use(['element', 'layer', 'upload'], function () {
  element = layui.element;
  layer = layui.layer;
  upload = layui.upload;
  
  //切换导航栏
  element.on('nav(test)', function (elem) {
    var event = event || window.event;
    if (event.preventDefault) {
      event.preventDefault();
    } else {
      event.returnValue = false;
    }
    $('.contentShow').hide();
    if (elem[0].href.split('html/')[1] != '' && elem[0].href.split('html/')[1] != 'null' && elem[0].href.split('html/')[1] != null) {
      $('#changeIframes').attr('src', elem[0].href);
    }
    $('#iframeBox').css('height', '100%');
  });

  //退出登录
  $('#logout').click(function () {
    $.ajax({
      type: "get",
      url: url + port + "/employee/logout",
      xhrFields: {
        withCredentials: true
      },
      success: function (res) {
        layer.msg('退出成功');
        setTimeout(() => {
          setCookie('username', "", -1);
          setCookie('id', "", -1);
          window.location.href = '../html/adminLogin.html'
        }, 2000);
        $(this).removeClass('layui-this');
      }
    });
  });
  $('#leftMenu').children('ul').eq(0).children('li').eq(0).children('a').click();
});

// 展开收缩导航栏
$('#sideNav-hide').click(function () {
  if ($(this).is('.nav-shrink')) {
    $(this).removeClass('nav-shrink');
    $('.layui-side').animate({
      width: '220px'
    }, 1000);
    $('.layui-layout-left,.layui-body,.layui-layout-admin').css('left', '220px');
    $(this).children().first().html('<i class="layui-icon layui-icon-shrink-right"></i>');
  } else {
    $(this).addClass('nav-shrink');
    $('.layui-side').animate({
      width: '54px'
    }, 1000);
    $('.layui-layout-left,.layui-body,.layui-layout-admin').css('left', '54px');
    $(this).children().first().html('<i class="layui-icon layui-icon-spread-left"></i>');
  }
});

$.ajax({
  type: "get",
  url: url + port + "/employee/getEmployee",
  xhrFields: {
    withCredentials: true
  },
  success: function (res) {
    document.cookie = 'id=' + res.id;
  }
});

console.log(getCookie('id'))

// 修改基本资料
$('.detailMsg').click(function () {
  $.ajax({
    type: "get",
    url: url + port + "/employee/getEmployee",
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
          '<label class="layui-form-label">附件</label>' +
          '<div class="layui-input-block">' +
          '<div class="layui-upload">' +
          '<button type="button" class="layui-btn layui-btn-normal" id="test8">+</button>' +
          '<button type="button" class="layui-btn uploadHeathPic" id="test9">开始上传</button>' +
          '</div>' +
          '</div>' +
          '</div>' +
          '</form>',
        btn: '保存',
        btnAlign: 'c',
        yes: function (index, layero) {
          $.ajax({
            type: "post",
            url: url + port + "/employee/updateEmployeeById",
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
              'healthPic': healthPic
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
            console.log(result); //得到文件base64编码，比如图片

            //obj.resetFile(index, file, '123.jpg'); //重命名文件名，layui 2.3.0 开始新增

            //这里还可以做一些 append 文件列表 DOM 的操作

            //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
            //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
          });
        },
        done: function (res) {
          healthPic = res.data;
        }
      });
      $('#detailUsername').val(res.account);
      $('#detailPassword').val(res.pwd);
      $('#detailName').val(res.name);
      $('#detailTel').val(res.tel);
    }
  });
});
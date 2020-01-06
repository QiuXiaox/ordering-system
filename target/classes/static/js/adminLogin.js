const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');


signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});


signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

layui.use('layer', function () {
  layer = layui.layer;
});

$(document).keydown(function (event) {
  if (event.keyCode == 13) {
    $('#loginToHome').click();
  }
});

$('#loginToHome').click(function () {
  if ($('#userName').val() == '' || $('#userPassword').val() == '') {
    layer.msg('账号密码不能为空');
  } else {
    $.ajax({
      type: "post",
      url: url + port + "/employee/login",
      dataType: "json",
      contentType: "application/json;charset=UTF-8",
      xhrFields: {
        withCredentials: true
      },
      data: JSON.stringify({
        "account": $('#adminName').val(),
        "pwd": $('#adminPassword').val()
      }),
      success: function (res) {
        if (res.result == 'SUCCESS') {
          layer.msg('登陆成功');
          document.cookie = 'username=' + $('#adminName').val();
          window.location.href = '../html/admin.html';
          window.event.returnValue=false;
        } else {
          layer.msg(res.msg);
        }
      },
      error: function (res) {
        layer.msg('错误请重试');
      }
    });
  }
});

$('#loginToUser').click(function () {
  window.location.href = '../html/userLogin.html';
  window.event.returnValue=false;
});

$('#checkAdminPassword').blur(function () {
  if ($('#checkAdminPassword').val() != $('#regAdminPassword').val()) {
    layer.msg('两次输入不一致');
  }
});

$('.registerToServe').click(function () {
  if ($('#regAdminName').val() == '' || $('#regAdminPassword').val() == '' || $('#checkAdminPassword').val() == '') {
    layer.msg('账号密码不能为空');
  } else {
    $.ajax({
      type: "post",
      url: url + port + "/employee/addEmployee",
      dataType: "json",
      contentType: "application/json;charset=UTF-8",
      xhrFields: {
        withCredentials: true
      },
      data: JSON.stringify({
        "account": $('#regAdminName').val(),
        "pwd": $('#regAdminPassword').val(),
        "name": $('#regName').val(),
        "tel": $('#regAdminTel').val()
      }),
      success: function (res) {
        if (res.result == 'SUCCESS') {
          layer.msg('注册成功');
        } else {
          layer.msg(res.msg);
        }
      },
      error: function (res) {
        layer.msg('错误请重试');
      }
    })
  }
});
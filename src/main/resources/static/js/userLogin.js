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
    document.getElementById("loginToHome").click();
  }
});

$('#loginToHome').click(function () {
  if ($('#userName').val() == '' || $('#userPassword').val() == '') {
    layer.msg('账号密码不能为空');
  } else {
    $.ajax({
      type: "post",
      url: url + port + "/client/login",
      dataType: "json",
      contentType: "application/json;charset=UTF-8",
      xhrFields: {
        withCredentials: true
      },
      data: JSON.stringify({
        "account": $('#userName').val(),
        "pwd": $('#userPassword').val()
      }),
      success: function (res) {
        if (res.result == 'SUCCESS') {
          layer.msg('登陆成功');
          document.cookie = 'uname=' + $('#userName').val();
          window.location.href = '../html/home.html';
          window.event.returnValue=false;
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

$('#toAdminLogin').click(function () {
  window.location.href = '../html/adminLogin.html';
  window.event.returnValue = false;
});

$('#checkUserPassword').blur(function () {
  if ($('#checkUserPassword').val() != $('#regUserPassword').val()) {
    layer.msg('两次输入不一致');
  }
});

$('.registerToServe').click(function () {
  if ($('#regUserName').val() == '' || $('#regUserPassword').val() == '' || $('#checkUserPassword').val() == '') {
    layer.msg('账号密码不能为空');
  } else {
    $.ajax({
      type: "post",
      url: url + port + "/client/addClient",
      dataType: "json",
      contentType: "application/json;charset=UTF-8",
      xhrFields: {
        withCredentials: true
      },
      data: JSON.stringify({
        "account": $('#regUserName').val(),
        "pwd": $('#regUserPassword').val(),
        "name": $('#regName').val(),
        "tel": $('#regUserTel').val(),
        "address": $('#regUserAddress').val(),
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
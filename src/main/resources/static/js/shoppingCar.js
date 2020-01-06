$(function () {
  let total;
  let sum = '';
  // 查找购物车列表
  getCar();
  function getCar() {
    $.ajax({
      type: "get",
      url: url + port + "/cart/queryItem",
      xhrFields: {
        withCredentials: true
      },
      success: function (res) {
        total = res.data.price
        $('.total_text').html(total + '元')
        var data = '';
        $.each(res.data.dishMap, function (index, value) {
          console.log(value)
          console.log(index)
          data += '<ul class="order_lists">' +
            '<li class="list_con">' +
            '<div class="list_img"><a href="javascript:;"><img src="' + url + '' + port + '' + value.dish.img + '" alt=""></a></div>' +
            '<div class="list_text"><a href="javascript:;">' + value.dish.name + '</a></div>' +
            '</li>' +
            '<li class="list_info">' +
            '<p>' + value.dish.detail + '</p>' +
            '</li>' +
            '<li class="list_price">' +
            '<p class="price" value="' + value.dish.id + '" id="' + value.dish.price + '">' + value.dish.price + '</p>' +
            '</li>' +
            '<li class="list_amount">' +
            '<div class="amount_box">' +
            '<input type="text" value="' + value.quantity + '" class="sum" id="' + value.dish.id + '">' +
            '</div>' +
            '</li>' +
            '<li class="list_op">' +
            '<p class="del"><a href="javascript:;" class="delBtn" value="' + value.dish.id + '">移除商品</a></p>' +
            '</li>' +
            '</ul>'
        });
        $('#shopBox').empty().append(data);
      }
    });
  }

  // 总价
  $("body").on('blur', '.sum', function () {
    $.ajax({
      type: "get",
      url: url + port + "/cart/modItem",
      xhrFields: {
        withCredentials: true
      },
      data: {
        "id": $(this).attr('id'),
        "quantity": $(this).val()
      },
      success: function (res) {
        getCar();
      }
    });
  });

  // 结算
  $('.calBtn').click(function() {
    $.ajax({
      type: "get",
      url: url + port + "/cart/countItem",
      xhrFields: {
        withCredentials: true
      },
      success: function (res) {
        console.log(res)
        if (res.result != "ERROR") {
          layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg('结算成功！');
          });
          setTimeout(() => {
            $('#shopBox').empty();
            getCar();
          }, 1000);
        } else {
          layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg('结算失败！');
          });
        }
      }
    });
  });

  // 清空购物车
  $('.deleteCar').click(function() {
    $.ajax({
      type: "get",
      url: url + port + "/cart/clearItem",
      xhrFields: {
        withCredentials: true
      },
      success: function (res) {
        getCar();
      }
    });
  });
});
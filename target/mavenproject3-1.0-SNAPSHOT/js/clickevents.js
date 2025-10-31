/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function openModal(
    modalID,
    id,
    image1,
    image2,
    name,
    salePrice,
    price,
    describe,
    classifyStr,
    companyName
) {
    const modalElement = document.getElementById(modalID);
    const arr = classifyStr.split(/\s+/);

    // Chèn HTML vào modal
    modalElement.innerHTML = `
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div class="modal_body">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5 col-md-5 col-sm-12">
                            <div class="modal_tab">
                                <div class="tab-content product-details-large">
                                    <div class="tab-pane fade show active" id="tab1">
                                        <div class="modal_tab_img">
                                            <img src="${image1}" alt="">
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab2">
                                        <div class="modal_tab_img">
                                            <img src="${image2}" alt="">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal_tab_button">
                                    <ul class="nav product_navactive">
                                        <li><a href="#tab1" class="nav-link active" data-toggle="tab"><img src="${image1}"></a></li>
                                        <li><a href="#tab2" class="nav-link" data-toggle="tab"><img src="${image2}"></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-7 col-md-7 col-sm-12">
                            <div class="modal_right">
                                <h2>${name}</h2>
                                <div class="modal_price">
                                    <span class="new_price">Rs. ${salePrice}$</span>
                                    ${price != salePrice ? `<span class="old_price">Rs. ${price}$</span>` : ''}
                                </div>
                                <p>${describe}</p>
                                <h2>Supplier: ${companyName}</h2>
                                <h2>Size</h2>
                                <select class="select_option">
                                    ${arr.map((si, index) => `<option value="${index + 1}">${si}</option>`).join('')}
                                </select>
                                <form>
                                    <input type="number" name="quantity" min="1" value="1">
                                    <input type="hidden" name="id" value="${id}">
                                    <input type="hidden" name="role" value="add">
                                    <button onclick="loadProductCart(this)" type="button">Add to cart</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`;

    // Hiển thị modal bằng Bootstrap
    const $modal = $(`#${modalID}`);
    $modal.modal('show'); // <-- đúng cách mở modal

    // Đảm bảo aria đúng
    $modal.attr('aria-hidden', 'false');
}


//
function modalOpen2(modalID, name, avt, balance) {
    let modalElement = document.getElementById(modalID);
    let modal = `<div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <button type="button" data-dismiss="modal" aria-label="close" class="close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <div class="modal_body">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-5 col-md-5 col-sm-12">
                                <img src="images/bannerwallet.png" alt="banner">
                            </div>

                            <div class="col-lg-7 col-md-7 col-sm-12">
                                <div class="modal_add_to_cart">
                                    <div style="display: flex;">
                                        <div ><img src="${avt}" atl="avatar" width="100px" style="border-radius:50%;"/></div>
                                        <div><h4>${name}</h4></div>
                                    </div>
                                        <div>
                                            <h4>Balance in card:</h4>
                                            <h3 style="color: green">$ ${balance}</h3>
                                        </div>
                                    <form action="wallet" method="get">
                                        <input  name="add" type="text" min="25" max="50000" step="5" value="25">
                                        <p style="color: grey">Enter a minimum amount of $50 and a maximum of $10000</p>
                                        <input  name="userN" type="hidden" value="${name}">
                                        <button type="submit">Input to card</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
   </div>`;
    let result = modalElement.innerHTML = modal;
    return result;
}


function confirmLogout(modalID) {
    let modalElement = document.getElementById(modalID);
    let modal = `
<div class="modal-dialog modal-dialog-centered" role="document" style="text-align:center">
    <div class="modal-content" style="width:500px; margin: 0 auto">
      <div class="modal-header" style="font-size:28px;padding: 30px 0; font-weight: 600; margin: 0 auto"><div>Sign out</div></div>
      <div class="modal-body" style="font-size: 16px; font-weight: 600">Are you sure you would like to sign out of your account?</div>
        <div class="modal-footer" style="justify-content: center;">
            <div style="width:40%; background-color: white"><a style="background-color: white!important; color: black; border-color: black;" class="btn btn-primary btn-block" href="logout">Logout</a></div>
            <div style="width:40%"><button data-dismiss="modal" aria-label="close" class="btn btn-primary btn-block">Cancel</button></div>
        </div>
    </div>
</div>`;

    let result = modalElement.innerHTML = modal;
    return result;
}

function modalEditWallet(modalID, username) {
    let modalElement = document.getElementById(modalID);
    let modal = `
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form" action="addbalance" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Wallet</h4>
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>User Name</label>
                                <input name="userName" type="text" class="form-control" value="${username}" readonly>
                            </div>
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>Balance</label>
                                <input name="balance" type="number" class="form-control" min="0" step="0.001">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" onclick="submitForm()" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>`;
    let result = modalElement.innerHTML = modal;
    return result;
}

//
function acceptRead() {
    var elements = document.querySelectorAll(".acceptEdit");
    var buttonVip = document.getElementById("buttonVip");
    var buttonVip2 = document.getElementById("buttonVip2");
    elements.forEach(function (element) {
        element.readOnly = false;
    });

    buttonVip.style.display = 'none';
    buttonVip2.style.display = 'block';
}

//
function notAccept() {
    var elements = document.querySelectorAll(".acceptEdit");
    var buttonVip = document.getElementById("buttonVip");
    var buttonVip2 = document.getElementById("buttonVip2");
    elements.forEach(function (element) {
        element.readOnly = true;
    });

    buttonVip.style.display = 'block';
    buttonVip2.style.display = 'none';
}

//
function checkLogout(choice) {
    if (choice == 'yes') {
        document.getElementById('logout').href = 'logout';
    }
}
//

function loadProductCart(buttonElement) {
    const form = buttonElement.closest("form");
    const quantity = form.querySelector("input[name='quantity']").value;
    const id = form.querySelector("input[name='id']").value;
    const role = form.querySelector("input[name='role']").value;

    $.ajax({
        url: "/mavenproject1/cart",
        type: "get",
        data: {
            quantity: quantity,
            id: id,
            role: role
        },
        success: function (data) {
            // Cập nhật phần giỏ hàng
            document.getElementById("header_right").innerHTML = data;

            // Đóng modal nếu đang mở
            $('#modal_box').modal('hide');

            // Dọn dẹp các lớp nền tối còn sót (fix lỗi treo màn)
            $(".modal-backdrop").remove();
            $("body").removeClass("modal-open");
            $("body").css("padding-right", "");
        },
        error: function (xhr) {
            console.log("Lỗi khi thêm vào giỏ:", xhr);
        }
    });
}


function removeProductCart(obj) {
    var role = "remove";
    var id = obj.value;
    console.log(role, id);
    $.ajax({
        url: "/mavenproject1/cart",
        type: "get",
        data: {
            rid: id,
            role: role
        },
        success: function (data) {
            var row = document.getElementById("header_right");
            row.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}


function reloadPriceViewCart(id) {
    event.preventDefault();
    $.ajax({
        url: "/mavenproject1/priceviewcart",
        type: "get",
        data: {
            rid: id
        },
        success: function (data) {
            var row = document.getElementById("viewcart_content");
            row.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}

function toggleWishlist(id) {
    event.preventDefault();
    $.ajax({
        url: "/mavenproject1/wishlist",
        type: "get",
        data: {
            wishId: id
        },
        success: function (data) {
            var row = document.getElementById("header_right");
            row.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}
function  loadMore(obj) {
    var amount = document.getElementsByClassName("product_items").length;
    var numPage = ((parseInt(obj.textContent) - 1) * 9);
    $(".linkLoad").removeClass("active");
    $(obj).addClass("active");

    $.ajax({
        url: "/mavenproject1/load",
        type: "get",
        data: {
            exits: numPage
        },
        success: function (data) {
            var row = document.getElementById("contentt");
            row.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}

function change() {
    var a = document.getElementById("avt");
    if (a.style.display === 'none' || a.style.display === '') {
        a.style.display = 'block';
    } else {
        a.style.display = 'none';
    }
}

function submitForm() {
    var here = document.querySelector('#here');
    var form = document.getElementById('form');
    var dobDay = document.getElementById('dobDay').value;
    var dobMonthText = document.getElementById('dobMonth').value;
    var dobYear = document.getElementById('dobYear').value;
    if (dobMonthText < 10 && dobDay < 10) {
        dobFull = dobYear + '-0' + dobMonthText + '-0' + dobDay;
    } else if (dobMonthText < 10 && !(dobDay < 10)) {
        dobFull = dobYear + '-0' + dobMonthText + '-' + dobDay;
    } else if (dobDay < 10 && !(dobMonthText < 10)) {
        dobFull = dobYear + '-' + dobMonthText + '-0' + dobDay;
    } else {
        dobFull = dobYear + '-' + dobMonthText + '-' + dobDay;
    }
    here.value = dobFull;
    form.submit();
}
function closeModal(modalID) {
    // 1. Ẩn modal
    $(`#${modalID}`).modal('hide');

    // 2. Xóa nội dung modal
    $(`#${modalID}`).html('');

    // 3. Xóa backdrop nếu còn
    $('.modal-backdrop').remove();

    // 4. Reset các class và CSS do Bootstrap thêm
    $('body').removeClass('modal-open').css('padding-right', '');

    // 5. Reset aria để tránh cảnh báo
    document.getElementById(modalID).setAttribute('aria-hidden', 'true');
    document.getElementById(modalID).style.display = 'none';
}
function searchByName() {
    var searchValue = document.getElementById("searchId").value;

    // Gửi request đến SearchServlet (hoặc RefineServlet nếu bạn gộp chung)
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "search?name=" + encodeURIComponent(searchValue), true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Gán kết quả tìm được vào khối chứa sản phẩm (ví dụ div có id="product_list")
            document.getElementById("product_list").innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}

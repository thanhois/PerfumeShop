<%-- 
    Document   : about_us
    Created on : Jun 21, 2025, 11:59:31 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <title>Perfume Paradise - About Us</title>
        <link rel="icon" href="images/logo1.png" type="image/x-icon" />

        <!-- Bootstrap & FontAwesome -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>

        <!-- Additional Libraries -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/style.css">
        <style>
            .a {
                color: black;
            }
        </style>
    </head>
    <body class="stretched no-transition">
        <header>
            <div class="main_header header_transparent header-mobile-m">
                <div class="header_container sticky-header">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col-lg-2">
                                <div class="logo">
                                    <a href="home"><img src="images/logo.png" alt="Logo"></a>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="main_menu menu_two menu_position">
                                    <nav>
                                        <ul>
                                            <li class="mega_items">
                                                <a href="#">Collections <i class="fa fa-angle-down"></i></a>
                                                <div class="mega_menu">
                                                    <ul class="mega_menu_inner">
                                                        <li><a class="a" href="refine?cid_refine=0">ALL</a></li>
                                                            <c:forEach items="${requestScope.category}" var="c">
                                                            <li><a class="a" href="refine?cid_refine=${c.id}">${c.name}</a></li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                            </li>
                                            <li><a href="aboutus" style="color:#f6692a">About Us</a></li>
                                            <li><a href="contact">Contact Us</a></li>
                                                <c:choose>
                                                    <c:when test="${sessionScope.account == null}">
                                                    <li>
                                                        <a href="#">User <i class="fa fa-angle-down"></i></a>
                                                        <ul class="sub_menu pages">
                                                            <li><a href="login">Login</a></li>
                                                            <li><a href="register">Sign Up</a></li>
                                                        </ul>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li><a href="profile">Profile</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <%@ include file="header_right.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section id="content">

            <!-- group 1 -->
            <section id="section_about" class="corner clearfix" style="background-image: url('images/banner/banner15.png');">
                <div class="container py-5">
                    <h2 style="font-family: Roboto; font-size: 48px; margin-top: 30px">
                        Store <span style="color: #ff5722;">PERFUME PARADISE</span>
                    </h2>
                    <h3 style="font-weight: 400;">Cửa hàng phân phối nước hoa chính hãng tại Việt Nam</h3>
                    <div class="row mt-4 align-items-center">
                        <div class="col-md-4 text-center">
                            <img src="images/products/Women/20-2.png" class="img-fluid" alt="heading_image">
                        </div>
                        <div class="col-md-8">
                            <div class="section_about_content_text">
                                <p><i class="fa fa-check"></i><span> Cam kết</span> chính hãng.</p>
                                <p><i class="fa fa-check"></i> Chính sách bảo hành <span>10 năm.</span></p>
                                <p><i class="fa fa-check"></i> Giao hàng <span>nhanh chóng</span>, nhận hàng sau 3 ngày.</p>
                            </div>
                            <a class="button-click mt-3" href="refine?cid_refine=0">
                                <button>CHỌN MUA NGAY</button>
                            </a>
                        </div>
                    </div>
                </div>
            </section>

            <!-- group 2 -->
            <section id="section_inspiration" class="py-5 bg-white">
                <div class="container">
                    <div class="row align-items-center mb-4">
                        <div class="col-md-6">
                            <h2 class="section_inspiration_title">Cam Kết Chính Hãng</h2>
                            <p>Chúng tôi hợp tác với các nhãn hiệu nổi tiếng để đảm bảo mọi sản phẩm bạn chọn đều là chính hãng, chất lượng và có nguồn gốc rõ ràng.</p>
                        </div>
                        <div class="col-md-6 text-center">
                            <img src="images/banner/banner2.jpg" alt="section_inspiration" class="img-fluid">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <img src="images/banner/banner11.png" alt="section_inspiration_large" class="img-fluid">
                        </div>
                    </div>
                </div>
            </section>

            <!-- group 3 -->
            <section id="section_material" class="py-5 bg-light">
                <div class="container">
                    <div class="row align-items-center mb-4">
                        <div class="col-md-6 order-md-2 text-center">
                            <img src="images/banner/banner14.jpg" alt="section_material_image" class="img-fluid">
                        </div>
                        <div class="col-md-6 order-md-1">
                            <h3 class="section_material_1_title">Mẫu Mã Đa Dạng</h3>
                            <blockquote>
                                <i>Chúng tôi mang đến hương thơm độc đáo và phù hợp với cá tính riêng của từng khách hàng.</i>
                            </blockquote>
                            <p>Bộ sưu tập nước hoa phong phú, từ hương thơm quyến rũ đến tươi mới, liên tục cập nhật.</p>
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <div class="col-md-6">
                            <h3 class="section_material_2_title">Hỗ Trợ Khách Hàng Nhanh Chóng</h3>
                            <p>Đội ngũ CSKH luôn sẵn sàng hỗ trợ bạn qua điện thoại, email, hoặc trực tiếp tại cửa hàng.</p>
                        </div>
                        <div class="col-md-6 text-center">
                            <img src="images/banner/banner10.jpg" alt="section_material_2_image" class="img-fluid">
                        </div>
                    </div>
                </div>
            </section>

            <!-- contact section -->
            <section id="section_contact" class="py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7318.072762120251!2d105.52271427723237!3d21.01242168833568!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgSMOgIE7hu5lp!5e1!3m2!1svi!2s!4v1751703083987!5m2!1svi!2s" 
                                    width="100%" height="400" frameborder="0" style="border:0;" allowfullscreen="" 
                                    loading="lazy" referrerpolicy="no-referrer-when-downgrade">
                            </iframe>
                        </div>
                        <div class="col-md-6" style="background-color: #383c44;">
                            <div class="p-4">
                                <h3 style="color: #ffffff; font-size: 24px;">Văn phòng chính</h3>
                                <div style="line-height: 1.7;">
                                    <address style="color: #dbdbdb; font-size: 16px;">
                                        <strong style="color: #fff;">North America:</strong><br>
                                        795 Folsom Ave, Suite 600<br>
                                        San Francisco, CA 94107<br>
                                    </address>
                                    <address style="color: #dbdbdb; font-size: 16px;">
                                        <strong style="color: #fff;">Miền Bắc:</strong><br>
                                        Km29 Đại lộ Thăng Long<br>
                                        Huyện Thạch Thất, Hà Nội<br>
                                        Hotline: 1900 9090<br>
                                        Email: perfumeparadisevn@gmail.com
                                    </address>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <jsp:include page="footer.jsp"/>

            <!-- Scripts -->
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/owl.carousel.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
            <script src="js/main.js"></script>
    </body>
</html>

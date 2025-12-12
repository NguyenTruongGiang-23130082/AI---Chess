<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png"  href="img/logo.png" >
    <title>HOME DECOR - ĐĂNG NHẬP </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="css/login_style.css">
</head>
<body>

<%-- Show server-side messages from LoginServlet / RegisterServlet --%>
<% String error = (String) request.getAttribute("ERROR_MESSAGE");
   String regMsg = (String) request.getAttribute("MESS_REGISTER");
   String success = (String) request.getAttribute("MESS_SUCCESS"); %>
<div class="flash-messages">
    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>
    <% if (regMsg != null) { %>
        <div class="error"><%= regMsg %></div>
    <% } %>
    <% if (success != null) { %>
        <div class="success"><%= success %></div>
    <% } %>
</div>

    <div class="container" id="container">
        <div class="form-container sign-up-container">
        <form action="<%= request.getContextPath() %>/RegisterServlet" method="post">
             <h1>Đăng ký</h1>
             <div class="social-container">
                 <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                 <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                 <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
             </div>
             <span>hoặc sử dụng email của bạn để đăng ký</span>
             <div class="infield">
                 <input id="reg_username" type="text" name="username" placeholder="Tên" required/>
                 <label for="reg_username" class="visually-hidden"></label>
             </div>
             <div class="infield">
                 <input id="reg_email" type="email" placeholder="Email" name="email" required/>
                 <label for="reg_email" class="visually-hidden"></label>
             </div>
             <div class="infield">
                 <input id="reg_password" type="password" name="password" placeholder="Mật khẩu" required minlength="8" />
                 <label for="reg_password" class="visually-hidden"></label>
                 <small class="password-note">Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm một ký tự viết hóa, một ký tự số và một ký tự đặc biệt</small>
             </div>

             <div class="infield">
                 <input id="reg_re_password" type="password" name="re_password" placeholder="Xác nhận mât khẩu" required/>
                 <label for="reg_re_password" class="visually-hidden"></label>
             </div>

             <button>Đăng ký</button>
         </form>
     </div>
     <div class="form-container sign-in-container">
         <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
             <h1></h1>
             <div class="social-container">
                 <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                 <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                 <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
             </div>
             <span>hoặc sử dụng tài khoản của bạn</span>
             <div class="infield">
                 <input id="login_email" type="email" placeholder="Email" name="email" required/>
                 <label for="login_email" class="visually-hidden"></label>
             </div>
             <div class="infield">
                 <input id="login_password" type="password" name="password" placeholder="Mật khẩu" required />
                 <label for="login_password" class="visually-hidden"></label>
             </div>
             <div class="remember-forgot">
                 <label>
                     <input type="checkbox" name="remember">
                     Ghi nhớ đăng nhập
                 </label>
                 <a href="html/login_forgot_password.html" class="forgot">Quên mật khẩu?</a>
             </div>

             <button>Đăng nhập</button>
         </form>
     </div>
     <div class="overlay-container" id="overlayCon">
         <div class="overlay">
             <div class="overlay-panel overlay-left">
                 <h1>HOME DECOR!</h1>
                 <p>Để tiếp tục kết nối với chúng tôi, vui lòng đăng nhập bằng thông tin cá nhận của bạn</p>
                 <button>Đăng nhập</button>
             </div>
             <div class="overlay-panel overlay-right">
                 <h1>HOME DECOR!</h1>
                 <p>Hãy nhập thông tin cá nhận của bạn và bắt đầu hành trình cùng chúng tôi</p>
                 <button>Đăng ký</button>
             </div>
         </div>
         <button id="overlayBtn"></button>
     </div>
</div>

<script src="js/login_JS.js"></script>


</body>
</html>
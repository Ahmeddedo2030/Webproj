<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Online Buecher</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="js/cart.js"></script>
     <script>
    $(document).ready(function(){
      $('.slider').bxSlider({
          
       speed:2000,
       auto: true,
       controls: false
          
      });
    });
        
  </script>
    
    <script>
    
        window.onload = function(){
        	
        	clearCart();
            document.getElementById("totalcount").innerHTML = getallItemsCount();
            
            
              };
    
    </script>
    

</head>

<body>
    
    <header>

<div id="wrapper">
 <div id="header">
  <div id="subheader">
   <div class="container">
   
     <li><a href="login.jsp">Login</a></li>
     <li><a href="register.jsp">Register</a></li>
       <li><a href="index.jsp">Home</a></li>
       
   </div>
  </div>
     
 </div>
</div>
     <!--main Header-->
    <div id="main-header">
         
         <div id="logo">
         
             <span id="sp1">MeinBuchGeschaeft.de</span> 
             
         </div>
     
         <!--Suchen-->
         <div id = "search">
         <form action="">
         
             <input class="searchfeld" type = "text" name="text" placeholder="Hier ihre Suche bitte Eingeben... ">
             <input class="searchsubmit" type = "submit" name="submit" value="Suchen">
         
         </form>
     
        </div>
     </div>
    
    <!---Home Slide--->
    
    
 <div class="slide">
    
        <ul class="slider">
        <li><img src= slideimgs/1.jpg style="position: relative; margin-left: 230px; margin-top: 10px; height: 430px;  width: 900px;"></li>
        <li><img src= slideimgs/2.jpg style="position: relative; margin-left: 230px; margin-top: 10px; height: 430px;  width: 900px;"></li>
        <li><img src= slideimgs/3.jpg style="position: relative; margin-left: 230px; margin-top: 10px; height: 430px;  width: 900px;"></li>
        </ul>
    
    </div>
        </header>
    
    
    <h1 style=" margin-left: 550px; font-size: 50px; color:cadetblue;">Willkommen...</h1>
    
    <footer>
        
        <div id="footer">
            
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>

</body>
</html>
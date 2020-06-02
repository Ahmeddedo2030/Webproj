<%@page import="buchladen.WarenKorbImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Payment Checkout Form</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css">
	<link rel="stylesheet" href="css/style.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <style>
    
    .button {
    width:150px;
    height: 50px;
    font-size:18px;
    background-color:#000;
    color:#fff;
    border:#fff solid 1px;
    margin-left: 110px;
    margin-top: 20px;
}

.button:hover {
    background-color:#fff;
    color:#000;
    cursor: pointer;
}
    
    </style>

</head>
<body>
	
<div id="wrapper">
 <div id="header">
  <div id="subheader">
   <div class="container">
   
     <span style="font-weight: bold; margin-top:50px; font-size:30px; color:cornflowerblue;"><%= session.getAttribute("username")%></span>
    <span style="font-weight: bold; padding-right: 20px; font-size: 50px; margin-top:50px; font-size:30px; color:darkorange;"><%=WarenKorbImpl.getCount((int)session.getAttribute("userid"))%></span>
       <span><a href="warenkorb"><img src= imgs/cart.png style="height: 60px; width: 80px;"></a></span>
     <li><a href="index.jsp">logout</a></li>
       <li><a href="CategoryBookList">Home</a></li>
       
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

<div class="wrapper">
  <div class="payment">
  <h2 style="color:#FACC2E;">${message}</h2>
    
    <h2>Payment Gateway</h2>
<form action = "Payment_Form" method="post">
    <div class="form">
      <div class="card space icon-relative">
        <label class="label">Karte Besitzer:</label>
        <input type="text" name="kartbesitzer" class="input" value="${kartbesitzer}" placeholder="Coding Market">
        <i class="fas fa-user"></i>
      </div>
      <div class="card space icon-relative">
        <label class="label">Karte Nummer:</label>
        <input type="text" name="kartnummer" class="input" value="${kartnummer}" data-mask="0000 0000 0000 0000" placeholder="Card Number">
        <i class="far fa-credit-card"></i>
      </div>
      <div class="card-grp space">
        <div class="lieferungsadresse">
        <label class="label">Ablaufdatum:</label>
        <input type="text" name="date" class="input" value="${date}" placeholder="00/00" data-mask="00/00">
      </div>
        <div class="card-item icon-relative">
          <label class="label">CVC:</label>
          <input type="text" name="cvc" class="input" value="${cvc}" data-mask="000" placeholder="000">
          <i class="fas fa-lock"></i>
        </div>
      </div>
        <div class="lieferungsadresse">
        <label class="label">Lieferungsadresse:</label>
        <input type="text" name="adresse" class="input" value="${adresse}" placeholder="Ihre Lieferungsadresse">
      </div>
        <button type="submit" name="submit" value="bezahlen" class="button">Bezahlen</button>
    </div>
      </form>
     
  </div>
</div>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
    
     <footer>
        
        <div id="footer">
            
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>

</body>
</html>
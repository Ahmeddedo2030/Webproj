<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "buchladen.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Online Buecher</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
     <style>
    
    .button {
    width:250px;
    height: 50px;
    font-size:18px;
    background-color:#000;
    color:#fff;
    border:#fff solid 1px;
    margin-top: 30px;
    margin-left: 520px;
}

.button:hover {
    background-color:skyblue;
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
         <form action="" method="post">
         
             <input class="searchfeld" type = "text" name="text" placeholder="Hier ihre Suche bitte Eingeben... ">
             <input class="searchsubmit" type = "submit" name="submit" value="Suchen">
         
         </form>
     
        </div>
     </div>
     
     <form action = "Payment_Form" method="post">

    <div id="bestaetigung">
        <h3>Bitte ihre Bestellung Besteatigen</h3>
       
        
        <table>
        
            <tr>
                <td>Konto Inhaber :</td>
                <td>${kontoinhaber}</td>
            
            </tr>
             <tr>
              
                <td>IBAN :</td>
                <td>${iban}</td>
            
            </tr>
            <tr>
               
                <td>BIC:</td>
                <td>${bic}</td>
            
            </tr>
            
            <tr>
                <td>Total:</td>
                <td>${total} $</td>
            
            </tr>
            
             <tr>
               
                <td>Lieferungsadresse:</td>
                <td>${adresse}</td>
            
            </tr>
        
        </table>
            
    </div>
    
    
    <%=WarenKorbImpl.displayWarenkorb((int)session.getAttribute("userid")) %>
    
    
    <input type="hidden" name = "kontoinhaber" id="kontoinhaber" value="${kontoinhaber}">
    <input type="hidden" name = "iban" id="iban" value="${iban}">
    <input type="hidden" name = "bic" id="bic" value="${bic}">
    <input type="hidden" name = "badresse" id="kadresse" value="${adresse}">
    <input type="hidden" name = "btotal" id="ktotal" value="${total}">
    
     <button type="submit" name="submit" value="bank_warencorp" id="besteatigung" class="button">Bestätigen</button>
     
        </form>
    
    
    <form action = "warenkorb" method="get">
    
     <button type="submit" name="submit" id="warenkorb" value="warenkorb" class="button">Zum Warenkorb</button>
     
     </form>
    
     <footer>
        
        <div id="footer">
            
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>

</body>
</html>
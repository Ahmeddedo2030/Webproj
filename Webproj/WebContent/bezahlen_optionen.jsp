<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "buchladen.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Abschluss</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
    
    <style>
    
        #footer{
    
    width: 100%;
    height: 140px;
    background: darkslategray;
    margin-top: 170px;
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
    
        
        <h2 style="color:cadetblue; margin-left:500px; margin-top:200px;">Bezahlungsmöglichkeiten :</h2>
        
         <select onchange="getValue(this.value)" style="margin-left:480px; pxpadding-bottom:10px; border-radius:10px; width:300px;" class="input-xlarge">  
		 <option disabled selected>Bezahlungstyp auswählen</option>
		 <option value="bezahlen.jsp">Credit Card</option>
		 <option value="bank_bezahlen.jsp">Bank-IBAN-Überweisung</option> 
	     </select>
	     
			    <script>		          
				    function getValue(selectObject) {
				    	window.location = selectObject;
				    }      
			    </script>
    
    <footer>
        
        <div id="footer">
            
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>
    

</body>
</html>
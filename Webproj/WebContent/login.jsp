<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Form</title>

<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>

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

   <div id="container">
   <h2 style="color:#FACC2E;">${message}</h2>
  <h1>Login</h1>
 <form action = "Login_Register_Validate" method="post">
  <label for="Benutzername"><b>Benutzername</b></label><br>
  <input type="text" name="username" placeholder="Benutzername" value="${username}"><br>
  <label for="psw"><b>Passwort</b></label><br>
  <input type="password" name="password" placeholder="passwort" value="${password}"><br>
  <button type="submit" name="submit" class="button" value="login">Login</button><br>
  </form>
  <a href="register.jsp">Registrieren</a><br>
    <span class="psw"> <a href="#">passwort vergessen?</a></span><br>
    
</div>
    
     <footer>
        
        <div id="footer">
            
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>
    
</body>
</html>
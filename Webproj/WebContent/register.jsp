<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Anmeldungsseite</title>

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
     
     
    <form action = "Login_Register_Validate" method="post">
     <div id="container2">
     
     <h2 style="color:#FACC2E;">${message}</h2>
     
        <div class="control-group">
  <label class="control-label" for="name">Name</label><br>
  
    <input id="name" name="name" placeholder="name" class="input-xlarge" type="text" value="${name}">
    
</div>


<div class="control-group">
  <label class="control-label" for="nachname">Nachname</label><br>
  
    <input id="nachname" name="nachname" placeholder="nachname" class="input-xlarge" type="text" value="${nachname}">
   
  
</div>

   <div class="control-group">
  <label class="control-label" for="name">Benutzer Name</label><br>
  
    <input id="username" name="username" placeholder="Benutzer Name" class="input-xlarge" type="text" value="${username}">
    
  
  </div>
  
     <div class="control-group">
  <label class="control-label" for="name">Passwort</label><br>
  
    <input id="password" name="password" placeholder="Passwort" class="input-xlarge" type="password" value="${password}">
    
  
</div>


<div class="control-group">
  <label class="control-label" for="email">Email</label><br>
  
    <input id="email" name="email" placeholder="Email." class="input-xlarge" type="text" value="${email}">
  
</div>


<div class="control-group">
  <label class="control-label" for="Geburtsdatum">Geburtsdatum</label><br>
  
      <input id="Geburtsdatum" name="date" class="input-large" placeholder="DD.MM.YYYY" data-mask="00.00.0000" type="text" value="${date}">
      
</div>


<div class="control-group">
  <label class="control-label" for="Geschlecht">Geschlecht</label><br>
  
    <label class="radio inline" for="Geschlecht">
      <input name="Geschlecht" id="Geschlecht-0" value="m" checked="checked" type="radio">
      M‰nnlich
    </label>
    <label class="radio inline" for="gender-1">
      <input name="Geschlecht" id="Geschlecht-1" value="f" type="radio">
      Weiblich
    </label><br><br>
  
</div>

<div class="control-group">
   
   <label class="control-label" for="Ort">Ort</label><br>
   <input id="Ort" name="Ort" placeholder="Ort" class="input-xlarge" type="text" value="${Ort}"><br>
   
   <label class="control-label" for="PLZ">PLZ</label><br>
   <input id="PLZ" name="PLZ" placeholder="PLZ" class="input-xlarge" type="text" value="${PLZ}"><br>
   
   <label class="control-label" for="Straﬂe">Straﬂe</label><br>
   <input id="Strasse" name="Strasse" placeholder="Straﬂe" class="input-xlarge" type="text" value="${Strasse}"><br>
   
</div>


<div class="control-group">
  <label class="control-label" for="Beruf">Beruf</label><br>
  
    <select id="Beruf" name="Beruf" class="input-xlarge" style="width: 100px; border-radius:10px;">
      <option>Dozent</option>
      <option>Student</option>
      <option>Anders</option>
    </select>
  
</div><br>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for="submit"></label>
  <div class="controls">
    <button id="submit" name="submit" class="button" value="registeration">Registrieren</button>
  </div>
</div>
         </div>
        </form>
        
        <footer>
        
        <div id="footer">
            
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>

</body>
</html>

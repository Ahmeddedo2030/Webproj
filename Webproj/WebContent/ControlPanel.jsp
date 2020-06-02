<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "buchladen.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Panel</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="js/cart.js"></script>
    <script src="js/general.js"></script>
    
    <script>
    
        window.onload = function(){
 
            document.getElementById("totalcount").innerHTML = getallItemsCount();
            
              };
    
    </script>
    
    <style>
    
       .button {
       
    width:150px;
    height: 50px;
    font-size:18px;
    background-color:#000;
    color:#fff;
    border:#fff solid 1px;
    margin-top: 30px;
    margin-left: 50px;
}

.button:hover {
    background-color:skyblue;
    color:#000;
    cursor: pointer;
}
    
 #footer{
    
    width: 100%;
    height: 140px;
    background: darkslategray;
    margin-top: 170px;
}

 #transaktionen{
 
  font-size: 25px;
  color:navy;
 
 }
 
 #buch{
 
  font-size: 25px;
  color:navy;
  margin-top: 50px;
  margin-left: 50px;
 
 }
 
  #main-header{
  
  margin-bottom:50px;
  
  }

    </style>

</head>

<body>
    

<div id="wrapper">
 <div id="header">
  <div id="subheader">
   <div class="container">
   
     <span><div id="totalcount"></div></span>
       <span><a href="einkaufswagen.jsp"><img src= imgs/cart.png style="height: 60px; width: 80px;"></a></span>
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
     
     <div id="transaktionen">
     
  Transaktionen des Benutzers Zeigen: <input id="benutzer" style = "width:300px;" class="showtran" type = "text"  name="username" placeholder="Hier Benutzername bitte Eingeben... ">
    
   <button id="transaktion" type="submit" name="submit" value="transaktionen" class="button">submit</button>
   
     </div>
    
     <div id="res3"></div>
     
      <div id="transaktionen">
     
  Alle Users zeigen:  <button id="allusers" type="submit" name="submit" value="showusers" class="button">submit</button>
   
     </div>
     
      <div id="res4"></div>
      
     
      <div id="buch">
      
        Buch Aktualisieren: 
     
     <span style="margin-left:10px; color:red;">${buchaktu}</span>
     <br>
     
      Kategorie: <%= KategorieImpl.showCategories(2) %>
      
    <button style = "margin-left: 20px;" type="submit" name="submit" value="kategorieliste2" id="katliste2" class="button">Kategorie Einfügen</button>
    <button style = "margin-left: 830px;" type="submit" name="submit" value="kategorieloeschen" id="katloeschen2" class="button">Liste Löschen</button>
     
      <div id ="res6" >${katliste2}</div>
      
      </div>
     
     <form action = "Buch_Kategorie" method="post">
     
      <div id="buch">
      
     <br>
      Buch Auswählen:  <%= BuchImpl.showBuch(1) %>
     <br>
     Titel: <input style="margin-top:10px; margin-left:40px; height:30px; width:705px; border-radius: 10px; border-style: ridge; border-color: cadetblue;" type = "text" name="buch_titel2" value="${kat2}">
     <br>
     Preis: <input style="margin-top:10px; height:30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width:705px; margin-left: 37px;" type = "text" name="buch_preis2" value="${preis2}">
     <br>
     ISBN: <input style="margin-top:10px; height:30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width:705px; margin-left: 32px;" type = "text" name="buch_isbn2" value="${isbn2}">
     <br>
     Sprache: <input style="margin-top:10px; height:30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width:705px; margin-left: 5px;" type = "text" name="buch_sprache2" value="${sprache2}">
     <br>
     Bildpfad: <input style="margin-top:10px; height:30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width:705px;" type = "text" name="buch_bild2" value="${bild2}">
     <br>
     Anzahl: <input style="margin-top:10px; height:30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width:705px; margin-left: 17px;" type = "text" name="buch_anzahl2" value="${anzahl2}">
     <br>
     Verlag: <%= VerlagImpl.showVerlag(2) %>
     <br>
     Autor: <%= AutorImpl.showAutor(2) %>
     <br>
     Lager: <%= BuchImpl.showLager(2) %>
     <br>
     <button style="margin-left: 320px;" type="submit" name="submit" value="buchaktualisieren" class="button">Aktualisieren</button>
     
     </div>
     
     <div id="buch">
      
   <span style="margin-left:10px; color:red;">${buchdel}</span>
     <br>
  Buch Auswählen:  <%= BuchImpl.showBuch(2) %>
  
  <br>
     <button style="margin-left: 320px;" type="submit" name="submit" value="buchloeschen" class="button">Löschen</button>
   
     </div>
     
     </form>
     
     <div id="buch">
      
       Bücher einer Kategorie Zeigen: <%= KategorieImpl.showCategories(3) %>
       
       <br>
    
   <button style = "margin-left: 320px;" type="submit" name="submit" value="buecherzeigen" id="buch_kategorie" class="button">submit</button>
      
      </div>
      
      <div id="res2"></div>
     
       <div id="buch">
      
  Alle Bücher zeigen: <button style = "margin-left: 110px;" type="submit" name="submit" value="allebuecher" id="buchname" class="button">submit</button>
      
      </div>
      
      <div id="res"></div>
      
                     
    <footer>
        
        <div id="footer">
        
            <img src= imgs/logo.png>
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>


</body>
</html>
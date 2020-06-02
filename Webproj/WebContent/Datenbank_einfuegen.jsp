<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="buchladen.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Panel</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/cart.js"></script>
<script src="js/general.js"></script>

<script>
	window.onload = function() {

		document.getElementById("totalcount").innerHTML = getallItemsCount();

	};
</script>

<style>
.button {
	width: 150px;
	height: 50px;
	font-size: 18px;
	background-color: #000;
	color: #fff;
	border: #fff solid 1px;
	margin-top: 30px;
	margin-left: 50px;
}

.button:hover {
	background-color: skyblue;
	color: #000;
	cursor: pointer;
}

#footer {
	width: 100%;
	height: 140px;
	background: darkslategray;
	margin-top: 170px;
}

#transaktionen {
	font-size: 25px;
	color: navy;
}

#buch {
	font-size: 25px;
	color: navy;
	margin-top: 50px;
	margin-left: 50px;
}

#main-header {
	margin-bottom: 50px;
}
</style>

</head>

<body>


	<div id="wrapper">
		<div id="header">
			<div id="subheader">
				<div class="container">

					<span><div id="totalcount"></div></span> <span><a
						href="einkaufswagen.jsp"><img src=imgs/cart.png
							style="height: 60px; width: 80px;"></a></span>
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
		<div id="search">
			<form action="">

				<input class="searchfeld" type="text" name="text"
					placeholder="Hier ihre Suche bitte Eingeben... "> <input
					class="searchsubmit" type="submit" name="submit" value="Suchen">

			</form>

		</div>
	</div>
	
	<form action="db_einfuegen" method="post">
	
	 <div id="buch">
	
	Kategorie Einfügen: <span style="margin-left: 10px; color: red;">${katadd}</span>
		
		<br>
		
		Kategoriename: <input
				style="margin-top: 10px; margin-left: 40px; height: 30px; width: 705px; border-radius: 10px; border-style: ridge; border-color: cadetblue;"
				type="text" name="kat_name"> <br>
				
				<button style="margin-left: 450px;" type="submit" name="submit"
				value="kateinfugen" id="einfugen" class="button">Einfügen</button>
	  </div>
	  
	   <div id="buch">
	
	Verlag Einfügen: <span style="margin-left: 10px; color: red;">${verlagadd}</span>
		
		<br>
		
		Verlagsname: <input
				style="margin-top: 10px; margin-left: 40px; height: 30px; width: 705px; border-radius: 10px; border-style: ridge; border-color: cadetblue;"
				type="text" name="verlag_name" value="${verlag_name}"> <br>
				Verlagsadresse: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 22px;"
				type="text" name="verlag_adresse" value="${verlag_adresse}"> <br>
			Kontakt: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 90px;"
				type="text" name="verlag_kontakt" value="${verlag_kontakt}"> <br>
				
				<button style="margin-left: 450px;" type="submit" name="submit"
				value="verlageinfugen" id="einfugen" class="button">Einfügen</button>
	  </div>
	  
	    <div id="buch">
	
	Autor Einfügen: <span style="margin-left: 10px; color: red;">${autoradd}</span>
		
		<br>
		
		Autorname: <input
				style="margin-top: 10px; margin-left: 40px; height: 30px; width: 705px; border-radius: 10px; border-style: ridge; border-color: cadetblue;"
				type="text" name="autor_name" value="${autor_name}"> <br>
				
			Autorkontakt: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 20px;"
				type="text" name="autor_kontakt" value="${autor_kontakt}"> <br>
				
				<button style="margin-left: 450px;" type="submit" name="submit"
				value="autoreinfugen" id="einfugen" class="button">Einfügen</button>
	  </div>
	  
	</form>

	<div id="buch">

		Buch Einfügen: <span style="margin-left: 10px; color: red;">${buchadd}</span>
		<br> Kategorie:
		
		<%=KategorieImpl.showCategories(1)%>

		<button style="margin-left: 20px;" type="submit" name="submit"
			value="kategorieliste" id="katliste" class="button">Kategorie
			Einfügen</button>
		<button style="margin-left: 830px;" type="submit" name="submit"
			value="kategorieloeschen" id="katloeschen" class="button">Liste
			Löschen</button>

		<div id="res1">${katliste}</div>

	</div>

	<form action="db_einfuegen" method="post">

		<div id="buch">

			<br> Titel: <input
				style="margin-top: 10px; margin-left: 40px; height: 30px; width: 705px; border-radius: 10px; border-style: ridge; border-color: cadetblue;"
				type="text" name="buch_titel" value="${kat}"> <br>
			Preis: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 37px;"
				type="text" name="buch_preis" value="${preis}"> <br>
			ISBN: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 32px;"
				type="text" name="buch_isbn" value="${isbn}"> <br>
			Sprache: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 5px;"
				type="text" name="buch_sprache" value="${sprache}"> <br>
			Bildpfad: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px;"
				type="text" name="buch_bild" value="${bild}"> <br>
			Anzahl: <input
				style="margin-top: 10px; height: 30px; border-radius: 10px; border-style: ridge; border-color: cadetblue; width: 705px; margin-left: 17px;"
				type="text" name="buch_anzahl" value="${anzahl}"> <br>
			Verlag:
			<%=VerlagImpl.showVerlag(1)%>
			<br> Autor:
			<%=AutorImpl.showAutor(1)%>
			<br> Lager:
			<%=BuchImpl.showLager(1)%>
			<br>
			<button style="margin-left: 320px;" type="submit" name="submit"
				value="bucheinfugen" id="einfugen" class="button">Einfügen</button>

		</div>

	</form>

	<footer>

		<div id="footer">

			<img src=imgs/logo.png>
			<p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>

		</div>
	</footer>


</body>
</html>
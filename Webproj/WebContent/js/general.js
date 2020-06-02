
$(document).ready(function() {

	$('#buchname').click(function(event) {
		 
		var name = $('#buchname').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'Buch_Kategorie',
		    data: {submit:name},
		    success:function(result){
		    	
		     $('#res').html(result);
		    
		    }
		});
         
	});
	
	$('#buch_kategorie').click(function(event) {
		 
		var name = $('#buch_kategorie').val();
		var kat = $('#kategorie3').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'Buch_Kategorie',
		    data: {submit:name,
		    	kategorie3:kat},
		    success:function(result){
		    	
		     $('#res2').html(result);
		    
		    }
		});
         
	});
	
	$('#transaktion').click(function(event) {
		 
		var name = $('#transaktion').val();
		var username = $('#benutzer').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'Payment_Form',
		    data: {submit:name,
		    	username:username},
		    success:function(result){
		    	
		     $('#res3').html(result);
		    
		    }
		});
         
	});
	
	$('#allusers').click(function(event) {
		 
		var name = $('#allusers').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'Payment_Form',
		    data: {submit:name},
		    success:function(result){
		    	
		     $('#res4').html(result);
		    
		    }
		});
         
	});
	
	$('#katliste').click(function(event) {
		 
		var name = $('#katliste').val();
		var kat = $('#kategorie1').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'db_einfuegen',
		    data: {submit:name,
		    	kategorie1:kat},
		    success:function(result){
		    	
		     $('#res1').html(result);
		    
		    }
		});
         
	});
	
	$('#katloeschen').click(function(event) {
		 
		var name = $('#katloeschen').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'db_einfuegen',
		    data: {submit:name},
		    success:function(result){
		    	
		     $('#res1').html(result);
		    
		    }
		});
         
	});
	
	$('#katliste2').click(function(event) {
		 
		var name = $('#katliste2').val();
		var kat = $('#kategorie2').val();
		 
		$.ajax({
			
		    type: 'POST',
		    url: 'Buch_Kategorie',
		    data: {submit:name,
		    	kategorie2:kat},
		    success:function(result){
		    	
		     $('#res6').html(result);
		    
		    }
		});
         
	});
	
});


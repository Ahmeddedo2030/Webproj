
 var cart = [];
 var cartCopy= [];

 if (localStorage.getItem("shoppingcart") !== null) {
 
  loadCart();
  
 }

var Item = function(name,title,price,count,imagepath,totalpreis){
    
    this.name = name
    this.title = title
    this.price = price
    this.count = count
    this.path = imagepath
    this.totalpreis = totalpreis
};


function addItemtoCart(name,title,price,count,imagepath,totalpreis){
    
 
     for(var i = 0; i < cart.length; i++){
      
      if(cart[i].name === name){
          
          return null;
      }
         
     }
    
    totalpreis = price;
    var item = new Item(name,title,price,count,imagepath,totalpreis);
    cart.push(item);
    saveCart();
}

function incrementCart(name){
    
    
     for(var i = 0; i < cart.length; i++){
      
      if(cart[i].name === name){
          
          cart[i].count++;
          
          cart[i].totalpreis = cart[i].count * cart[i].price;
          
          saveCart();
          displayCart();
          
      }
      
  }
    
}


function removeOneItemfromCart(name){
    
  for(var i = 0; i < cart.length; i++){
      
      if(cart[i].name === name && cart[i].count > 1){
          
          cart[i].count--;
          
         cart[i].totalpreis = cart[i].count * cart[i].price;
          
          saveCart();
          displayCart();
          
          break;
      }
  }
    
}


function removeItem(name){
    
for(var i =0; i < cart.length; i++){
        
 if(cart[i].name === name){
    
    cart.splice(i,1);
    saveCart();
    displayCart();
             
         }
    }
}



function cartListCopy(){
    
    for(var i in cart){
        
        var item = {};
        
        for(var i2 in cart[i]){
            
            item[i2] = cart[i][i2];
            
        }
        
        cartCopy.push(item);
        
    }
    
    return cartCopy;
    
}

function clearCart(){
    
    cart = [];
    
    saveCart();
    displayCart();
}

function saveCart(){
    
    localStorage.setItem("shoppingcart",JSON.stringify(cart));
    
}

function loadCart(){
    
    cart = JSON.parse(localStorage.getItem("shoppingcart"));
    
}

function getallItemsCount(){
    
    var total = 0;
    
    for(var i=0; i < cart.length; i++){
        
        total += cart[i].count;
        
    }
    
    
    return total;
}

function getTotalCost(){
    
      var total = 0;
    
    for(var i=0; i < cart.length; i++){
        
        total += cart[i].count * cart[i].price;
        
    }
    
    return total;
}

function displayCart(){
    
    var arr = cartListCopy();
    
    var out = "";
    
    for(var i = 0; i < cart.length; i++){
        
        out += '<div class="blockbox">'
    +'<img src='+cart[i].path+' style="height:200px;">'
    +'<script src="cart.js"></script>'
    +'<span>'+cart[i].title+'.<div id ="preis">'
    +' <div id=preissum>'+cart[i].totalpreis+' $</div>'
+'<button type="submit" name="submit" class="button" onclick="incrementCart(\''+cart[i].name+'\')" style="height:0px; width: 0px; background-color:' +'white;"><img src=imgs/plus.png style="height: 50px;width: 50px; "></button>'
+'<button type="submit" name="submit" class="button" onclick="removeOneItemfromCart(\''+cart[i].name+'\')"; style=" margin-left: 50px; height: 0px;' +'width:0px; background-color: white;"><img src=imgs/minus.png style="height: 45px;width: 45px; "></button>'
+'<div class = "del">'
+'<button type="submit" name="submit" class="button" onclick="removeItem(\''+cart[i].name+'\')" style=" margin-left: 50px; height: 0px; width: 0px;' +'background-color: white;"><img src=imgs/del.png style="height: 70px;width: 55px; "></button></div><div id ="num">'+cart[i].count+'</div>'
    +'</div></span>'
    +'</div><div id="line"><hr></div>';
        
    }
    
   document.getElementById("cart").innerHTML = out + '<div id="total">Total : '+getTotalCost()+' $</div>';
    
}

function displayCart2(){
    
    var arr = cartListCopy();
    
    var out = "";
    
    for(var i = 0; i < cart.length; i++){
        
        out += '<div class="blockbox">'
    +'<img src='+cart[i].path+' style="height:100px;">'
    +'<script src="cart.js"></script>'
    +'<span>'+cart[i].title+'.<div id ="preis" style="height:100px;">'
    +'<div id=preissum>'+cart[i].totalpreis+' $</div>'
    +'<div id ="num" style="font-size:25px;">'+cart[i].count+'</div>'
    +'</div></span>'
    +'</div><div id="line2" style="margin-top:10px;"><hr></div>';
        
    }
    
   document.getElementById("cart2").innerHTML = out;
   
}


$(document).ready(function() {
	
$('#test').click(function(event) {
		  
	var kart = JSON.stringify(cart);
	var name = $('#test').val();
		
   $.ajax({
			
		    type: 'POST',
		    url: 'Payment_Form',
		    data: {submit:name},
		    success:function(result){
		    	
		   $('#cart').html(result);
			    
			    }
		});
		
	});
});


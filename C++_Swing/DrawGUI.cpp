#include <iostream>
#include <string>
#include <fstream>

using namespace std;

char* xyDecToHex(int intValue)          // Wandeln X,Y-Decimal zu HEX um
{
    char *CharRes = new (char);
    sprintf(CharRes, "%X", intValue);
	
	char *CharRes2 = new (char);
	
	if(intValue > -1 && intValue < 16){
		
		*CharRes2 = *CharRes;
		*CharRes = 48;
		CharRes++;
		*CharRes = 48;
		CharRes++;
		*CharRes = *CharRes2;
		CharRes++;
		*CharRes='\0';
		CharRes -= 3;
		
	}else if(intValue > 15 && intValue < 256){
		
		*CharRes2 = *CharRes;
		CharRes++;
		CharRes2++;
		*CharRes2 = *CharRes;
		CharRes--;
		CharRes2--;
		*CharRes = 48;
		CharRes++;
		*CharRes = *CharRes2;
		CharRes++;
		CharRes2++;
		*CharRes = *CharRes2;
		CharRes++;
		*CharRes='\0';
		CharRes -= 3;
	}
    
	return CharRes;
}

char* rgbDecToHex(int intValue)       // Wandeln R,G,B-Decimal zu HEX um
{
    char *CharRes = new (char);
    sprintf(CharRes, "%X", intValue);
	
	char *CharRes2 = new (char);
	
	if(intValue > -1 && intValue < 16){
		
		*CharRes2 = *CharRes;
		*CharRes = 48;
		CharRes++;
		*CharRes = *CharRes2;
		CharRes++;
		*CharRes='\0';
		CharRes -= 2;
	}
    
	return CharRes;
}

string hexAll(int x,int y,int red,int green,int blue){  // X,Y,R,G,B-Werte zusammenfügen
	
	string hexall= "";
	
	string strx = xyDecToHex(x);
	string stry = xyDecToHex(y);
	string strred = rgbDecToHex(red);
	string strgreen = rgbDecToHex(green);
	string strblue = rgbDecToHex(blue);
	
	hexall = strx+stry+strred+strgreen+strblue;
	
	return hexall;
}

string hexXY(int x,int y){  // X,Y-Werte zusammenfügen
	
	string hexxy= "";
	
	string strx = xyDecToHex(x);
	string stry = xyDecToHex(y);
	
	hexxy = strx+stry;
	
	return hexxy;
}

void sendhexAllToSwing(int x,int y,int red,int green,int blue){  // Schicken die X,Y,R,G,B-Werte nach Java-Swing
	
   cout<<hexAll(x,y,red,green,blue)<<"\n";
   cout.flush();
   
}

void sendhexXYToSwing(int x,int y){   // Schicken die X,Y-Werte nach Java-Swing

   cout<<hexXY(x,y)<<"\n";
   cout.flush();
	
}

#include <iostream>
#include <cmath>
#include <iostream>
#include "DrawGUI.cpp"
#include <set>
#include <iterator>
#include <complex>

using namespace std;

struct Position{
  int x;
  int y;
  Position(int x_=0, int y_=0){x=x_;y=y_;}
};

class Shape{

protected:
  Position _position;
  int _red;
  int _green;
  int _blue;

public:
virtual double area();
virtual std::string shapeType();
void typeZeigen(Shape*);
std::string getPixel(int,int);
void setPixel(int,int,int,int,int);
void floodFill(int,int,int,int,int,int,int);
void floodFill2(int,int,int,int,int,int,int);
int getJframeHeight();
int getJframeWidth();

};


void Shape::typeZeigen(Shape* s){

std::cout<<s->shapeType()<<std::endl;

}

std::string Shape::shapeType(){

std::string s = "Das ist ein Shape";

return s;
}

double Shape::area(){


return 0;

}

void Shape::setPixel(int x,int y,int red,int green,int blue){
	
sendhexAllToSwing(x,y,red,green,blue);

}

std::string Shape::getPixel(int x,int y){
	
sendhexXYToSwing(x,y);

string line;

cin>>line;

cout<<line<<"\n";

return line;

}

int Shape::getJframeHeight(){
	
int height = 0;
	
cout<<"H"<<"\n";
	
string line;

cin>>line;

height = stoi(line);

return height;
	
}

int Shape::getJframeWidth(){
	
int width = 0;
	
cout<<"W"<<"\n";
	
string line;

cin>>line;

width = stoi(line);

return width;
	
}

void Shape::floodFill(int x,int y,int red,int green,int blue,int jframeheight,int jframewidth){
	
	
	if((x > jframewidth) || (y > jframeheight) || (x < 0) || (y < 0)
	||(getPixel(x, y).compare("238,238,238") != 0)){
		
		return;
		
	} else if ((x < jframewidth) && (y < jframeheight) &&
      (getPixel(x, y).compare("238,238,238") == 0)){
		
		setPixel(x,y,red,green,blue);
		
	     floodFill(x+1,y,red,green,blue,jframeheight,jframewidth); // east
         floodFill(x,y+1,red,green,blue,jframeheight,jframewidth); // south
         floodFill(x-1,y,red,green,blue,jframeheight,jframewidth); // west
         floodFill(x,y-1,red,green,blue,jframeheight,jframewidth); // north
		
  }
	}
	
void Shape::floodFill2(int x,int y,int red,int green,int blue,int jframewidth,int jframeheight){
	
	set<Position*> Points;
	set<Position*>::iterator itr;
	
	Position* p = new Position(x,y);
	
	Points.insert(p);
	
	while(!Points.empty()){
		
		itr = Points.begin();
		Points.erase(itr);
		
		Position* pbegin = *itr;
		
		if((pbegin->x < jframewidth) && (pbegin->x > 0)&&
		(pbegin->y < jframeheight) && (pbegin->y > 0) &&
      (getPixel(pbegin->x,pbegin->y).compare("238,238,238") == 0)){
			
			setPixel(pbegin->x,pbegin->y,255,0,255);
			
			Position* pright = new Position(pbegin->x+1,pbegin->y);
			Points.insert(pright);
			Position* pleft = new Position(pbegin->x-1,pbegin->y);
			Points.insert(pleft);
			Position* psouth = new Position(pbegin->x,pbegin->y+1);
			Points.insert(psouth);
			Position* pnorth = new Position(pbegin->x,pbegin->y-1);
			Points.insert(pnorth);
			
		}
		
	}
	
}
	  
class Rectangle : public Shape {

protected:

  int _width;
  int _height;
 
public:
  Rectangle(int x=0, int y=0, int width=0,int height=0,int red=0,int green=0,int blue=0);
  void draw();
  //double area();
  std::string shapeType();

};

Rectangle::Rectangle(int x, int y, int width,int height,int red,int green,int blue){

  _position = Position(x,y);
  _width = width;
  _height = height;
  _red = red;
  _green = green;
  _blue = blue;
}

std::string Rectangle::shapeType(){

std::string s = "Das ist ein Rectangle";

return s;

}

void Rectangle::draw(){

int i = _position.x;
int w = _width;

while(w >= 0){

setPixel(i,_position.y,_red,_green,_blue);

setPixel(i,_position.y+_height,_red,_green,_blue);

i++;

w--;

}

int i2 = _position.y;
int h  = _height;

while(h >= 0){
	
setPixel(_position.x,i2,_red,_green,_blue);

setPixel(_position.x+_width,i2,_red,_green,_blue);
	

i2++;

h--;

}

}

class MandelBrot:public Shape {
 
public:
  MandelBrot();
  void draw(int,int,int);
  std::string shapeType();
  int value(int,int,int,int,int);

};

MandelBrot::MandelBrot(){
	
  
}

std::string MandelBrot::shapeType(){

std::string s = "Das ist ein Mandelbrot";

return s;

}

int MandelBrot::value (int x, int y,int width,int height,int iteration)  {
    complex<float> point((float)x/width-1.5, (float)y/height-0.5);
    complex<float> z(0, 0);
    int nb_iter = 0;
    while (abs (z) < 2 && nb_iter <= iteration) {
        z = z * z + point;
        nb_iter++;
    }
    if (nb_iter < iteration)
       return (255*nb_iter)/20;
    else 
       return 0;
}

void MandelBrot::draw(int width,int height,int iteration){
	
 for (int i = 0; i < width; i++) {
   for (int j = 0; j < height; j++)  {
          
		  int val = value(i, j,width,height,iteration);
		  
		  setPixel(i,j,val,0,0);
                
            }
        }
}

int main() {
	
	Rectangle* r = new Rectangle(200,200,250,250,100,0,255);
	
	r->draw();
	
	//r->floodFill2(201,201,255,0,255,r->getJframeWidth(),r->getJframeHeight());
	
	//MandelBrot* mb = new MandelBrot();
	
	//mb->draw(mb->getJframeWidth(),mb->getJframeHeight(),1000);
	
    return 0;
}
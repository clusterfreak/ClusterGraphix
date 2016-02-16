package cluster;
/**
 * Point with 2D coordinates
 * @version 1.3.0 (01-24-2016)
 * @author Thomas Heym
 */
class Point2D { 
	double x,y=0.0;
	Point2D(double xi, double yi){
		x=xi; y=yi;
	}
/**
 * Convert Point2D to PointPixel
 * @param pixelOffset
 * @return
 */
	public PointPixel toPointPixel(int pixelOffset){
		PointPixel pointPixel = new PointPixel(0,0);
		int x=0; 
		int y=0;
		double o; 
		double p;
	    for(int t=0;t<pixelOffset;t++){
		  o = (double)t/pixelOffset;
		  p = o+(double)1/pixelOffset;
		  p=Math.round(p*100.)/100.;
		  if((this.x>o) & (this.x<=p))x=t;  
		  if((this.y>o) & (this.y<=p))y=t;
		  //Attention! 0 and 1 are excluded as extreme values
	    }
	    pointPixel.x=x;
	    pointPixel.y=y;
		return pointPixel;
	}
}
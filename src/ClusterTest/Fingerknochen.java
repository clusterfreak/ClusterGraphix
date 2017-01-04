package ClusterTest;

import java.util.*;
  
class Fingerknochen
{
  public static void main( String args[] )
  {
    GregorianCalendar cal = new GregorianCalendar();

    System.out.println( cal.getTime() );

    for ( int month=Calendar.JANUARY;
          month<=Calendar.DECEMBER;
          month++ )
    {
      cal.set( Calendar.MONTH, month );

      System.out.println( (month+1) + " : " +
        cal.getActualMaximum(Calendar.DAY_OF_MONTH) );
    }
  }
}

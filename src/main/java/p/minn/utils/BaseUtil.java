package p.minn.utils;


/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class BaseUtil 
{

	
	/**
	 * Prints a byte array
	 * @param bytes
	 * @return
	 */
	
	public static String print ( byte [ ] bytes )
	{
		
		String result = "";
		
		for ( byte actual : bytes ) result += BaseUtil.getHexaValue( actual & 0xff ) + " ";
		return result;

	}
	
	/**
	 * Splits value into two four-bit parts, returns their hexa value
	 * @param valueX
	 * @return
	 */
	
	public static String getHexaValue ( int valueX )
	{

		String result = "";
		
		result += getHexaBits( valueX >> 4 );
		result += getHexaBits( valueX & 0x0F );
		
		return result;

	}
	
	/**
	 * Returns hexa value
	 * @param valueX
	 * @return
	 */
	
	public static String getHexaBits ( int valueX )
	{

		switch ( valueX )
		{
		
			case 15 : return "F";
			case 14 : return "E";
			case 13 : return "D";
			case 12 : return "C";
			case 11 : return "B";
			case 10 : return "A";
			default : return "" + valueX;
		
		}
		
	}
	
	

}

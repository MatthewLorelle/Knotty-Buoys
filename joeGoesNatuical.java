import com.sailsim.app.NavInterfaceClient;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
public class joeGoesNautical
{
	public static final String username = "Knotty_Buoys";
	public static final String password = "isogonal";
	public static NavInterfaceClient goat = new NavInterfaceClient();
	public static float[] optHead = new float[2];
	public static float[][] allVect = new float[181][91];
	
	public static boolean connect() throws IOException
	{
		Scanner input = new Scanner(System.in);
		System.out.println( "Input Server name" );
		String server = input.nextLine();
		System.out.println("Input port #");
		int port = input.nextInt();
		goat.connect( server , port , username , password );
		return goat.isConnected();
	}
		
	public static float[] maxVector() throws IOException
	{
		float max = 0;
		goat.send("obstacleScan 1");
		float point = goat.receive[0];
		goat.send("windHeading");
		float windHead = Float.parseFloat(goat.receive()[0]);
		goat.send("windStrength");
		float windSpeed = Float.parseFloat(goat.receive()[0]);
		for( int boat = point; boat <= 180 + point; boat++ )
		{
			for( int sail = 0; sail <= 90; boat++ )
			{
				allVect[boat][sail] =	windSpeed * (float) Math.sin( ( coordinateConverter(boat) + coordinateConverter(sail) ) - coordinateConverter(windHead) );
				if( allVect[boat][sail] > max )
					max = allVect[boat][sail];
					optHead[0] = boat;
					optHead[1] = sail;
			}
		}
		return optHead;
	}
	
	public static void main(String args[]) throws IOException
	{
		connect();
		while( goat.isConnected()  )
		{
			goat.send("boatHeading 45");
			goat.receive();
			goat.send("sailAngle 15");
			goat.receive();
			goat.send("anchor up");
			goat.receive();

		}
	}
}
import com.sailsim.app.NavInterfaceClient; 
import java.io.IOException;

public class SailSim
{
	private static String hostname = "209.141.56.247";
	private static int port = 20170;
	private static String username = "Knotty_Buoys";
	private static String password = "isogonal";
	private static NavInterfaceClient navAPI = new NavInterfaceClient();
	private static boolean connected;
	public static String[] msg = new String[2];
	
	public static void connect() throws IOException 
	{
		connected = navAPI.connect(hostname, port, username, password);
	}
	
	public static String sendReceive(String data) throws IOException
	{
		String a = " ";
		return a;
	}
	
	public static void main (String[] args) throws IOException
	{
		connect();
		
		if (connected) 
			{
				System.out.println("YES");
			}
			else
			{
				System.out.println("no");
			}
		
		int count = 0;
		while (navAPI.isConnected())
		{
			navAPI.send("anchor up");
			navAPI.receive();

			navAPI.send("obstacle 1");
			System.out.println( navAPI.receive()[1]);

			
			navAPI.send("boatPosition");
			System.out.println( navAPI.receive()[1]);
			//navAPI.receive();
			//System.out.println("");
			
			navAPI.send("boatHeading  55.491535");
			//System.out.println( navAPI.receive()[1]);
			navAPI.receive();

			System.out.println("");
			
			navAPI.send("sailAngle 25");
			navAPI.receive();

			navAPI.send("obstacleScan");
			System.out.println( navAPI.receive()[1]);
			//msg[0] = navAPI.receive()[1];
			
			
			
			
			
			
			//System.out.println( navAPI.receive()[1]);
			//System.out.println("");
			//System.out.println(msg[1]);
		}
	}
}
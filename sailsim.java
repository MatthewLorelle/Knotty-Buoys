import com.sailsim.app.NavInterfaceClient;
import java.io.IOException;

public class sailsim {

    public static NavInterfaceClient api = new NavInterfaceClient();

    public float headingConversion() throws IOException{
        //convert to boatHeading string type to a float
        api.send("boatHeading");
        float angle = Float.parseFloat(api.receive()[1]);
        float convertedangle = 0;

        if (0 < angle && angle < 90)
            convertedangle = 90 - angle;

        if (90 < angle && angle < 180)
            convertedangle = 180 - angle + 270;

        if (180 < angle && angle < 270)
            convertedangle = 270 - angle + 180;

        if (270 < angle && angle < 360)
            convertedangle = 270 - angle + 180;

        if (angle == 0)
            convertedangle = 90;

        if (angle == 90)
            convertedangle = 0;

        if (angle == 180)
            convertedangle = 270;

        if (angle == 270)
            convertedangle = 180;


        return convertedangle;
    }

    public void connection() {

        boolean connected;
        String host = "209.141.56.247";
        int port = 20170;
        String username = "Knotty_Buoys";
        String password = "isogonal";
        try
        {
            connected = api.connect(host,port,username,password);
            if (connected)
                System.out.println("Connection established");
            else
                System.out.println("Connection failed");
        }

        catch (IOException e)
        {
            System.err.println("IOException");
        }
    }

    public static void main(String[] args) throws IOException{

        sailsim x = new sailsim();
        x.connection();

        api.send("anchor false");
        api.receive();
        api.send("boatHeading 315");
        api.receive();
        api.send("sailAngle 20");
        api.receive();

        System.out.println(x.headingConversion());

    }

}
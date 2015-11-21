package netcalcclnt;

/**
 * @title NetCalcClnt - Client/server Java demo
 * @author Stavros Kalapothas - Pervasive Computing
 */

import java.io.*;
import java.net.*;

public class NetCalcClnt {

    // main - supports command line argument
    public static void main(String[] args) {

        // declarations of basic connection settings
	int port=18500;			// server port
	String strPort="",		// server port
	ip="",                          // server ip
	UserName="";            	// client username

        // object creation handling keyboaard inputs
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	try
	{
		// show messages
		System.out.println("\n\nWelcome to NetCalc.\n\n");

		// wating to enter server ip
		System.out.print("\n\nServer IP address: ");
		ip = input.readLine();
		if (ip.equals(""))
			ip = "127.0.0.1";	// default IP
			// enter port
		System.out.print("Enter Port number: ");
		strPort = input.readLine();
		if (strPort.equals(""))
			port = 18500;			// default port
		else
			port = Integer.parseInt(strPort);
			// enter user name
		UserName = "Stavros";
		do
		{
			System.out.print("Username: ");
			UserName = input.readLine();
		}
		while (UserName.equals(""));	// wait until a name is given
			// open socket with server
		Socket socket = new Socket(ip, port);
		System.out.print("\n\n\t\tConnection successful!\n\t\t");
			// default declarations
		String Choice = "a",                    // variable
			   rdint1 = "45",		// 1st number
			   rdint2 = "2",		// 2nd number
			   defoper = "-",		// default operation
			   strResult="";
		// insntiate objects needed to send/receive to/from server
		InputStream in = socket.getInputStream(); //data read from socket
		BufferedReader br = new BufferedReader(new InputStreamReader(in)); //add to buffer
		OutputStream out = socket.getOutputStream(); //send data to socket
		PrintWriter pr = new PrintWriter(out, true); //send text
			// send username
		pr.println(UserName);
			// operations
		while (Choice.charAt(0) != 'x')
		{
			// ui
			System.out.println("\n\n      E(x)it from server.\n" +
				"      Press any other key to calculate.  ");
			Choice = input.readLine();
			if (Choice.equals(""))
				Choice = "a";
			switch(Choice.charAt(0))
			{
				case 'x':
					pr.println("x");
					break;
				default:
					pr.println("a");
					System.out.print("Enter 1st integer: ");
					rdint1 = input.readLine();
					if (rdint1.equals(""))
						rdint1 = "12";
						System.out.print("Enter 2nd integer: ");
					rdint2 = input.readLine();
					if (rdint2.equals(""))
						rdint2 = "2000";
						System.out.print("Enter your operand: ");
					defoper = input.readLine();
					if (defoper.equals(""))
						defoper = "+";
					// send the two numbers and the operation to server
					pr.println(rdint1);
					pr.println(rdint2);
					pr.println(defoper);
					// read result from server
					strResult = br.readLine();
					System.out.println(strResult);
					break;
			}
		}
			// close connection
		socket.close();
		}
	// catch exception
        catch(Exception e)
	{
		// print exception
                System.out.println("Something went wrong! Exception : "+e);
		System.exit(0); // exit
	}
   }

}

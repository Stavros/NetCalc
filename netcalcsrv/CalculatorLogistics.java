/**
 * @title CalculatorLogistics - Client/server Java demo
 * @author Stavros Kalapothas - Pervasive Computing
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class CalculatorLogistics extends Thread {
        Socket socket;
	Hashtable OnlineUsers;
	String UserName;

	// thread code constructor
	public CalculatorLogistics(Socket s, Hashtable online, String userName)
	{
		socket = s;
		OnlineUsers = online;
		UserName = userName;
	}

	// thread code
	public void run()
	{
		try
		{
			// instantiate basic objects to handle the i/o streams
			InputStream in = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			OutputStream out = socket.getOutputStream();
			PrintWriter pr = new PrintWriter(out, true);

			// object creation handling keyboaard inputs
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			// create strings to hold choice and result
                        String choice="", result="";

                        // loop
			while(!choice.equals("x"))
			{
				// ui
                                result = ""; // reset variable
				choice = br.readLine();	// read user entry
				System.out.println("User option is " + choice);
				switch(choice.charAt(0))
				{
					case 'x':
						System.out.println("User disconnects.");
						break;
					case 'a':
						System.out.println("Please wait calculating...\n");
						String rdint1 = br.readLine();
						String rdint2 = br.readLine();
						String rdOp = br.readLine();
						int int1 = Integer.parseInt(rdint1);
						int int2 = Integer.parseInt(rdint2);
						char chOp = rdOp.charAt(0);
						String FinalResult = "";
						switch(chOp)
						{
							case '+':
								FinalResult = "Result: "+(int1+int2); break;
							case '-':
								FinalResult = "Result: "+(int1-int2); break;
							case '*':
								FinalResult = "Result: "+(int1*int2); break;
							case '/':
								FinalResult = "Result: "+(int1/int2); break;
							default:
								FinalResult = "The Operand is invalid."; break;
						}
						pr.println(FinalResult);

						break;
				}
			}

			// release user from socket and close socket
			Socket s = (Socket)OnlineUsers.remove(UserName);
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

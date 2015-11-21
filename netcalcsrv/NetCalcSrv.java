/**
 * @title NetCalcSrv - Client/server Java demo
 * @author Stavros Kalapothas - Pervasive Computing
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class NetCalcSrv {

    public static void main(String[] args) {
        // TODO code application logic here
        try
	{
		// server socket
		ServerSocket ss = new ServerSocket(18500);
		// populate a hashtable with clients' usernames
		Hashtable OnlineUsers = new Hashtable(10);
		// accept cleint connections
		while(true)
		{
			Socket socket = ss.accept();	// accept client socket
			System.out.println("New user connected!"); //notify on user connection
			// instantiate basic objects to handle the i/o streams
			InputStream in = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			OutputStream out = socket.getOutputStream();
			PrintWriter pr = new PrintWriter(out, true);
			// enter username and socket to hashtable
			String UserName = br.readLine();
			System.out.println("Username: " + UserName + "\n");
			OnlineUsers.put(UserName, socket);
				// for every connection call CalculatorLogistics on a seperate thread
			CalculatorLogistics cl = new CalculatorLogistics(socket, OnlineUsers, UserName);
			cl.start(); // execute each thread
		}
		}
	// catch exception
        catch(Exception e)
	{
		// print exception
                System.out.println("Something went wrong! Exception : "+e);
		System.exit(0); // exit
    }
}

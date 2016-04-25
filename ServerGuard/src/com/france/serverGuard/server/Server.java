package com.france.serverGuard.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import org.hyperic.sigar.SigarException;

import com.france.serverGuard.test.StatusUtil;

public class Server {

	public static void main(String[] args) throws IOException, SigarException {  
        ServerSocket server = new ServerSocket(9876);  
        Socket client = server.accept();  
        BufferedReader in = new BufferedReader(new InputStreamReader(  
                client.getInputStream()));  
        PrintWriter out = new PrintWriter(client.getOutputStream());  
        while (true) {  
            String str = in.readLine();  
            if("C".equals(str)||"c".equals(str)){
            	out.println(StatusUtil.getCpu());
            }else if("M".equals(str)||"m".equals(str)){
            	out.println(StatusUtil.getMem());
            }else if("N".equals(str)||"n".equals(str)){
            	out.println(StatusUtil.getNet());
            }else if("O".equals(str)||"o".equals(str)){
            	out.println(StatusUtil.getOs());
            }
            else out.println("收到其他信息,请重试");
            out.flush();  
            if ("Q".equals(str)||"q".equals(str))  
                break;  
        }  
        client.close();  
    }  
}

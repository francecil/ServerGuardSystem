package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	static Socket server;

	public static void main(String[] args) throws Exception {
		System.out.println("请输入以下字符来获取服务器信息（不区分大小写）");
		System.out.println("C:CPU信息,M:内存状态,N:网络信息,O:操作系统信息,Q:退出系统");
		server = new Socket(InetAddress.getLocalHost(), 9876);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = wt.readLine();
			out.println(str);
			out.flush();
			if ("Q".equals(str)||"q".equals(str)) {
				break;
			}
			System.out.println(in.readLine());
		}
		server.close();
	}
}

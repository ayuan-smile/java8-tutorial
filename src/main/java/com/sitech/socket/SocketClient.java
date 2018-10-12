package com.sitech.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws Exception {
        InputStream is = null;
        OutputStream os = null;

        Socket socket = new Socket("localhost", 5888);//创建Socket对象，指明需要连接的服务器的地址和端口号
        is = socket.getInputStream();
        os = socket.getOutputStream();
        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);
        //dos.writeUTF("hello");//连接建立后，通过输出流向服务器端发送请求信息

        //OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
        pw.write("用户名：admin；密码：123");
        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("我是客户端，服务器说：" + info);
        }
        /*
        String s = null;
        if((s = dis.readUTF()) != null){
            System.out.println(s);//通过输入流获取服务器响应的信息
        }*/
        dos.close();
        dis.close();
    }
}

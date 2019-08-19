package com.smile.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * socket服务器端
 * <p>
 * SocketServer实现了多线程的socket编程，接收每一个socket连接的请求。SocketClient实现了socket的客户端。
 * 调试的时候，多开几个SClient，测试多线程
 * </p>
 * @author: ayuan
 * @create: 2013-8-13-13:41
 */
public class MultiThreadSocketServer extends ServerSocket{
    private static final int port = 8999;
    public MultiThreadSocketServer() throws IOException {
        super(port);
        try{
            while(true){
                System.out.println("等待连接ing....");
                Socket socket = accept();
                // 为每一个socket都建立一个线程
                new SevrerThread(socket);
                System.out.println("生成的socket的hash值："+socket.hashCode());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    class SevrerThread extends Thread{
        private Socket subServer;
        private BufferedReader in;
        private PrintWriter out;
        public SevrerThread(Socket socket)  throws UnsupportedEncodingException,IOException {
            subServer = socket;
            in = new BufferedReader(new InputStreamReader(subServer.getInputStream(),"UTF-8"));
            out = new PrintWriter(subServer.getOutputStream(),true);
            start();
            System.out.println("连接成功....");
        }
        @Override
        public void run() {
            try{
                String line = "";
                if(in!=null){
                    // 阻塞读取一行
                    line = in.readLine();
                }
                while(line!=null&&!line.equals("bye")){
                    String msg = createMessage(line);
                    out.println(msg);
                    if(in!=null)
                        // line 在in中没有内容时得到null值
                        line = in.readLine();
                }
                out.println("see you!");
                in.close();
                out.close();
                subServer.close();
                System.out.println("断开连接ing....");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        private String createMessage(String msg){
            return "You has said:"+msg;
        }
    }
    public static void main(String[] args) throws IOException {
        new MultiThreadSocketServer();
    }
}

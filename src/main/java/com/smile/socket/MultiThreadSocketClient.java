package com.smile.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * socket客户端
 * @author: ayuan
 * @create: 2013-8-13-13:41
 */
public class MultiThreadSocketClient {
    private Socket cl;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader reader;
    private String outMsg = "";
    public MultiThreadSocketClient(){
        try{
            System.out.println("连接服务器ing....");
            cl = new Socket("127.0.0.1",8999);
            if(cl!=null){
                System.out.println("连接成功-_-");
                in = new BufferedReader(new InputStreamReader(cl.getInputStream(),"GB2312"));
                out = new PrintWriter(cl.getOutputStream(),true);
                while(true){
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    out.println(reader.readLine());
                    outMsg = in.readLine();
                    System.out.println(outMsg);
                    if(outMsg.equals("see you!")){
                        break;
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                reader.close();
                in.close();
                out.close();
                cl.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MultiThreadSocketClient();
    }
}

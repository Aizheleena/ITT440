import java.io.*;
import java.net.*;

public class MyClient {

public static void main (String[] args){

try{

Socket s = new Socket("192.168.68.138",6666);

DataOutputStream dout = new DataOutputStream (s.getOutputStream());

dout.writeUTF("Hello Server");

dout.flush();

dout.close();

s.close();

}catch(Exception e){System.out.println(e);}

}

}

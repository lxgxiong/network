package lab3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Receiver {
	
	private InetAddress address;
	private int windowSize;
	private int maxSeq;
	private int droppedPacketNumber;
	private DatagramSocket receiveSocket;
	private ArrayList<Integer> window;
	
	public Receiver() {
		try {
			this.address= InetAddress.getByName("localhost");
			this.receiveSocket= new DatagramSocket(9876);
			this.windowSize=0;
			this.maxSeq=0;
			this.droppedPacketNumber=0;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveInitialPacket(){
		byte buf[] = new byte[1024];
		DatagramPacket initialPacket = new DatagramPacket(buf, buf.length);
		try {
			this.receiveSocket.receive(initialPacket);

			//this.windowSize=Integer.valueOf(buf.toString().split(":")[0]);
			//this.maxSeq=Integer.valueOf(buf.toString().split(":")[1]);
			byte buffer[]="Receive confirmation from the receiver".getBytes();
			System.out.println(buffer.toString());
			this.receiveSocket.send(new DatagramPacket(buffer,buffer.length ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		receiver.receiveInitialPacket();
	}
}

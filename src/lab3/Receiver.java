package lab3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
	public static void main(String[] args) {
		try {
			DatagramSocket receiverSocket = new DatagramSocket(9876);
			byte[] rcvData = new byte[1024];
			DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
			receiverSocket.receive(rcvPkt);
			InetAddress IPAddress = rcvPkt.getAddress();
			int port = rcvPkt.getPort();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

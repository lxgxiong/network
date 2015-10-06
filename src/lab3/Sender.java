package lab3;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Sender {
	
	private InetAddress address;
	private int windowSize;
	private int maxSeq;
	private int droppedPacketNumber;
	private DatagramSocket sendSocket;
	private ArrayList<Integer> window;
	
	public Sender(int windowSize, int maxSeq, int droppedPacketNumber) {
		this.windowSize = windowSize;
		this.maxSeq = maxSeq;
		this.droppedPacketNumber = droppedPacketNumber;
		this.window = new ArrayList<Integer>();
		try {
			this.sendSocket=new DatagramSocket(9877);
			this.address=InetAddress.getByName("localhost");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void sendInitialPacket(){
		String str = windowSize+":"+maxSeq;
		DatagramPacket sendPacket = new DatagramPacket(str.getBytes(),
				str.getBytes().length, this.address, 9876);
		
		DatagramPacket confirmPacket = new DatagramPacket(new byte[1024], 1024);
		
		try {
			System.out.println("Send window's size, maximum seq. number to the receiver");
			sendSocket.send(sendPacket);
			sendSocket.receive(confirmPacket);
			System.out.println(confirmPacket.getData().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		this.sendSocket.close();
	}
	
	public static void main(String args[]) {

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the window's size on the sender: ");
		int windowSize = in.nextInt();
		System.out.print("Enter the maximum sequence number on the sender: ");
		int maxSeq = in.nextInt();
		System.out.print("Select the packet(s) that will be dropped: ");
		int droppedPacketNumber = in.nextInt();
		
		Sender sender = new Sender(windowSize, maxSeq, droppedPacketNumber);
		
		sender.sendInitialPacket();
		sender.close();

	}
}
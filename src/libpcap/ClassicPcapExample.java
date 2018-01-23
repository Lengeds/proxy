package libpcap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.tcpip.Tcp;

public class ClassicPcapExample {

public static void main(String[] args) {
         List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
         StringBuilder errbuf = new StringBuilder(); // For any error msgs

/***************************************************************************
* First get a list of devices on this system
**************************************************************************/
int r = Pcap.findAllDevs(alldevs, errbuf);
if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
      System.err.printf("Can’t read list of devices, error is %s", errbuf.toString());
      System.out.println("----------:"+errbuf.toString()); 
      return;
}

System.out.println("Network devices found:");

int i = 0;
for (PcapIf device : alldevs) {
String description =
(device.getDescription() != null) ? device.getDescription()
: "No description available";
System.out.printf("#%d: %s [%s]\n", i++, device.getName(), description);
}

PcapIf device = alldevs.get(2); // We know we have atleast 1 device
System.out.printf("\nChoosing ‘%s’ on your behalf:\n",(device.getDescription() != null) ? device.getDescription():device.getName());

/***************************************************************************
* Second we open up the selected device
**************************************************************************/
int snaplen = 64 * 1024; // Capture all packets, no trucation
int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
int timeout = 10 * 1000; // 10 seconds in millis
Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

if (pcap == null) {
System.err.printf("Error while opening device for capture: "
+ errbuf.toString());
System.out.println("在此返回");
return;
}

/***************************************************************************
* Third we create a packet handler which will receive packets from the
* libpcap loop.
**************************************************************************/
final Map<PcapPacket,Long> map = new HashMap<PcapPacket,Long>();
PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

	
  public void nextPacket(PcapPacket packet, String user) {
	//String header="IP";
	/*System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s-4d %s\n",
         new Date(packet.getCaptureHeader().timestampInMillis()),
         packet.getCaptureHeader().caplen(), // Length actually captured
         packet.getCaptureHeader().wirelen(), // Original length
         user, // User supplied object
         packet.getCaptureHeader().getStructName()
    );*/
	 
	  Tcp tcp = packet.getHeader(new Tcp()); 
	   if(tcp!=null){
		   System.out.println("tcp输出："+tcp.getUTF8Char(1));
	   }
	   
  }
};

/***************************************************************************
* Fourth we enter the loop and tell it to capture 10 packets. The loop
* method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
* is needed by JScanner. The scanner scans the packet buffer and decodes
* the headers. The mapping is done automatically, although a variation on
* the loop method exists that allows the programmer to sepecify exactly
* which protocol ID to use as the data link type for this pcap interface.
**************************************************************************/
pcap.loop(-1, jpacketHandler, "jNetPcap rocks!");

/***************************************************************************
* Last thing to do is close the pcap handle
**************************************************************************/
pcap.close();
}
}
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class BitTorrentDriver {
    public static void main(String[] args) throws FileNotFoundException {
        BitTorrent client = new BitTorrent("onePhrase.txt", 5);
        BitTorrent client2 = new BitTorrent("twoPhrases.txt", 6);
        BitTorrent client3 = new BitTorrent("DavidBowie.txt", 7);
//        BitTorrent client4 = new BitTorrent("FleetwoodMac.txt", 8);

        //Create torrent
        client.torrentPieces();
        System.out.println(client.printTorrent());
        client2.torrentPieces();
        System.out.println(client2.printTorrent());
        client3.torrentPieces();
        System.out.println(client3.printTorrent());
//        client4.torrentPieces();
//        System.out.println(client4.printTorrent());


        //Stitch hashes to produce original text
        ArrayList<Integer> hash = new ArrayList<>();
        ArrayList<Integer> hash2 = new ArrayList<>();
        ArrayList<Integer> hash3 = new ArrayList<>();
        ArrayList<Integer> hash4 = new ArrayList<>();
//        One Phrase
        hash.add(1664561561);
        hash.add(259921423);
        hash.add(1794146334);
        System.out.println(client.stitch(hash));

        //        TwoPhrases
        hash2.add(-1941036129);
        hash2.add(-414570365);
        System.out.println(client2.stitch(hash2));

//        David Bowie
        int bowie1 = -1152798046, bowie2 = 758242152, bowie3 = -1721051824;
        hash3.add(bowie1);
        hash3.add(bowie2);
        hash3.add(bowie3);
        System.out.println(client3.stitch(hash3));

//        Fleetwood Mac
        int fleetwood1 = 1909016033, fleetwood2=-958060482, fleetwood3 =-1360305793, fleetwood4 =-338557054, fleetwood5=-1994021557, fleetwood6 =1676822872;
         hash.add(-1994021557);
        hash.add(1676822872);
        hash4.add(fleetwood1);
        hash4.add(fleetwood2);
        hash4.add(fleetwood3);
        hash4.add(fleetwood4);
        hash4.add(fleetwood5);
        hash4.add(fleetwood6);
//        System.out.println(client4.stitch(hash4));

        //Test swarm
        System.out.println("ONE PHRASE");
        client.tracker(5);
        System.out.println(client.printSwarm());

        System.out.println("TWO PHRASES");
        client2.tracker(4);
        System.out.println(client2.printSwarm());

        System.out.println("DAVID BOWIE");
        client3.tracker(3);
        System.out.println(client3.printSwarm());

//        System.out.println("FLEETWOOD MAC");
//        client4.tracker(2);
//        System.out.println(client4.printSwarm());


    }

}

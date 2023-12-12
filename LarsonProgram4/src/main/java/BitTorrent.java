// CSCI 211
// Trey Larson
// Student ID: 10933974
// Program 4
// Due Wednesday, November 15, 2023.
// In keeping with the UM Honor Code, I have neither given nor
// received assistance from anyone other than the instructor/TA/tutor.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BitTorrent implements BitTorrentADT {
    private HashMap<Integer, Piece> torrent;
    private HashMap<String, ArrayList<Integer>> index;
    //private ArrayList<Piece> pieces;
    private ArrayList<Seed> swarm;
    private ArrayList<String> titles;
    private int peers;
    private String filename;



    public BitTorrent(String filename, int peers) {
        this.torrent = new HashMap<>();
        this.index = new HashMap<>();
        this.swarm = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.peers = peers;
        this.filename = filename;
    }

    /**
     * method torrentPieces that reads from a file populating the torrent and index HashMaps
     * @throws FileNotFoundException
     */
    public void torrentPieces() throws FileNotFoundException {
        //TODO:  Read data in from the file - oneline represents one "piece" of the torrent
        //       Use count to keep track of the "order" of the Piece. If count is 0 you know that the line your read in is the title
        //       Add this title to the "titles" ArrayList (you can't add to "index" until your torrent is complete)
        //       If count is greater than 0 you should add the piece to the "torrent" hashmap and its hash to the "hash" ArrayList. Don't forget to increment count
        //       If the line your read in is "END", this is the end of this torrent - you now should add to the "index" hashmap
        //       You also need to reset count and hash for the next torrent.
        //
        int count = 0;
        String oneLine, title = "";
        Scanner in = new Scanner(new File(this.filename));
        ArrayList<Integer> hashes = new ArrayList<>();

        while (in.hasNextLine()) {
            oneLine = in.nextLine();
            // the end of the torrent
            if (oneLine.equals("END")) {
                // add to the index hashmap
                index.put(title, new ArrayList<>(hashes));
                // then reset count and hash
                hashes.clear(); // important for the tracker method
                count = 0;
                // count is 0, the line your read in is the title
            } else if (count == 0) {
                title = oneLine;
                titles.add(title);
                count++;
                // else if it is greater than 0, add the piece to the torrent hashmap.
            } else {
                // hash each piece
                int hash = oneLine.hashCode();
                // create a piece object and use it to get the information from each count in the text file
                Piece piece = new Piece(count, title, oneLine);
                // then put the piece into the torrent hashmap
                torrent.put(hash, piece);
                hashes.add(hash); // very important for tracker method
                count++;
            }
        }
    }


    /**
     * method stitch that combines the pieces based on the "order"
     * @param hash
     * @return
     */
    public String stitch(ArrayList<Integer> hash){
        //TODO:  Read in the hash values, sort by the "order" and stitch (i.e., combine), separating each piece with a \n (newline)
        //       I've provided the line that sorts, calling the private class PieceComparator that I've also given
        String output = "";

        ArrayList<Piece> oneTorrent = new ArrayList<>();
        for (Integer i : hash) {
            Piece piece = torrent.get(i);
            oneTorrent.add(piece);
        }
        //Use the line below to sort your torrent by the order - I've called my variable "oneTorrent", yours may be different
        Collections.sort(oneTorrent, new PieceComparator());

        for (Piece piece : oneTorrent) {
            output += piece.getOnePiece() + "\n";
        }
        return output;
    }

    /**
     * method tracker that adds the torrent to the swarm, duplicated
     * @param duplicates
     */
    public void tracker(int duplicates) {
        //TODO: DO NOT CHANGE THE seed on the random number generator
        // For each title:
        //    Retrieve the ArrayList of hash values from the "index" Hashmap
        //    Using this Arraylist, get the "pieces" from "Torrent"
        //    Now generate a random number from 0 to (peers-1). Recall the number of peers was defined in the constructor
        //    You are ready to add a "Seed" to the "swarm" ArrayList
        Random gen = new Random(17L); // dont change
        HashMap<Integer, ArrayList<Integer>> peerMap = new HashMap<>();

        // use a for each to get the titles
        for (String title : titles) {
            // create an ArrayList to get the index hash values
            ArrayList<Integer> hash = index.get(title);
            for (int hashValues : hash) {
                Piece piece = torrent.get(hashValues);
                peerMap.put(hashValues, new ArrayList<>());
                ArrayList<Integer> temp = peerMap.get(hashValues);

                for (int i = 0; i < duplicates; i++) {
                    int random = gen.nextInt(peers);
                    while (temp.contains(random)) {
                        random = gen.nextInt(peers);
                    }
                    temp.add(random);
                    Seed seed = new Seed(title, hashValues, piece, random);
                    // add the seed to the swarm
                    swarm.add(seed);
                }
            }
        }
    }


    /**
     * method printTorrent given that prints the key and value of the torrent Hashmap
     * @return
     */
    public String printTorrent() {
        String output = "";
        for( Map.Entry<Integer, Piece> onePiece : torrent.entrySet() )
            output += onePiece.getKey() + ": " + onePiece.getValue() + "\n";

        return output;
    }

    /**
     * method printSwarm given that prints each seed in the "swarm" ArrayList
     * @return
     */
    public String printSwarm() {
        String output = "";
        Integer temp=0;
        for(Seed oneSeed: swarm) {
            if(temp != oneSeed.getHash()) {
                temp = oneSeed.getHash();
                output += oneSeed.getHash() + ": " + oneSeed.getPiece() + "\n" + oneSeed.getPeerNumber();
            }
            else {
                output += oneSeed.getPeerNumber() ;
            }
            output += "\n";
        }
        return output;
    }

    /**
     * private Piececomparator class used to sort a torrent's "pieces" based on its "order"
     */
    private class PieceComparator implements Comparator<Piece> {

        // override the compare() method
        public int compare(Piece p1, Piece p2)
        {
            if (p1.getOrder() == p2.getOrder())
                return 0;
            else if (p1.getOrder() > p2.getOrder())
                return 1;
            else
                return -1;
        }
    }
}

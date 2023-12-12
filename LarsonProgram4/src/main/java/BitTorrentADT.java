import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface BitTorrentADT {
    public void torrentPieces() throws FileNotFoundException;
    public String stitch(ArrayList<Integer> hash);
    public void tracker(int duplicates);
}

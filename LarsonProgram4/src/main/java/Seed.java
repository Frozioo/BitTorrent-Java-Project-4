import java.util.ArrayList;

public class Seed {
    private String title;
    private int hash;
    private Piece onePiece;
    private int peerNumber;

    public Seed(String title, int hash, Piece onePiece,  int peerNumber) {
        this.title = title;
        this.hash = hash;
        this.onePiece = onePiece;
        this.peerNumber = peerNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
    public String getPiece(){
        return this.onePiece.getOnePiece();
    }
    public int getPeerNumber(){
        return peerNumber;
    }

}

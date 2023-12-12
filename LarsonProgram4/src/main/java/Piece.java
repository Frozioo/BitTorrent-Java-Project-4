public class Piece {
    private int order;
    private String title;
    private String onePiece;


    public Piece(int order, String title, String onePiece) {
        this.order = order;
        this.title = title;
        this.onePiece = onePiece;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOnePiece() {
        return onePiece;
    }

    public void setOnePiece(String onePiece) {
        this.onePiece = onePiece;
    }
    public String getTitle() {
        return title;
    }
    public String toString(){
        return order + ": " + onePiece;
    }
}

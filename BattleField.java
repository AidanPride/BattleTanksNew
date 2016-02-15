
public class BattleField {
    final int BF_WIDTH = 590;
    final int BF_HEIGHT = 590;
    private String[][] battleField = { { "B", "B", "B", "B", "B", "B", "B", "B", "B" },
                                       { "B", " ", "B", "B", " ", " ", " ", " ", "B" },
                                       { "B", "B", "B", " ", "B", "B", "B", "B", "B" },
                                       { "B", "B", "B", " ", " ", " ", "B", "B", "B" },
                                       { "B", "B", "B", " ", "B", "B", "B", "B", "B" },
                                       { "B", "B", " ", "B", "B", "B", " ", "B", "B" },
                                       { "B", "B", " ", "B", "B", " ", " ", "B", "B" },
                                       { "B", " ", " ", "B", "B", "B", "B", " ", "B" },
                                       { "B", " ", " ", "B", "B", "B", "B", "B", "B" } };

    public BattleField() {
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public String[][] getBattleField() {
        return battleField;
    }

    public String scanQuadrant(int x , int y){
       return battleField[x][y];
    }

    public void updateQuadrant(int x , int y , String field){
        this.battleField[x][y] = field;
    }

    public int getDimentionX(){
        return  9;
    }

    public int getDimentionY(){
        return  9;
    }

}

package PvZ;

public class GameWonException extends GameEventException{
    public int numSunTokens;
    public GameWonException(int numSunTokens){
        super();
        this.numSunTokens = numSunTokens;
    }
}

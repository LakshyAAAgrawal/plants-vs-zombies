package PvZ;

public class GameEndException extends Exception{
    public int numSunTokens;
    public GameEndException(int numSunTokens){
        super();
        this.numSunTokens = numSunTokens;
    }
}

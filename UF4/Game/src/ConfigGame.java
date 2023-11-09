package Proyectos_MP03.UF4.Game.src;

public class ConfigGame {

    public int level;
    public int numPlayers;

    public ConfigGame() {
        this.level = 1;
        this.numPlayers = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}

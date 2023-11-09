package Proyectos_MP03.UF4.Game.src;

public class Player {

    public String name;
    public int points;

    public Player(String name) {
        this.name = name;
        points = (int) (Math.random() * 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

}

package Proyectos_MP03.UF4.Game.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    ConfigGame cg = new ConfigGame();
    MenuExample menu = new MenuExample(this,cg);
    List<Player> players = new ArrayList<>();

    public void start(){
        menu.mostrarMenu();
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        String namePlayer;

        System.out.println("Level of the game: " + cg.getLevel());
        System.out.println("Playing....");
        System.out.println("There is " + cg.getNumPlayers() +" players");

        for (int i = 0; i < cg.getNumPlayers(); i++) {
            System.out.print("Enter player's name: ");
            namePlayer = sc.nextLine();
            Player player = new Player(namePlayer);

            players.add(player);
            System.out.println(player.toString());
        }

        System.out.println("...Finished game");


    }
}

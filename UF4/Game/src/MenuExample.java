package Proyectos_MP03.UF4.Game.src;

import java.util.Scanner;

public class MenuExample {

    Game game;
    ConfigGame cg;
    public MenuExample(Game game, ConfigGame cg) {

        this.game = game;
        this.cg = cg;
    }



    public void mostrarMenu() {
       Scanner sc = new Scanner(System.in);

       int respuesta;

       do {
       System.out.println("Main Menu");
       System.out.println("1. Play\n2. Setting\n0. Exit");

       respuesta = sc.nextInt();

       switch (respuesta) {
           case 1:
              game.play();
               break;
           case 2:
               settingMenu();
               break;
           case 0:
               System.out.println("Thank you for playing");
               break;
           default:
               System.out.println("You must choose an option between 0 - 2");
       }

       } while (respuesta != 0);

    }

    public void settingMenu() {
        Scanner sc = new Scanner(System.in);

        int respuesta;
        do {
            System.out.println("Settign Menu");
            System.out.println("1. Players\n2. Game\n0. Return");

            respuesta = sc.nextInt();

            switch (respuesta) {
                case 1:
                    int numPlayer;
                    System.out.print("Number of players (Maximum 4)? ");
                    numPlayer = sc.nextInt();
                    if (numPlayer > 4){
                        System.out.println("Maximum number of players is 4, try again");
                    } else {
                        cg.setNumPlayers(numPlayer);
                    }
                    break;
                case 2:
                    int leveDifficulty;
                    System.out.print("Choose difficulty (1 - 5): ");
                    leveDifficulty = sc.nextInt();
                    if (leveDifficulty > 5) {
                        System.out.println("Maximum difficulty 5, try again");
                    } else  {
                        cg.setLevel(leveDifficulty);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("You must choose an option between 0 - 2");
            }
        } while (respuesta != 0);

    }

}

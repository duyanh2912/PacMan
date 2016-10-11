/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.util.Scanner;
import pacman.logic.Game;

/**
 *
 * @author Andy
 */
public class pacmanUI {

    private Scanner reader;
    private Game game;

    public pacmanUI(Game game) {
        this.game = game;
        this.reader = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println(game.map());
            String input = "";
            while (true) {
                System.out.print("Your next move? ");
                String st = reader.nextLine().toLowerCase();
                switch (st) {
                    case "a":
                        input = st;
                        break;
                    case "d":
                        input = st;
                        break;
                    case "w":
                        input = st;
                        break;
                    case "s":
                        input = st;
                        break;
                    default:
                        break;
                }
                if (!input.isEmpty()) {
                    break;
                }
            }
            if (!game.play(input)) {
                System.out.println("YOU LOST!!!");
                break;
            }
            if (game.getFoods().isEmpty()) {
                System.out.println("YOU WON!!!");
                break;
            }
        }
    }
}

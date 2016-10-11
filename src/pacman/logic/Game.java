/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import pacman.domain.Food;
import pacman.domain.Ghost;
import pacman.domain.Item;
import pacman.domain.Player;

/**
 *
 * @author Andy
 */
public class Game {

    private Player player;
    private List<Food> foods;
    private List<Ghost> ghosts;

    public Game() {
        player = new Player(1, 1);

        foods = new ArrayList<Food>();
        foods.add(new Food(0, 0));
        foods.add(new Food(0, 1));
        foods.add(new Food(1, 0));

        ghosts = new ArrayList<Ghost>();
        ghosts.add(new Ghost(3, 0));
        ghosts.add(new Ghost(3, 3));
    }

    public boolean play(String input) {
        itemMoves(player, input);
        for (Iterator<Food> iterator = foods.iterator(); iterator.hasNext();) {
            Food next = iterator.next();
            if (player.samePlace(next)) {
                iterator.remove();
            }
        }
        if (foods.isEmpty()) {
            return true;
        }
        for (Ghost ghost : ghosts) {
            if (ghost.samePlace(player)) {
                return false;
            }
            while (true) {
                Ghost temp = new Ghost(ghost.getX(), ghost.getY());
                String move = "";
                switch (new Random().nextInt(4)) {
                    case 0:
                        itemMoves(temp, "a");
                        break;
                    case 1:
                        itemMoves(temp, "s");
                        break;
                    case 2:
                        itemMoves(temp, "w");
                        break;
                    case 3:
                        itemMoves(temp, "d");
                        break;
                    default:
                        break;
                }
                int n = 0;
                for (int i = 0; i < ghosts.size(); i++) {
                    if (temp.samePlace(ghosts.get(i))) {
                        n++;
                    }
                }
                for (Food food : foods) {
                    if (temp.samePlace(food)) {
                        n++;
                    }
                }
                if (n == 0) {
                    ghost.setX(temp.getX());
                    ghost.setY(temp.getY());
                    break;
                }
            }
            if (ghost.samePlace(player)) {
                return false;
            }
        }
        return true;
    }

    public List<Food> getFoods() {
        return foods;
    }

    private void itemMoves(Item item, String input) {
        switch (input) {
            case "a": {
                if (item.getX() > 0) {
                    item.moveLeft();
                }
                break;
            }
            case "d": {
                if (item.getX() < 3) {
                    item.moveRight();

                }
                break;
            }
            case "w": {
                if (item.getY() > 0) {
                    item.moveUp();

                }
                break;
            }
            case "s": {
                if (item.getY() < 3) {
                    item.moveDown();

                }
                break;
            }
            default:
                break;
        }
    }

    public String map() {
        String map = "";
        for (int i = 0; i < 4; i++) {
            outerloop:
            for (int j = 0; j < 4; j++) {
                if (player.getX() == j && player.getY() == i) {
                    map += "P ";
                    continue;
                }

                for (Food food : foods) {
                    if (food.getX() == j && food.getY() == i) {
                        map += "F ";
                        continue outerloop;
                    }
                }
                for (Ghost ghost : ghosts) {
                    if (ghost.getX() == j && ghost.getY() == i) {
                        map += "G ";
                        continue outerloop;
                    }
                }
                map += "- ";
            }
            map += "\n";
        }
        return map;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import pacman.logic.Game;

/**
 *
 * @author Andy
 */
public class PacMan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new pacmanUI(new Game()).run();
    }
    
}

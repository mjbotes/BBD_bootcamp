package com.bbd.java.tetris.controller;

import com.bbd.java.tetris.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class GameController {

        private Board board;

        @GetMapping("")
        public String showMenu() {
            return "index";
        }

        @GetMapping("game")
        public String showUpdateForm(Model model) {
            int i = (int)(Math.random() * 7);
            board = null;
            board = new Board(20, 10, i);
            model.addAttribute("board", board.makeTable());
            model.addAttribute("score", board.getScore());
            return "game";
        }


           @GetMapping("game/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        switch (id){
            case 1:
                board.rotS();
                break;
            case 2:
                board.moveSR();
                break;
            case 3:
                board.moveSL();
                break;
            case 4:
            default:
                int i = (int)(Math.random() * 7);
                board.moveSD(i);
                break;
        }
        model.addAttribute("board", board.makeTable());
        model.addAttribute("score", board.getScore());
        return "game";
    }

}

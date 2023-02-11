package kz.timka.shop.controllers;

import kz.timka.shop.dto.ResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping("/calc/add")
    public ResultDTO calcTwoSum(@RequestParam Integer a, @RequestParam Integer b) {
        return new ResultDTO(a + b);
    }
}

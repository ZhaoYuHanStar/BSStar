package zyh.code1.bsstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName HelloController
 * @Description
 * @Author 赵语涵
 * @Date 2020-10-26 22:39
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(name="name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}

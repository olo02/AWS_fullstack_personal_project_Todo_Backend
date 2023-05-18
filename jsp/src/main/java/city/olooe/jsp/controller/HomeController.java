package city.olooe.jsp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import city.olooe.jsp.service.HomeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "myName");
        model.addAttribute("time", homeService.getTime());
        return "index";
    }

    @GetMapping("list")
    public String getList(Model model) {
        model.addAttribute("list", homeService.getList());
        return "list";
    }

    @PostMapping("list")
    public String create(Integer no, String name, Integer score) {
        log.info("{}", name);
        log.info("{}", score);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("score", score);

        homeService.create(map);
        return "redirect:/list";
    }

    @GetMapping("remove")
    public String remove(Integer no) {
        log.info("{}", no);
        homeService.remove(no);
        return "redirect:/list";
    }

}

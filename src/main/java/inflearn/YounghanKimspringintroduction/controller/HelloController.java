package inflearn.YounghanKimspringintroduction.controller;

import inflearn.YounghanKimspringintroduction.domain.HelloDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello~");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("/hello-string")
    @ResponseBody
    public String helloSpring(@RequestParam("name") String name) {
        return "안녕 " + name;
    }

    /*
//    // 1. domain에 parameter가 여려 개 있는 경우
      // 객체 전체의 정보를 받아서 바로 반환
    @GetMapping("/hello-api")
    @ResponseBody
    public HelloDomain helloApi(@ModelAttribute HelloDomain helloDomain) {
        return helloDomain;
    }
     */

    // 2. domain에 parameter가 여려 개 있는 경우
    // 인자를 1개씩 받고 객체에 담아서 반환
    @GetMapping("/hello-api")
    @ResponseBody
    public HelloDomain helloApi(
            @RequestParam("name") String name,
            @RequestParam("address") String address
    ) {
        HelloDomain helloDomain = new HelloDomain();

        helloDomain.setName(name);
        helloDomain.setAddress(address);

        return helloDomain;
    }
}

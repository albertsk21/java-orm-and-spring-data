package softuni.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;


@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return view("user/login");
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userRegisterModel",new UserRegisterModel());
        return view("user/register",modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView registerPOST(UserRegisterModel userRegisterModel){
        this.userService.registerUser(userRegisterModel);
        return redirect("/home");
    }
}

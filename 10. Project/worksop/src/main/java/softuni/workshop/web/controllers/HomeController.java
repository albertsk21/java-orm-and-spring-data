package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.*;

@Controller
public class HomeController extends BaseController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    private final ProjectService projectService;

    @Autowired
    public HomeController(CompanyService companyService, EmployeeService employeeService, RoleService roleService, UserService userService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }




    @GetMapping("/")
    public ModelAndView index(){
        boolean areImported = this.companyService.areImported() && this.employeeService.areImported()
                              && this.projectService.areImported();
        return super.view("index","areImported",areImported);
    }



}

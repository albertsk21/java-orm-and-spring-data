package softuni.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

@Controller
public class HomeController extends BaseController {

    private CompanyService companyService;
    private EmployeeService employeeService;
    private ProjectService projectService;

    public HomeController(CompanyService companyService, EmployeeService employeeService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        return view("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();

        boolean areImported = this.companyService.areImported() &&
                this.projectService.areImported() &&
                this.employeeService.areImported();
        modelAndView.addObject("areImported",areImported);
        return view("/home",modelAndView);
    }

}

package softuni.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private ProjectService projectService;
    private EmployeeService employeeService;

    public ExportController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/project-if-finished")
    public ModelAndView exportProjects(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projectsIfFinished",this.projectService.exportFinishedProjects());
        return view("export/export-project-if-finished", modelAndView);
    }

    @GetMapping("/employees-above")
    public ModelAndView exportEmployees(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employeesAbove",this.employeeService.exportEmployeesWithAgeAbove());
        return view("export/export-employees-with-age", modelAndView);
    }

}

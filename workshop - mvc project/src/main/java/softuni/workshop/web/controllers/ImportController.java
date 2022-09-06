package softuni.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private CompanyService companyService;
    private EmployeeService employeeService;
    private ProjectService projectService;
    private RoleService roleService;
    private UserService userService;


    public ImportController(CompanyService companyService,
                            EmployeeService employeeService,
                            ProjectService projectService,
                            RoleService roleService,
                            UserService userService
    ) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/xml")
    public ModelAndView mainImport(){

        boolean[] areImported = {
                this.companyService.areImported(),
                this.projectService.areImported(),
                this.employeeService.areImported()};

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("areImported",areImported);

        return view("xml/import-xml", modelAndView);
    }

    @GetMapping("/companies")
    public ModelAndView companies() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companies",this.companyService.readCompaniesXmlFile());
        return view("xml/import-companies",modelAndView);
    }
    @PostMapping("/companies")
    public ModelAndView companiesPOST() throws JAXBException, IllegalAccessException, InstantiationException {
        this.companyService.importCompanies();
        return redirect("/import/xml");
    }

    @GetMapping("/employees")
    public ModelAndView employees()  throws IOException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees",this.employeeService.readEmployeesXmlFile());
        return view("xml/import-employees",modelAndView);
    }
    @PostMapping("/employees")
    public ModelAndView employeePOST() throws JAXBException, IllegalAccessException, InstantiationException {
        this.employeeService.importEmployees();
        return redirect("/import/xml");
    }

    @GetMapping("/projects")
    public ModelAndView projects()  throws IOException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projects",this.projectService.readProjectsXmlFile());
        return view("xml/import-projects",modelAndView);
    }

    @PostMapping("/projects")
    public ModelAndView projectsPOST() throws JAXBException, IllegalAccessException, InstantiationException {
        this.projectService.importProjects();
        return redirect("/import/xml");
    }




}

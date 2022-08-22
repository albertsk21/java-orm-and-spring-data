package softuni.workshop.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView view(String viewName, ModelAndView modelAndView){
        modelAndView.setViewName(viewName);

        return modelAndView;
    }


    public ModelAndView view(String view, String objectName, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        modelAndView.addObject(objectName, object);

        return modelAndView;
    }

    public ModelAndView view(String viewName){
        return this.view(viewName, new ModelAndView());
    }

    public ModelAndView redirect(String url){
        return this.view("redirect:" + url);
    }
}


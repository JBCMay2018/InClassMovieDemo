package me.afua.moviedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MovieRepository movies;

    @RequestMapping("/")
    public String showList(Model model)
    {
        model.addAttribute("allMovies",movies.findAll());
        return "index";
    }

    @RequestMapping("/addmovie")
    public String addMovie(Model model)
    {
        model.addAttribute("aMovie",new Movie());
        return "addmovie";
    }

    @RequestMapping("/savemovie")
    public String saveMovie(@Valid @ModelAttribute("aMovie") Movie theMovie, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "addmovie";
        }

        movies.save(theMovie);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateMovie(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("aMovie",movies.findById(id).get());
        return "addmovie";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id, Model model)
    {
        movies.deleteById(id);
        return "redirect:/";
    }

}

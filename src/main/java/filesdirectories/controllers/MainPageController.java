package filesdirectories.controllers;

import filesdirectories.entities.Directory;
import filesdirectories.exceptions.FileNotExistsException;
import filesdirectories.exceptions.NotDirectoryException;
import filesdirectories.forms.AddDirectoryFormBean;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainPageController {

    @Autowired
    private DirectoryRepo directoryRepo;

    @Autowired
    private DirectoryService directoryService;

    @GetMapping(path = "/")
    public String mainPage(ModelMap model) {
        List<Directory> allDirs = Optional.ofNullable(directoryRepo.findAll()).orElseGet(ArrayList::new);
        model.addAttribute(allDirs);
        return "mainPage";
    }

    @PostMapping(path = "/addToList")
    public String addToList(@Validated @ModelAttribute("addDirFormBean") AddDirectoryFormBean bean,
                            BindingResult result) {


        try {
            directoryService.addDirectory(bean.getPath());
        } catch (FileNotExistsException e) {

        } catch (NotDirectoryException v) {

        }


        return "redirect:/";
    }


    @ModelAttribute("addDirFormBean")
    public AddDirectoryFormBean newFormBean() {
        AddDirectoryFormBean bean = new AddDirectoryFormBean();
        bean.setPath("Введите путь");
        return bean;
    }


}

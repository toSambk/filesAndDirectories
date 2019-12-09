package filesdirectories.controllers;

import filesdirectories.entities.Directory;
import filesdirectories.exceptions.FileNotExistsException;
import filesdirectories.exceptions.NotDirectoryException;
import filesdirectories.forms.AddDirectoryFormBean;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.service.DirectoryService;
import filesdirectories.viewRepresentation.DirectoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        List<DirectoryInfo> allDirs = Optional.ofNullable(directoryService.getDirectoryInfo()).orElseGet(ArrayList::new);
        model.addAttribute("addedDirectories", allDirs);
        return "mainPage";
    }

    @PostMapping(path = "/addToList")
    public String addToList(@Validated @ModelAttribute("addDirFormBean") AddDirectoryFormBean bean,
                            BindingResult result, ModelMap model) {
        if(directoryRepo.findByPath(bean.getPath()) == null) {
            try {
                directoryService.addDirectory(bean.getPath());
            } catch (FileNotExistsException | NotDirectoryException e) {
                result.addError(new FieldError("addDirFormBean", "path", e.getMessage()));
            }
        } else {
            result.addError(new FieldError("addDirFormBean", "path", "Данная директория уже добавлена"));
        }

        if(result.hasErrors()) {
            model.addAttribute("addedDirectories",
                    Optional.ofNullable(directoryService.getDirectoryInfo()).orElseGet(ArrayList::new));
            return "mainPage";
        } else {
            return "redirect:/";
        }

    }


    @ModelAttribute("addDirFormBean")
    public AddDirectoryFormBean newFormBean() {
        AddDirectoryFormBean bean = new AddDirectoryFormBean();
        return bean;
    }


}

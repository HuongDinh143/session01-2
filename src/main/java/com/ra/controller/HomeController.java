package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.service.CategoryService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }
    @GetMapping("add-category")
    public String add(Category category, Model model) {
        category.setCategoryStatus(true);
        model.addAttribute("category", category);
        return "add";
    }
    @PostMapping("/add-category")
    public String create(@ModelAttribute Category category) {
        if (categoryService.save(category) != null) {
            return "redirect:/";
        }else {
            return "redirect:/add-category";
        }
    }
    @GetMapping("edit-category")
    public String initEdit(Model model, @RequestParam Long id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "edit";
    }
    @PostMapping("/edit-category")
    public String edit(@ModelAttribute Category category) {
        if (category!=null){
            categoryService.save(category);
            return "redirect:/";
        }else {
            return "redirect:/edit-category?id="+category.getId();
        }
    }
    @GetMapping("delete")
    public String delete(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
        Category category = categoryService.findById(id);
        if (category!=null){
            categoryService.delete(id);

            return "redirect:/";
        }else {
           
            return "error";
        }
    }
}

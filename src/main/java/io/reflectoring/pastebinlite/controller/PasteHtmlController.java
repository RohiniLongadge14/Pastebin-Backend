package io.reflectoring.pastebinlite.controller;

import io.reflectoring.pastebinlite.bean.Paste;
import io.reflectoring.pastebinlite.service.PasteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.HtmlUtils;

import java.util.UUID;

@Controller
public class PasteHtmlController {

    @Autowired
    private PasteService service;

    @GetMapping("/p/{id}")
    public String view(@PathVariable("id") UUID id, HttpServletRequest httpServletRequest, Model model){
        try{
            Paste p = service.fetch(id, httpServletRequest);
            model.addAttribute("content", p.getContent());
            return "paste";
        } catch (Exception e) {
            return String.valueOf(new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
    }

}

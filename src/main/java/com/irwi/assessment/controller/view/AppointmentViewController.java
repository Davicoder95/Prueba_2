package com.irwi.assessment.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentViewController {

    @GetMapping
    public String getAppointmentsPage(Model model) {
        // Puedes agregar datos iniciales al modelo si es necesario
        model.addAttribute("pageTitle", "Gestión de Citas");
        return "appointments"; // Nombre de la plantilla Thymeleaf sin extensión .html
    }
}
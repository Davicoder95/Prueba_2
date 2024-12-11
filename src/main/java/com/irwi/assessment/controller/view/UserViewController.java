package com.irwi.assessment.controller.view;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.DoctorRequestDto;
import com.irwi.assessment.application.dtos.requests.PatientRequestDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.domain.enums.Roles;
import com.irwi.assessment.domain.ports.service.interfaces.IDoctorService;
import com.irwi.assessment.domain.ports.service.interfaces.IPatientService;
import com.irwi.assessment.domain.ports.service.interfaces.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public class UserViewController {

        private  IUserService userService;
        private  IDoctorService doctorService;
        private  IPatientService patientService;

        // Mostrar formulario para crear un usuario Admin
        @GetMapping("/create/admin")
        public String showCreateAdminForm(Model model) {
            // Puedes agregar algún modelo adicional si lo necesitas
            return "createAdmin";  // Nombre de la vista para crear un Admin
        }

        // Mostrar formulario para crear un usuario Doctor
        @GetMapping("/create/doctor")
        public String showCreateDoctorForm(Model model) {
            // Puedes agregar algún modelo adicional si lo necesitas
            return "createDoctor";  // Nombre de la vista para crear un Doctor
        }

        // Mostrar formulario para crear un usuario Patient
        @GetMapping("/create/patient")
        public String showCreatePatientForm(Model model) {
            // Puedes agregar algún modelo adicional si lo necesitas
            return "createPatient";  // Nombre de la vista para crear un Patient
        }

        // Manejar la creación de un usuario Admin
        @PostMapping("/register/admin")
        public String registerAdmin(@ModelAttribute UserRequestDto userRequestDto) {
            // Llamamos al servicio para registrar al Admin
            AuthResponseDto authResponse = userService.register(userRequestDto, Roles.ADMIN);

            // Puedes redirigir a una página de éxito o la lista de usuarios
            return "redirect:/users/list";  // Redirige a la lista de usuarios después de la creación
        }

        // Manejar la creación de un usuario Doctor
        @PostMapping("/register/doctor")
        public String registerDoctor(@ModelAttribute DoctorRequestDto doctorRequestDto) {
            // Llamamos al servicio para registrar al Doctor
            AuthResponseDto authResponse = doctorService.registerDoctor(doctorRequestDto);

            // Redirigimos a la lista de usuarios después de registrar al doctor
            return "redirect:/users/list";  // Redirige a la lista de usuarios
        }

        // Manejar la creación de un usuario Patient
        @PostMapping("/register/patient")
        public String registerPatient(@ModelAttribute PatientRequestDto patientRequestDto) {
            // Llamamos al servicio para registrar al Patient
            AuthResponseDto authResponse = patientService.registerPatient(patientRequestDto);

            // Redirigimos a la lista de usuarios después de registrar al paciente
            return "redirect:/users/list";  // Redirige a la lista de usuarios
        }

        // Lista de usuarios registrados (opcional)
        @GetMapping("/list")
        public String listUsers(Model model) {
            model.addAttribute("users", userService.readAll());
            return "userList";  // Vista con la lista de usuarios registrados
        }
}

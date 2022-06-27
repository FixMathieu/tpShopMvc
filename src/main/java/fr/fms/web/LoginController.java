package fr.fms.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login(Model model) {
		nameAuth(model);
		return "login";
	}

	@GetMapping("/loginAuth")
	public String loginAuth(Model model) {
		nameAuth(model);
		return "redirect:/index";
	}

	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping("/403")
	public String refused() {
		return "403";
	}
	public void nameAuth(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	// verifie utilisateur Connecte
		 String currentUserCo = auth.getName();											// recupere son nom
		 model.addAttribute("auth",currentUserCo);
	}

//	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
//	public ModelAndView indexView(HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("index");
//		String userName = "not logged in"; // Any default user name
//		Principal principal = request.getUserPrincipal();
//		if (principal != null) {
//			userName = principal.getName();
//		}
//
//		mv.addObject("username", userName); // By adding a little code (same way) you can check if user has any // roles
//											// you need, for example:
//		boolean fAdmin = request.isUserInRole("ROLE_ADMIN");
//		mv.addObject("isAdmin", fAdmin);
//		return mv;
//	}
}

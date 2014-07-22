package net.danko.spring.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.danko.spring.service.UserRoleService;
import net.danko.spring.service.UserService;
import net.danko.spring.support.PasswordGenerator;
import net.danko.spring.domain.User;
import net.danko.spring.domain.UserFormRegistration;
import net.danko.spring.domain.UserJsonObject;
import net.danko.spring.domain.UserRole;

@Controller
public class MyController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserRoleService userRoleService;
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
 
	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Home page");
	  model.addObject("message", "Example Spring Application");
	  model.setViewName("hello");
	  
	  return model; 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
	  ModelAndView model = new ModelAndView();
	  model.addObject("userList", userService.listUser());
	  model.setViewName("admin");
	  
	  return model; 
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "label.loginError");
	  }
 
	  if (logout != null) {
		model.addObject("msg", "label.logoutMsg");
	  }
	  model.setViewName("login");
 
	  return model; 
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView listUsers() {
		
		ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }	
	
	@RequestMapping(value = "/springPaginationDataTables", method = RequestMethod.GET, produces = 
	        "application/json")
	    public @ResponseBody String springPaginationDataTables() 
	        throws IOException {
		
		List<User> usersList = userService.listUser();

        UserJsonObject userJsonObject = new UserJsonObject();
        userJsonObject.setiTotalDisplayRecords(usersList.size());
        userJsonObject.setiTotalRecords(usersList.size());
        userJsonObject.setAaData(usersList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(userJsonObject);

        return json2;
    }
	
	@RequestMapping(value = "/checklanguage", method = RequestMethod.GET)
	public String checklanguage() {
		
		String url = "redirect:/login_success";
		UserDetails activeUser = (UserDetails)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		User user = userService.getUserByName(activeUser.getUsername());
		url = url + "?language=" + user.getLanguage();
		
        return url;
	}
	
	@RequestMapping(value = "login_success", method = RequestMethod.GET)
	public ModelAndView login_success() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("url", "You logged");
		model.addObject("message", "This is congratulations page:)");
		model.setViewName("login_success");
		
		return model;
	}
	
	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		
		ModelAndView model = new ModelAndView();
		List<String> languageList = new ArrayList<String>();
		languageList.add("ru");
		languageList.add("en");
		model.addObject("languageList", languageList);
		model.addObject("userFormRegistration", new UserFormRegistration());
		model.setViewName("registration");
		
		return model;		
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView addUser(@Valid @ModelAttribute("userFormRegistration") 
    		UserFormRegistration userFormRegistration,
            BindingResult result) throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			
			List<String> languageList = new ArrayList<String>();
			languageList.add("ru");
			languageList.add("en");
			model.addObject("languageList", languageList);
            model.setViewName("registration");            
        } else {        	
        	
        	model.setViewName("redirect:index");
        	User user = new User();
    		UserRole userRole = new UserRole();
    		
    		userRole.setRole("ROLE_USER");
    		user.setUsername(userFormRegistration.getUsername());
    		user.setLanguage(userFormRegistration.getLanguage());
    		user.setEnabled(true);
    		user.setPassword( PasswordGenerator.encoderMd5( userFormRegistration.getPassword() ) );
    		
            userService.addUser(user);
            user.getUserRoles().add(userRole);
            userRoleService.addUserRole(userRole, user.getUsername());
        }

        return model;
    }
	
	@RequestMapping("/delete/{username}")
    public String deleteContact(@PathVariable("username") String username) {

		userRoleService.removeUserRole(username);
        userService.removeUser(username);        

        return "redirect:/admin";
    }
 
	//for 403 access denied page
	@RequestMapping(value = "/error403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
 
	  ModelAndView model = new ModelAndView();
 
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
 
	  model.setViewName("error403");
	  return model;
 
	}
	
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	  public @ResponseBody String getTime(@RequestParam String name) {
	    String result = "Time for " + name + " is " + new Date().toString();
	    return result;
	  }
}

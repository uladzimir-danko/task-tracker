package net.danko.spring.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.danko.spring.domain.User;
import net.danko.spring.domain.UserFormRegistration;
import net.danko.spring.domain.UserJsonObject;
import net.danko.spring.domain.UserRole;
import net.danko.spring.service.UserRoleService;
import net.danko.spring.service.UserService;
import net.danko.spring.support.PasswordGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class UsersController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserRoleService userRoleService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
	  ModelAndView model = new ModelAndView();
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
	
	@RequestMapping(value = "/springAdminPaginationDataTables", method = RequestMethod.GET, produces = 
	        "application/json")
	    public @ResponseBody String springAdminPaginationDataTables() 
	        throws IOException {
		
		List<User> usersList = userRoleService.listUserDeveloper();

        UserJsonObject userJsonObject = new UserJsonObject();
        userJsonObject.setiTotalDisplayRecords(usersList.size());
        userJsonObject.setiTotalRecords(usersList.size());
        userJsonObject.setAaData(usersList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(userJsonObject);

        return json2;
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
        	
        	model.setViewName("redirect:admin");
        	User user = new User();
    		UserRole userRole = new UserRole();
    		
    		userRole.setRole("ROLE_DEVELOPER");
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
	
	@RequestMapping(value = "/editUser/{username}", method = RequestMethod.GET)
    public ModelAndView editeUser(@PathVariable("username") String username) {
		
		ModelAndView model = new ModelAndView();
		List<String> languageList = new ArrayList<String>();
		languageList.add("ru");
		languageList.add("en");
		model.addObject("username", username);
		model.addObject("languageList", languageList);
		model.addObject("userFormRegistration", new UserFormRegistration());
		model.setViewName("editUser");
		
		return model;		
	}
	
	@RequestMapping(value = "/editUser/{username}", method = RequestMethod.POST)
    public ModelAndView saveEditUser(@Valid @ModelAttribute("userFormRegistration")
    	UserFormRegistration userFormRegistration, BindingResult result,
            @PathVariable("username") String username) throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			
			List<String> languageList = new ArrayList<String>();
			languageList.add("ru");
			languageList.add("en");
			model.addObject("languageList", languageList);
            model.setViewName("/editUser/" + username);            
        } else {        	
        	
        	model.setViewName("redirect:/admin");
        	User user = new User();
    		
    		user.setUsername(username);
    		user.setLanguage(userFormRegistration.getLanguage());
    		user.setEnabled(true);
    		user.setPassword( PasswordGenerator.encoderMd5( userFormRegistration.getPassword() ) );
    		
            userService.updateUser(user);         
        }

        return model;
    }

}

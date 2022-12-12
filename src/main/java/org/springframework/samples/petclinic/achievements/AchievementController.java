package org.springframework.samples.petclinic.achievements;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesRepository;
import org.springframework.samples.petclinic.user.DuplicatedUserNameException;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("achievements")
public class AchievementController {

    private final String ACHIEVEMENTS_VIEW="/achievements/achievements";
    private final String ACHIEVEMENTS_FORM="/achievements/createOrUpdateAchievementForm";
    private final String PERSONAL_ACHIEVEMENTS_VIEW="/achievements/personalAchievements";

    private final UserService userService;
    private final AchievementService service;
	private final AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AchievementController(AchievementService service, UserService userService, AuthoritiesRepository authoritiesRepository){
        this.service=service;
        this.userService=userService;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping(value={"/", ""})
    public ModelAndView showAchievements() {
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_VIEW);
        addUserAndAuthoritiesToModelAndView(result);
        result.addObject("achievements", service.getAllAchievements());
        return result;
    }
    
    @GetMapping("/{id}")
    public ModelAndView showUserAchievementsEditForm() {
        ModelAndView result=new ModelAndView(PERSONAL_ACHIEVEMENTS_VIEW);
        addUserToModelAndView(result);  
        User u = (User) result.getModel().get("user");
        result.addObject("achievements", service.getAchievementsByUser(u.getUsername()));
        return result;
    }

    @Transactional(readOnly = true)
    @GetMapping("/new")
    public ModelAndView createAchievement(){
        Achievement achievement=new Achievement();
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);     
        addUserToModelAndView(result);  
        result.addObject("achievement",achievement);
        result.addObject("metrics",Arrays.asList(Metric.values()));        
        return result;
    }

    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewAchievement(@Valid Achievement achievement, BindingResult br){
        if(br.hasErrors()){
            return createAchievement().addAllObjects(br.getModel());
        }
        service.save(achievement);
        return showAchievements()
            .addObject("message", "The achievement was created successfully");
    }

    @Transactional()
    @GetMapping("/{id}/delete")
    public ModelAndView deleteAchievement(@PathVariable int id){
        service.deleteAchievementById(id);        
        return showAchievements();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}/edit")
    public ModelAndView editAchievement(@PathVariable int id){
        Achievement achievement=service.getAchievementById(id);        
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);
        addUserToModelAndView(result);  
        result.addObject("achievement", achievement);         
        result.addObject("metrics", Arrays.asList(Metric.values()));        
        return result;
    }

    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView saveAchievement(@PathVariable int id, @Valid Achievement achievement, BindingResult br){
        ModelAndView result=null;
        if(br.hasErrors()){
            return editAchievement(achievement.getId()).addAllObjects(br.getModel());
        }
        Achievement achievementToBeUpdated=service.getAchievementById(id);
        BeanUtils.copyProperties(achievement, achievementToBeUpdated,"id");
        service.save(achievementToBeUpdated);
        result = showAchievements();
        result.addObject("message", "The achievement was updated succesfully");
        return result;        
    }

    private void addUserToModelAndView(ModelAndView result) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        User u = userService.getUser(ud.getUsername()).get();
        result.addObject("user", u);
    }

    private void addUserAndAuthoritiesToModelAndView(ModelAndView result) {
        addUserToModelAndView(result);
        User u = (User) result.getModel().get("user");
        Authorities au = authoritiesRepository.findByName(u.getUsername());
		result.addObject("au", au);
    }

}

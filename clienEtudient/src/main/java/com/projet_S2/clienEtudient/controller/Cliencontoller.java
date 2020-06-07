package com.projet_S2.clienEtudient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.projet_S2.clienEtudient.model.Absence;
import com.projet_S2.clienEtudient.model.Etudien_t;
import com.projet_S2.clienEtudient.model.Etudient;
import com.projet_S2.clienEtudient.model.Remarque;
import com.projet_S2.clienEtudient.service.EtudientService;
import com.projet_S2.clienEtudient.service.Messanger;

@Controller
public class Cliencontoller {
    Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	EtudientService etudientService;
	
	@Autowired
	Messanger messanger;
	
	@GetMapping("/etudient")
	public ModelAndView accueil() {
	    Map<String,Etudien_t> model =new HashMap<String, Etudien_t>();
	    model.put("etudient", etudientService.etudientParCin(etudientService.getCinEtudientCurrent()));
		return new ModelAndView("home",model);
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		
	    Map<String,Etudient> model =new HashMap<String, Etudient>();
	    model.put("etudient", new Etudient());
	    return new ModelAndView("index",model);
	}
	
	@PostMapping("/")
	public  ModelAndView post(Etudient etudient) {
		
		if(etudientService.exist(etudient)) {
			RedirectView redirectView =new RedirectView();
			redirectView.setUrl("/etudient");
			etudientService.setCinEtudientCurrent(etudient.getCin());
			return new ModelAndView(redirectView);
			
		}else return new ModelAndView("index");
		
	}
	@PostMapping("/remarque")
	public ModelAndView postRemarque(Remarque remarque){
		etudientService.postRemarque(remarque);
		RedirectView redirectView=new  RedirectView();
		redirectView.setUrl("/EtudientList");
		return new ModelAndView(redirectView);
	}
	@GetMapping("/getremarque")
	public ModelAndView getRemarque(@RequestParam String cin){
		//messanger.sendMessage();
	    Map<String,Object> model =new HashMap<String, Object>();
	    model.put("etudient",etudientService.etudientparcin(cin));
	    Remarque remarque = new Remarque();
	    remarque.setCin(cin);	
	    model.put("remarque",remarque);
		return new ModelAndView("remarqueform",model);	
	}
	@PostMapping("/absence")
	public ModelAndView postAbsence(Absence absence){
		etudientService.postAbsence(absence);
		RedirectView redirectView=new  RedirectView();
		redirectView.setUrl("/EtudientList");
		return new ModelAndView(redirectView);
	}
	@GetMapping("/addabsence")
	public ModelAndView getAbsence(@RequestParam String cin){
		//messanger.sendMessage();
	    Map<String,Object> model =new HashMap<String, Object>();
	    Absence absence =new Absence();
	    absence.setCin(cin);
	    model.put("absence",absence);
	    model.put("etudient",etudientService.etudientparcin(cin));
		return new ModelAndView("absenceform",model);	
	}
	@PostMapping("/addetudient")
	public ModelAndView addEtudient(Etudient etudient){
		etudientService.addEtudient(etudient);
		return new ModelAndView("addetudient");
	}
	@GetMapping("/add_etudient")
	public ModelAndView addEtudient( ){
	    Map<String,Etudient> model =new HashMap<String, Etudient>();
	    model.put("etudient",new Etudient());
		return new ModelAndView("addetudient",model);	
	}
	@GetMapping("/EtudientList")
	public ModelAndView etudientList() {
        log.info("la page d'un etudient");
		 Map<String,List<Etudient>> model =new HashMap<String, List<Etudient>>();
		    model.put("etudientList", etudientService.etudientList());
			return new ModelAndView("EtudientList",model);	
	}
	@GetMapping("/Déconnexion")
	public ModelAndView deconnexion() {
		etudientService.setCinEtudientCurrent("");
		RedirectView redirectView =new RedirectView();
		redirectView.setUrl("/");
		return new ModelAndView(redirectView);}


}

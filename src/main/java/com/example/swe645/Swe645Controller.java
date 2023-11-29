package com.example.swe645;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/api")
public class Swe645Controller {

	    private final Swe645Repository swe645Repository;

    @Autowired
    public Swe645Controller(Swe645Repository swe645Repository) {
        this.swe645Repository = swe645Repository;
    }

	@GetMapping("/index")
	public String getIndex(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name,Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	@PostMapping(path = "/uploadData")
	public ResponseEntity<String> uploadData(@ModelAttribute SurveyModel model) {
		System.out.println(model.getFirstName());
		swe645Repository.save(model);
				return ResponseEntity.ok("Survey data created successfully.");
	}

	@GetMapping(path = "/deleteData/{id}")
	public ResponseEntity<String> uploadData(@PathVariable long id) {
		swe645Repository.deleteById(id);
		return ResponseEntity.ok("Survey data deleted successfully.");
	}

	@GetMapping(path = "/getDataById/{id}")
	public ResponseEntity<Object> getDataById(@PathVariable long id) {
		SurveyModel model = swe645Repository.findById(id).orElse(null);
		if(model!=null){
			return new ResponseEntity<>(model,HttpStatus.OK);
		}
		else{
			String message = "User with id " + id + " not exist.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping(path = "/updateData/{id}")
	public ResponseEntity<String> updateData(@PathVariable long id,@ModelAttribute SurveyModel surveyModel) {
		        SurveyModel model = swe645Repository.findById(id).orElse(null);
				if(model!=null){
					swe645Repository.save(surveyModel);
		return ResponseEntity.ok("Survey data updated successfully.");
				}
				else{
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Survey with id " + id + " not found.");
				}
	}

	@GetMapping("/getData")
	public ResponseEntity<List<SurveyModel>> getData(){
		return new ResponseEntity<>(swe645Repository.findAll(),HttpStatus.OK);
		// List<SurveyModel> surveyList = customRepository.findAll();
        // model.addAttribute("surveys", surveyList);
		// return surveyList;
	}

	
}


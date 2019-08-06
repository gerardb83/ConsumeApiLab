package co.grandcircus.ConsumeApiLab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConsumeApiLabController {

	@Autowired
	ConsumeApiLabApiService api;
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("tiny", "tinyList", api.getTinyList());
	}
	
	@RequestMapping("/complete")
	public ModelAndView completeList() {
		return new ModelAndView("complete", "completeList", api.getCompleteList());
	}
}

package laudhoot.rest.controller;


import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import laudhoot.core.domain.Shout;
import laudhoot.core.repository.TestRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/test")
public class TestController {

	@Autowired
	private Logger logger;
	
	@Autowired
	private TestRepository repository;
	
	@RequestMapping(value="/1")
	public @ResponseBody String test1(){
		logger.info("Executing Rest Test Con6troller : /1");
		Shout shout = new Shout();
		shout.setTestText("Apurve Gupta");
		repository.save(shout);
		return "Hello";
	}
	
	@RequestMapping(value="/2")
	public @ResponseBody String test2(){
		logger.info("Executing Rest Test Controller : /2");
		List<Shout> shouts = (List<Shout>) repository.findByTestText("Apurve");
		Iterator<Shout> itr = shouts.iterator();
		while(itr.hasNext()){
			logger.info("Executing Rest Test Controller : "+itr.next().getId());
		}
		Shout shout = repository.findOne((long) 2);
		logger.info("Executing Rest Test Controller : "+shout.getId());
		
		return "Hello";
	}

}
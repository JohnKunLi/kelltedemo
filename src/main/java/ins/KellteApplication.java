package ins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ins.task.TimingTask;

@RestController
@SpringBootApplication
public class KellteApplication {
	
	@Autowired
	private TimingTask timingTask;
	
	public static void main(String[] args) {
		SpringApplication.run(KellteApplication.class, args);
	}
	
	@RequestMapping(name = "/test")
	public Object test (){
		try{
			timingTask.calculatePremiuming();
		}catch (Exception e) {
			return "失败！";
		}
		return "成功！";
	}
	
	
}

package cathaybk.Midterm;

import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cathaybk.Midterm.svc.impl.MidtermServiceImpl;

@SpringBootApplication
public class MidtermApplication {

	public static void main(String[] args) {
		SpringApplication.run(MidtermApplication.class, args);
		List<String> a=MidtermServiceImpl.pokerCard();
		Map<Integer,List<String>> b =MidtermServiceImpl.dealCard1(4, a);

	}

}

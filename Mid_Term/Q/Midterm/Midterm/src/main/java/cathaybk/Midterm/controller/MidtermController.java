package cathaybk.Midterm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cathaybk.Midterm.DAO.CardDAO;
import cathaybk.Midterm.svc.MidtermService;

@RestController
@RequestMapping("/midterm")
@CrossOrigin("*")
public class MidtermController {

    @Autowired
    MidtermService midtermService;

    private final CardDAO cardDAO;

    public MidtermController(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @GetMapping(value = "/cards")
    public List<Map<String, Object>> getCards() {
        return cardDAO.getCards();
    }

    @ResponseBody
    @PostMapping(value = "/demoCode")
    public ResponseEntity<Map<String, Object>> demoCode(@RequestBody Map<String, String> demoMap) {
        return new ResponseEntity<>(midtermService.demoCode(demoMap), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/submit")
    public ResponseEntity<Map<String, Object>> submit(@RequestBody Map<String, String> map) {
        return new ResponseEntity<>(midtermService.submit(map), HttpStatus.OK);
    }

}

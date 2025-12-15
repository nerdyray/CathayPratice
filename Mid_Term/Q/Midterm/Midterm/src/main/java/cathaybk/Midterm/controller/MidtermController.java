package cathaybk.Midterm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cathaybk.Midterm.svc.MidtermService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/midterm")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MidtermController {

    @Autowired
    MidtermService midtermService;


    @ResponseBody
    @PostMapping(value = "/demoCode")
    public ResponseEntity<Map<String, Object>> demoCode(@RequestBody Map<String, String> map) {
        return new ResponseEntity<>(midtermService.demoCode(map), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/submit")
    public ResponseEntity<Map<String, Object>> submit(@RequestBody Map<String, String> map) {
        return new ResponseEntity<>(midtermService.submit(map), HttpStatus.OK);
    }


}

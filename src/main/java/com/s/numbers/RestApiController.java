package com.s.numbers;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestApiController {

    @Autowired
    RestTemplate rt;

    @GetMapping("/numbers")
    public Numbers getNumbers(@RequestParam("url") List<String> urls) {
        Numbers nums = new Numbers();
        List<Integer> numList = new ArrayList<>();
        for (String url : urls) {
            try {
                URL urlMain = new URL(url);
                System.out.println("URL is valid: " + urlMain);
                ResponseEntity<Numbers> res = rt.getForEntity(url, Numbers.class);
                numList.addAll(res.getBody().getNumbers());
            } catch (MalformedURLException e) {
                System.out.println("URL is invalid: " + url);
            }
        }
        nums.setNumbers(numList);
        return nums;
    }

    
}

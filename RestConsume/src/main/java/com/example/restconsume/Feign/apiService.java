package com.example.restconsume.Feign;
import com.example.restconsume.Entity.Student;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="service", url="http://localhost:80/")
public interface apiService {

@GetMapping(value = "/student")
@Headers(value = "Content-Type: application/json")
List<Object> getAll();

@GetMapping(value ="/student/{id}")
public Student getById(@PathVariable int id);

}

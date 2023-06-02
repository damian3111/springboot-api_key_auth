package com.example.springgatewaysecurity.config;

import com.example.springgatewaysecurity.dto.ApiKey;
import com.example.springgatewaysecurity.util.AppConstants;
import com.example.springgatewaysecurity.util.MapperUtil;
import com.sun.source.tree.BreakTree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class Controller {

    @GetMapping("/asd")
    public Object adssda(){
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("343C-ED0B-4137-B27E", Stream.of(AppConstants.STUDENT_SERVICE_KEY,
                AppConstants.COURSE_SERVICE_KEY).collect(Collectors.toList())));
        apiKeys.add(new ApiKey("FA48-EF0C-427E-8CCF", Stream.of(AppConstants.COURSE_SERVICE_KEY)
                .collect(Collectors.toList())));

        Map map = MapperUtil.objectMapper(apiKeys.get(0), Map.class);
        System.out.println(map);
        return null;
    }
}

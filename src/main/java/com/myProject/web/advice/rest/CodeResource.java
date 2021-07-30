package com.myProject.web.advice.rest;


import com.myProject.dto.CodeDto;
import com.myProject.service.CodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
public class CodeResource {
    private final CodeService codeService;
    public CodeResource(CodeService codeService) {
        this.codeService = codeService;
    }



    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CodeDto> codeDtoList = codeService.findAll();
        return new ResponseEntity<>(codeDtoList, HttpStatus.OK);
    }
}

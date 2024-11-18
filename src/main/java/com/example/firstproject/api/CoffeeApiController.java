package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired //게시글 repository 주입
    private CoffeeRepository coffeeRepository;
    //GET
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index(){
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    //POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create (@RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeDto.toEntity();
        if(coffee.getId() != null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        Coffee created = coffeeRepository.save(coffee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
    //PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Object> update (@PathVariable Long id, @RequestBody CoffeeDto coffeeDto){
        //1. DTO -> 엔티티 변환하기
        Coffee coffee = coffeeDto.toEntity();
        log.info("id: {}, Coffee: {}", id, coffee.toString());
        //2. 타깃 조회하기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if(target == null || id != coffee.getId()){
            //400 잘못 된 요청 응답
            log.info("잘못된 요청! id: {}, Coffee: {}", id, coffee.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. 업데이트 및 정상 응답(200) 하기
        target.patch(coffee);
        Coffee update = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
    //DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        //1. 대상 찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리하기
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //3. 대상 삭제하기
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
}

package com.example.demo.controller;
@RestController
@RequestMapping("/api/users")
public class UserAccountController{
    @AutoWired 
    UserAccountService uas;
    @PostMapping("/api/users/pot")
    public UserAccount createuser(@RequestBody UserAccount ua){
        return uas.createuser(ua);
    }
    @GetMapping("/api/users/gt")
    public list<UserAccount> getall(){
        return uas.getall();
    }
    @GetMapping("/api/users/gt{id}")
    public optional<UserAccount> getid(@pathvariable long id){
        return uas.getid(id);
    }
    @PutMapping("/api/users/upd{id}")
    public String updata(@PathVariable long id,@RequestBody UserAccount ua){
    Optional<UserAccount> UserA = uas.getid(id);

    if(UserA.isPresent()){
     usa.setId(id);
     usa.createuser(ua);

     return "Data Updated Successfully";
    }
    else{

        return id+ "not found";
    }
}
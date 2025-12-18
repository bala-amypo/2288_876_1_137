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
        uas.getall();
    }
    @GetMapping("/api/users/gt{id}")
    public optional<UserAccount> getid(@pathvariable long id){
        uas.getid(id);
    }
    @PutMapping("/api/users/upd{id}")
    public String updata(@PathVariable long id,@RequestBody UserAccount stu){
    Optional<Student> student = ser.fetchDataById(id);

    if(student.isPresent()){
     stu.setId(id);
     ser.createData( stu);

     return "Data Updated Successfully";
    }
    else{

        return id+ "not found";
    }

}
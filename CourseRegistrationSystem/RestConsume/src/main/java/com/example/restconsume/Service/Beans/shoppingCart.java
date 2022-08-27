package com.example.restconsume.Service.Beans;

import com.example.restconsume.Entity.Stu.Course;
import lombok.Synchronized;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.ManagedBean;
import java.util.*;

/*this bean by default is not in session scope
@scope("session") wouldnt work here since it's not web controller
these 2 annotation turn this into a bean of Session Scope , it ll be managed by spring session*/

@SessionScope
@ManagedBean

public class shoppingCart {

    private Map<Integer, Map<Integer,Course>> shoppingCart;

    public shoppingCart() {
        initalizeShoppingCart();
    }

    @Synchronized
    public void initalizeShoppingCart() {
        if(shoppingCart==null){
            shoppingCart=new HashMap<>();
        }
    }


    public void setShoppingCart(int studentId, int courseId, Course courseObject) {
        try {

            //if the map dont contain studentID, initalize a new LinkedHashMap
            if(!shoppingCart.containsKey(studentId)){
                shoppingCart.put(studentId,new LinkedHashMap<>());
                }

            //check if the course already exist in the inner LinkedHashMap
          if(shoppingCart.get(studentId).containsKey(courseId)){
                    throw new RuntimeException("course already exists"); }
          //else dont exist, add the course to the list
          else
           shoppingCart.get(studentId).put(courseId,courseObject);

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public int deleteCoursefromCart(){


        return 0;
    }

    //this method is used fetch specific student by id, then get the course he added to shopping cart
    public Map<Integer,Course> getList(int studentId){
        try {
            //check if outter Stu map is empty
            isStuMapEmpty();

            isCourCartMapEmpty(studentId);

                //return the inner LinkedHashMap
                return shoppingCart.get(studentId);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return shoppingCart.get(studentId);
    }

    public void deleteCourseFromCart(int courseId, int studentId) {
        try {
            isStuMapEmpty();
            isCourCartMapEmpty(studentId);

            if(shoppingCart.get(studentId).containsKey(courseId)){
                shoppingCart.get(studentId).remove(courseId);
            }


        }
        catch (Exception e){
            System.out.println(e);
        }
    }




    public void isStuMapEmpty() throws Exception {
        if (shoppingCart.isEmpty()) {
            throw new Exception("Map is empty, no student is inside");
        }

    }

    public void isCourCartMapEmpty(int studentId) throws Exception {
        if(shoppingCart.get(studentId).isEmpty()){
            throw new Exception("innerMap is empty, no course is added.");
        }

    }



}

package com.affle.iOS.facade;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerFacade {

    public static String emailAddress(String mailExtension){

        return new Faker(new Locale("en-US"))
                .bothify("???????######@"+mailExtension);
    }

    public static void getPersonName(){
        new Faker().name().fullName();
    }

}

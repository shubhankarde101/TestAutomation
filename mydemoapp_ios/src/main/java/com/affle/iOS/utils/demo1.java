package com.affle.iOS.utils;

 class Demo {

     public void method1() {
         System.out.println("Method 1");
     }

     public void method2() {
         this.method1(); // calls method1 using this keyword
         System.out.println("Method 2");
     }
 }

 class Demo1
    {

        public static void main(String[] args)
        {
            Demo d1 = new Demo();
            d1.method2();
        }
    }




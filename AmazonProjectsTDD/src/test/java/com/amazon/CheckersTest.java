
package com.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CheckersTest {

    WebDriver driver1;

    @Test
    public void checkerTest() {
        //Initiating Chrome Driver
        driver1 = new ChromeDriver();
        driver1.get("https://www.gamesforthebrain.com/game/checkers/");
        // verifying the current url to Confirm that the site is up
        String currentUrl = driver1.getCurrentUrl();
        if (currentUrl.contains("game/checkers/")) {
            System.out.println("Url is open");
        } else {
            System.out.println("Url is not open");
        }
        // Action class for drag and Drop
        Actions action = new Actions(driver1);

        // moving the first orange piece
        WebElement dragable1 = driver1.findElement(By.cssSelector("img[onclick='didClick(2, 2)']"));
        dragable1.click();
        WebElement dropable1 = driver1.findElement(By.cssSelector("img[onclick='didClick(1, 3)']"));
        dropable1.click();
        action.dragAndDrop(dragable1, dropable1).build().perform();

        // verifying next move after moving first piece
        String expresult1 = "Make a move.";
        String actresult1 = driver1.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult1.equals(actresult1)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        // moving the Second orange piece
        WebElement dragable2 = driver1.findElement(By.xpath("//img[@name='space42']"));
        dragable2.click();
        WebElement dropable2 = driver1.findElement(By.xpath("//img[@name='space53']"));
        dropable2.click();
        action.dragAndDrop(dragable2, dropable2).build().perform();

        // verifying next move after moving second piece
        String expresult2 = "Make a move.";
        String actresult2 = driver1.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult2.equals(actresult2)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        // moving the third orange piece
        WebElement dragable3 = driver1.findElement(By.xpath("//img[@name='space31']"));
        dragable3.click();
        WebElement dropable3 = driver1.findElement(By.xpath("//img[@name='space42']"));
        dropable3.click();
        action.dragAndDrop(dragable3, dropable3).build().perform();

        // verifying next move after moving third piece
        String expresult3 = "Make a move.";
        String actresult3 = driver1.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult3.equals(actresult3)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        // moving the fourth orange piece
        WebElement dragable4 = driver1.findElement(By.cssSelector("img[name='space11']"));
        dragable4.click();
        WebElement dropable4 = driver1.findElement(By.cssSelector("img[name='space33']"));
        dropable4.click();
        action.dragAndDrop(dragable4, dropable4).build().perform();

        // verifying next move after moving second piece
        String expresult4 = "Make a move.";
        String actresult4 = driver1.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult4.equals(actresult4)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        // moving the fifth orange piece
        WebElement dragable5 = driver1.findElement(By.cssSelector("img[name='space33']"));
        dragable5.click();
        WebElement dropable5 = driver1.findElement(By.cssSelector("img[name='space44']"));
        dropable5.click();
        action.dragAndDrop(dragable5, dropable5).build().perform();

        // verifying next move after moving second piece
        String expresult5 = "Make a move.";
        String actresult5 = driver1.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult5.equals(actresult5)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }
        // Restart
        driver1.findElement(By.xpath("//a[text()='Restart...']")).click();

        //Confirm that the restarting had been successful
        String expectedconfirmText = "Select an orange piece to move.";
        String actualconfirmText = driver1.findElement(By.xpath("//p[@id='message']")).getText();

        if (expectedconfirmText.equals(actualconfirmText)) {
            System.out.println("Restart has been successfully confirmed");
        } else {
            System.out.println("Restart unsuccessful");
        }
        driver1.close();
    }

}


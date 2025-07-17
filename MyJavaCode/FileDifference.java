package com.apple.phoenix.MyCode;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDifference {

    static String directoryPath1 = System.getProperty("user.dir") + "/src/main/java/com/apple/phoenix/MyCode/FileDemo/";


    public static void main(String[] args) {

        List<String> folder1 = getFiles("Folder1");
        List<String> folder2 = getFiles("Folder2");
        folder1.retainAll(folder2);
        folder1.stream().forEach(System.out::println);
        System.out.println("The difference of= bytes between two files with common name is");
        long diff = FileUtils.getFile(new File(directoryPath1 + "Folder1/" + folder1.get(0))).length() - FileUtils.getFile(new File(directoryPath1 + "Folder2/" + folder1.get(0))).length();
        System.out.println(Math.abs(diff));
    }

    public static List<String> getFiles(String folder) {
        String directoryPath = directoryPath1 + folder;
        Collection<File> allFiles1 = FileUtils.listFiles(new File(directoryPath), new String[]{"text"}, true);
        List<String> result = allFiles1.stream().map(x -> x.getName()).collect(Collectors.toList());
        return result;
    }
}

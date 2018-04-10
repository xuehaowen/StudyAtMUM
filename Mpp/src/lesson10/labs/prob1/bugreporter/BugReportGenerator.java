package lesson10.labs.prob1.bugreporter;

import java.io.*;
import java.util.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import lesson10.labs.prob1.classfinder.ClassFinder;

/**
 * This class scans the package lesson10.labs.prob2.javapackage
 * for classes with annotation @BugReport. It then generates a bug report
 * bugreport.txt, formatted like this:
 * <p>
 * Joe Smith
 * reportedBy:
 * classname:
 * description:
 * severity:
 * <p>
 * reportedBy:
 * classname:
 * description:
 * severity:
 * <p>
 * Tom Jones
 * reportedBy:
 * classname:
 * description:
 * severity:
 * <p>
 * reportedBy:
 * classname:
 * description:
 * severity:
 */
public class BugReportGenerator {
    private static final Logger LOG = Logger.getLogger(BugReportGenerator.class.getName());
    private static final String PACKAGE_TO_SCAN = "lesson10.labs.prob1.javapackage";
    private static final String REPORT_NAME = "bug_report.txt";
    private static final String REPORTED_BY = "reportedBy: ";
    private static final String CLASS_NAME = "classname: ";
    private static final String DESCRIPTION = "description: ";
    private static final String SEVERITY = "severity: ";
    private static final Set<String> names = new HashSet<>();

    public void reportGenerator() {
        List<Class<?>> classes = ClassFinder.find(PACKAGE_TO_SCAN);

        //sample code for reading annotations -- you will need to change
        //this quite a bit to solve the problem
        //Sample code below obtains a list of names of developers assigned to bugs
        String content = classes.stream().filter(cl -> cl.isAnnotationPresent(BugReport.class))
                .sorted(Comparator.comparing(cl -> cl.getAnnotation(BugReport.class).assignedTo()))
                .map(print::apply).collect(Collectors.joining("\n"));

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(REPORT_NAME))) {
            bw.write(content);
        }catch (Exception e){
            e.printStackTrace();
        }

//        StringBuilder info = new StringBuilder();
//
//        for (Class<?> cl : classes) {
//            System.out.println(cl.getName());
//            System.out.println(cl.getAnnotation(BugReport.class).assignedTo());
//            if (cl.isAnnotationPresent(BugReport.class)) {
//                StringBuilder sb = new StringBuilder();
//                BugReport annotation = (BugReport) cl.getAnnotation(BugReport.class);
//                sb.append(annotation.assignedTo()).append("\n").append("  ")
//                        .append("reportedBy: ").append(annotation.reportedBy()).append("\n").append("  ")
//                        .append("classname: ").append(annotation.getClass().getName()).append("\n").append("  ")
//                        .append("description: ").append(annotation.description()).append("\n").append("  ")
//                        .append("severity: ").append(annotation.severity()).append("\n");
//                info.append(sb);
//            }
//        }
//        System.out.println(info);

    }

    public static final Function<Class<?>, String> print = (cl) -> {
        BugReport annotation = (BugReport) cl.getAnnotation(BugReport.class);
        String name = annotation.assignedTo();
        if (names.contains(name)) {
            name = "";
        } else {
            names.add(name);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name.equals("") ? "" : "\n").append(name).append("\n").append("  ")
                .append("reportedBy: ").append(annotation.reportedBy()).append("\n").append("  ")
                .append("classname: ").append(cl.getName()).append("\n").append("  ")
                .append("description: ").append(annotation.description()).append("\n").append("  ")
                .append("severity: ").append(annotation.severity());
        return sb.toString();
    };


}

package com.maveric.core.cucumber.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.core.config.ConfigProperties;
import com.maveric.core.cucumber.reporter.pojo.After;
import com.maveric.core.cucumber.reporter.pojo.CucumberResults;
import com.maveric.core.cucumber.reporter.pojo.Element;
import com.maveric.core.cucumber.reporter.pojo.Step;
import com.maveric.core.cucumber.reporter.pojo.Tag;
import com.maveric.core.testng.listeners.DriverListener;
import com.maveric.core.testng.reporter.CustomReporter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.readFileToString;


public class ExtentBDDReportGenerator {

	private static final String SEPARATOR = System.getProperty("file.separator");
    public static String testCaseName = "";
    public static String lineSeparator = System.getProperty("line.separator");
    public static int totalFeatures = 0;
    public static int passedFeatures = 0;
    public static int totalScenarios=0;
    public static int passedScenarios=0;
    public static String featureTags = "";
    public static String featureDesc = "";
    
    //ExtentReport Related
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports html;
    static ExtentTest Feature;
    static ExtentTest scenario;

    public static void generateCucumberReport() {
    	
    	htmlReporter=new ExtentHtmlReporter(DriverListener.reportFolder+"/BDDExtentReport.html");
    	html=new ExtentReports();
    	html.attachReporter(htmlReporter);
    	
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String userDir = System.getProperty("user.dir");
            String cucumberResultsDir = userDir + "/target";

            CucumberResults[] results = objectMapper.readValue(
                    new File(cucumberResultsDir + SEPARATOR + "cucumber.json"),
                    CucumberResults[].class);  
            
            for (CucumberResults result : results) {
            	
            	if (result.keyword.equalsIgnoreCase("feature")) {
            		
            		
            		Feature=html.createTest(new GherkinKeyword("Feature"),result.name);
            		totalFeatures++;
          

                    StringBuilder builder = new StringBuilder();
                    String featureName = result.name;
                    // String featureId = result.getId();
                    // String featureDesc = result.getDescription();
                    // String featureUri = result.getUri();
                    boolean featureStatus = true;
                    List<Tag> tags = result.tags;
                    //featureTags = tags.get(0).name;

                    List<Element> elements = result.elements;
                    int scenarioCount = 0;
                    int passedScenarioCount = 0;
                    boolean bgFlag = false;
                    List<Step> bgSteps = new ArrayList<>();
                    for (Element element : elements) {
                        if (element.type.equalsIgnoreCase("background")) {
                            bgSteps.addAll(element.steps);
                        }
                        if (element.type.equalsIgnoreCase("scenario")) {
                            scenarioCount++;
                            boolean testStatus = true;
                            String scenarioName = element.name;
                            
                            scenario=Feature.createNode(new GherkinKeyword(element.type),element.name);
                            
                            List<Tag> scenarioTags=element.tags;
                            for(Tag tmp:scenarioTags) {
                            	scenario.assignCategory(tmp.name);
                            }
                            // String scenarioDesc = element.getDescription();
                            // String scenarioID = element.getId();
                            // String scenarionKeyword = element.getKeyword();
                            // int ScenarioLine = element.getLine();
                            List<After> after = element.after;
                            List<String> afterOutput = new ArrayList<>();
                            after.forEach(after1 -> afterOutput.addAll(after1.output));


//                                String duration = afterOutput.getDuration();

//                            List<Before> before = element.getBefore();
//                            Result beforeOutput = before.get(0).result;
                            List<Step> steps = new ArrayList<>();
                            steps.addAll(bgSteps);
                            steps.addAll(element.steps);

                            testCaseName = scenarioName;

                            ArrayList<String> scenarioLogs = new ArrayList<String>();
                            int stepCount = 0;
                            boolean skipNextSteps = false;

                            for (Step step : steps) {

                                // String stepLine = step.getLine();

                                String stepName = step.name;
                                String stepKeyword = step.keyword;
                                String stepResult = step.result.status;
                                List<String> output = step.output.stream().map(s -> s + "<br>").collect(Collectors.toList());
                                
                                System.out.println(step.keyword + step.name);
                                ;
                                


                                stepCount++;
                                ArrayList<String> stepLogs = new ArrayList<>(output);
                                int count = Integer.parseInt(scenarioCount + "" + stepCount);
                                String name = stepKeyword + " " + stepName;

                                if (stepResult.equalsIgnoreCase("passed")) {
                                    testStatus = true;
                                    scenario.createNode(new GherkinKeyword(step.keyword),step.name).pass("Passed");
                                    

                                } else if (stepResult.equalsIgnoreCase("failed") && !skipNextSteps) {

                                    testStatus = false;
                                    featureStatus = false;
                                    String error = step.result.errorMessage;
                                    stepLogs.add(error);
                                    scenario.createNode(new GherkinKeyword(step.keyword),step.name).fail(error);
                                    if (afterOutput.size() > 0) {
                                        stepLogs.add(String.join("", afterOutput));
                                    }
                                    
                                }
                                
                                System.out.println("Step Logs"+stepLogs);
                                String logs = String.join("", stepLogs);
                                
                                Pattern p = Pattern.compile("[a-z0-9-A-z/]*.[p&t][x&n][t&g]");
                                Matcher m = p.matcher(logs);
                                String tmp="";
                                while(m.find()) {
                                	tmp=m.group();
                                    System.out.println("----"+m.group());
                                    scenario.addScreenCaptureFromPath(tmp);
                                }
                                
                                //System.out.println("Extracted Logs"+tmp);
                                
                                //scenario.addScreenCaptureFromPath("D:\\Test\\"+tmp);
                                

                                if (testStatus) {
                                    scenarioLogs.add(CustomReporter.appendStepPass(logs, name, count).toString());
                                } else if (skipNextSteps) {
                                    scenarioLogs.add(CustomReporter.appendStepSkip(logs, name, count).toString());
                                } else {
                                    scenarioLogs.add(CustomReporter.appendStepFail(logs, name, count, scenarioName)
                                            .toString());
                                    skipNextSteps = true;
                                }
                            }

                            String logs = String.join("", scenarioLogs);

                            if (testStatus) {
                                passedScenarioCount++;
                                builder.append(CustomReporter.appendScenarioPass(logs, testCaseName));
                            } else {
                                builder.append(CustomReporter.appendScenarioFail(logs, testCaseName));

                            }
                            bgSteps.clear();
                        }
                    }

                    if (featureStatus)
                        passedFeatures++;
                    //CustomReporter.appendFeature(featureName, builder, scenarioCount, passedScenarioCount);
                    totalScenarios=scenarioCount;
                    passedScenarios=passedScenarioCount;
                    System.out.println("Total Scenarios>>>>>>"+totalScenarios+"Passed Sceanrio>>>>"+passedScenarios);   
                    html.flush();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

    }

}

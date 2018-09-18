package com.ititon.datacraw.controller;

import com.ititon.datacraw.model.Patent;
import com.ititon.datacraw.model.SearchField;
import com.ititon.datacraw.service.impl.PatenInitializer2;
import com.ititon.datacraw.service.impl.PatentInitializer;
import com.ititon.datacraw.service.impl.PatentServiceImpl;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PatentCrawController2 {

    @Autowired
    private ChromeDriverService chromeDriverService;

    @Autowired
    private PatenInitializer2 patentInitializer;

    @Autowired
    private PatentServiceImpl patentService;

    private final String systemDownloadLocation;

    {
        systemDownloadLocation = FileUtils.getUserDirectory().getPath() + "/Downloads";
    }

    private ChromeDriver chromeDriver;
    private WebDriverWait wait;
    private Actions actions;


    @GetMapping("/execute-chrome")
    public ResponseEntity executeChrome() {

        chromeDriver = new ChromeDriver(chromeDriverService);
        wait = new WebDriverWait(chromeDriver, 60, 500);
        actions = new Actions(chromeDriver);

//        chromeDriver.manage().window().fullscreen();
        chromeDriver.get("http://pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml");

        return ResponseEntity.ok().build();

    }


    @GetMapping("/open/patent/enter-login/{value}")
    public ResponseEntity enterLoginValues(@PathVariable("value") String value) {

        chromeDriver.findElement(By.id("j_username")).clear();
        chromeDriver.findElement(By.id("j_password_show")).clear();
        chromeDriver.findElement(By.id("j_validation_code")).clear();
//        chromeDriver.findElement(By.id("j_username")).sendKeys("amalka_123456789");
//        chromeDriver.findElement(By.id("j_password_show")).sendKeys("ABCDefg123456789");


        chromeDriver.findElement(By.id("j_username")).sendKeys("olegsprin96");
        chromeDriver.findElement(By.id("j_password_show")).sendKeys("Liberty700");
        chromeDriver.findElement(By.id("j_validation_code")).sendKeys(value);

        chromeDriver.findElement(By.id("wee_remember_me")).click();
//
        chromeDriver.findElement(By.cssSelector("a[class='btn btn-login']")).click();


        return ResponseEntity.ok().build();
    }


    private void fillSearchPanel() throws InterruptedException {


        chromeDriver.get("http://pss-system.gov.cn/sipopublicsearch/patentsearch/tableSearch-showTableSearchIndex.shtml");

        Actions actions = new Actions(chromeDriver);

        Thread.sleep(3000);
        chromeDriver.findElements(By.name("inventiontype")).forEach(element -> {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (!element.isSelected()) {
                actions.click(element).build().perform();
            }

        });

        chromeDriver.findElement(By.id("configTableItemBtnId")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='tableItemDiv']")));
        List<WebElement> elements = chromeDriver.findElements(By.cssSelector("div[class='tableItemDiv']"));
        System.out.println(elements.size());

        for (WebElement element : elements) {

            try {
                element.findElement(By.cssSelector("input[value='IVDB103']"));
                WebElement e1 = element.findElement(By.cssSelector("input[type='checkbox']"));
                wait.until(ExpectedConditions.elementToBeClickable(e1));
                if (!e1.isSelected()) {
                    actions.click(e1).build().perform();
                }
            } catch (Throwable e) {

            }

            try {
                element.findElement(By.cssSelector("input[value='IVDB022']"));
                WebElement e2 = element.findElement(By.cssSelector("input[type='checkbox']"));
                wait.until(ExpectedConditions.elementToBeClickable(e2));
                if (!e2.isSelected()) {
                    actions.click(e2).build().perform();
                }
            } catch (Throwable e) {

            }

        }

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-id='保存']")));

        WebElement button = chromeDriver.findElement(By.cssSelector("button[data-id='保存']"));
        actions.click(button).build().perform();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tableSearchItemIdIVDB022")));
        chromeDriver.findElement(By.id("tableSearchItemIdIVDB076")).sendKeys("CN");

        chromeDriver.findElement(By.id("tableSearchItemIdIVDB075")).sendKeys("CN");

        chromeDriver.findElement(By.id("tableSearchItemIdIVDB022")).sendKeys("贵州");

        WebElement tableSearch = chromeDriver.findElement(By.cssSelector("a[onclick='excuteTableSearch();']"));
        actions.click(tableSearch).build().perform();

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultMode")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));

    }


    @GetMapping("/patent/search")
    public ResponseEntity patentSearch() throws InterruptedException, IOException {

        AtomicInteger page = new AtomicInteger(9155);
        fillSearchPanel();
        savePatents(page);

        return ResponseEntity.ok().build();

    }

    private void savePatents(AtomicInteger page) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
            if (page.get() != 1) {
                Actions actions = new Actions(chromeDriver);
                //пагинация
                WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
                //кнопки
                List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));
                Optional<WebElement> pageSearchButton = paginationButtons.stream()
                        .filter(b -> !Objects.equals(null, b.getText()) && Objects.equals(b.getText(), "确定"))
                        .findAny();


                WebElement inputPageElement = chromeDriver.findElement(By.id("txt"));
                inputPageElement.clear();

//                int i = new Random().nextInt(11189 - 1) + 1;
                inputPageElement.sendKeys(String.valueOf(page.get()));
                WebElement button = pageSearchButton.get();
                wait.until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                actions.click(button).build().perform();
                Thread.sleep(15000);
            }
            savePatentsFromPage(page);
        } catch (Throwable e) {
            e.printStackTrace();
            page.getAndDecrement();
            page.set(page.get());
            savePatents(page);
        }
    }

    private Optional<WebElement> getNextButton() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));
        //пагинация
        WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
        //кнопки
        List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));

        return paginationButtons.stream().filter(b -> !Objects.equals(null, b.getText())
                && Objects.equals(b.getText(), "下一页")).findAny();
    }

    private void savePatentsFromPage(AtomicInteger page) throws InterruptedException {
        boolean isNotEnd = true;
        while (isNotEnd) {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultMode")));

            WebElement pagination = chromeDriver.findElement(By.cssSelector("div[class='page_bottom']"));
            //кнопки
//            List<WebElement> paginationButtons = pagination.findElements(By.tagName("a"));
//            Optional<WebElement> pageSearchButton = paginationButtons.stream()
//                    .filter(b -> !Objects.equals(null, b.getText()) && Objects.equals(b.getText(), "确定"))
//                    .findAny();
//
//
//            WebElement inputPageElement = chromeDriver.findElement(By.id("txt"));

            Optional<WebElement> nextButton = getNextButton();

            List<WebElement> hiddenDivsWithValues = chromeDriver.findElement(By.id("resultMode"))
                    .findElements(By.cssSelector("div[class='item']"));

            for (WebElement element : hiddenDivsWithValues) {
                Map<String, String> hiddenNamesValues = new LinkedHashMap<>();
                List<WebElement> divInputs = element.findElements(By.tagName("input"));
                for (WebElement input : divInputs) {
                    String name = input.getAttribute("name");
                    String value = input.getAttribute("value");
                    hiddenNamesValues.put(name, value);
                }

                List<WebElement> tableElements =
                        element.findElement(By.cssSelector("div[class='item-content-body left']"))
                                .findElements(By.tagName("p"));

                for (WebElement tableElement : tableElements) {
                    String text = tableElement.getText();
                    String[] keysValues = text.split(":");
                    String key = keysValues[0].trim();
                    SearchField field = SearchField.findByValue(key);
                    String value = null;

                    System.out.println("TEXT " + text);
                    if (keysValues.length > 1) {
                        value = keysValues[1].trim();
                    }
                    if (Objects.equals(field, SearchField.AGENT)) {
                        hiddenNamesValues.put("agent", value);
                    } else if (Objects.equals(field, SearchField.AGENCY)) {
                        hiddenNamesValues.put("agency", value);
                    } else if (Objects.equals(field, SearchField.LA_NUM)) {
                        hiddenNamesValues.put("lanum", value);
                    }
                }
                patentInitializer.initAndSave(hiddenNamesValues);
            }

            if (nextButton.isPresent()) {
                WebElement button = nextButton.get();
                wait.until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                actions.click(button).build().perform();

                page.getAndIncrement();

//                int i = new Random().nextInt(11189 - 1) + 1;
//                inputPageElement.sendKeys(String.valueOf(i));
                System.out.println("PAGE IS " + page);
                Thread.sleep(15000);
            } else {
                isNotEnd = false;
            }
        }
    }


    @PreDestroy
    public void destroy() {
        chromeDriver.quit();
    }
}

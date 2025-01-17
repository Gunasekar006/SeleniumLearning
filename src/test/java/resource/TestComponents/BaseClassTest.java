package resource.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.pageobjects.HomePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseClassTest {
    //    public WebDriver driver;
    public HomePage homePage;
    XSSFWorkbook workBook;
    XSSFSheet sheet;
    XSSFCell cell;
    XSSFRow row;
    Properties prop;
    FileInputStream fis;


    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        fis = new FileInputStream("src/main/java/SampleProjects/resource/Application.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        System.out.println("Test will run in " + browserName);
        if (browserName.contains("chrome") && driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver.set(new ChromeDriver(option));
        } else if (browserName.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions option = new FirefoxOptions();
            option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver.set(new FirefoxDriver(option));
        }

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get().manage().window().maximize();
        return driver.get();

    }

    public String getScreenshot(String testName, WebDriver driver) throws IOException {
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File src = ts.getScreenshotAs(OutputType.FILE);
//        File dest = new File(System.getProperty("user.dir") + "//reports//" + testName + "//.png");
//        FileUtils.copyFile(src, dest);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmmss");
        String strDate = formatter.format(date);

        File dest = new File(System.getProperty("user.dir") + "//reports//" + testName + "//" + strDate + ".png");
        FileUtils.copyFile(src, dest);
        System.out.println(System.getProperty("user.dir") + "//reports//" + testName + "//" + strDate + ".png");
        return System.getProperty("user.dir") + "//reports//" + testName + "//" + strDate + ".png";

    }

    public Map<String, List<String>> getDataFromExcel() throws IOException {

        File file = new File("D://Users//Guna.xlsx/");
        FileInputStream fis = new FileInputStream(file);
        workBook = new XSSFWorkbook(fis);
        sheet = workBook.getSheet("Personal exp");
        row = sheet.getRow(0);

        Map<String, List<String>> map = new LinkedHashMap<>();
        String key = null;
        List<String> value;

        for (int i = 0; i < row.getLastCellNum(); i++) {
            value = new ArrayList<>();
            for (int j = 0; j < sheet.getLastRowNum(); j++) {
                key = sheet.getRow(0).getCell(i).getStringCellValue();
                value.add(sheet.getRow(j + 1).getCell(i).getStringCellValue());
            }
            map.put(key, value);
        }

//        System.out.println(map.entrySet());

//        for(Map.Entry<String, List<String>> entry: map.entrySet()){
//            System.out.println(entry.getKey()+" : "+entry.getValue());
//            System.out.println("____________________");
//        }
        return map;
    }

    @BeforeMethod
//    @Parameters("browser")
    public HomePage OpenURL() throws IOException {
        prop = new Properties();
        fis = new FileInputStream("src/main/java/SampleProjects/resource/Application.properties");
        prop.load(fis);

//      driver = initializeDriver();
        homePage = new HomePage(initializeDriver());
        homePage.goTo(prop.getProperty("url"));
        return homePage;
    }

    @AfterMethod
    public void closeBrowser() {
        driver.get().quit();
    }


}

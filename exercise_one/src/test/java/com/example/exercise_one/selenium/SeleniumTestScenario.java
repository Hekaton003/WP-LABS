package com.example.exercise_one.selenium;

import com.example.exercise_one.model.Category;
import com.example.exercise_one.model.Manufacturer;
import com.example.exercise_one.model.User;
import com.example.exercise_one.model.enums.Role;
import com.example.exercise_one.service.CategoryService;
import com.example.exercise_one.service.ManufactureService;
import com.example.exercise_one.service.ProductService;
import com.example.exercise_one.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTestScenario {
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufactureService manufacturerService;

    @Autowired
    ProductService productService;

    private HtmlUnitDriver driver;

    private static boolean dataInitialized;

    private static Category c1;
    private static Category c2;
    private static Manufacturer m1;
    private static Manufacturer m2;

    private static User user;
    private static User admin;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);

        c1 = categoryService.create("c1", "c1 desc");
        c2 = categoryService.create("c2", "c2 desc");
        m1 = manufacturerService.save("m1", "m1 address").get();
        m2 = manufacturerService.save("m2", "m2 address").get();

        user = userService.register("user1", "user", "user", "user", "user", Role.ROLE_USER);
        admin = userService.register("admin1", "admin", "admin", "admin", "admin", Role.ROLE_ADMIN);

    }
    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Test
    public void testScenario() throws Exception{
        this.driver = new HtmlUnitDriver(true);
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        CategoriesPage categoriesPage = LoginPage.doLogin(this.driver, loginPage, "user1", "user");
        categoriesPage.assertElements(5);
    }

}

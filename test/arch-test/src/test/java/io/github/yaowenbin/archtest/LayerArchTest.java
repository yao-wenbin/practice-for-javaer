package io.github.yaowenbin.archtest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * @Author yaowenbin
 * @Date 2023/7/28
 */
@AnalyzeClasses(packages = "io.github.yaowenbin.archtest")
public class LayerArchTest {

    String packageName = "io.github.yaowenbin.archtest";


    @ArchTest
    ArchRule controllerShouldNotAccessMapperDirectly = noClasses()
            .should()
            .accessClassesThat()
            .resideInAPackage("..persistence..");


    @ArchTest
    public ArchRule controllerShouldLocatedInApiPackage = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage(packageName+ ".api");
}

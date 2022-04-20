module com.company {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.company to javafx.fxml;
    opens com.company.src.models.magazine;
    opens com.company.src.models.individual.productFlyweight;

    exports com.company;

}
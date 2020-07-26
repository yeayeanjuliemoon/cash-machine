package rocks.zipcode.atm;

import javafx.scene.control.*;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();

    private TextField email = new TextField();


    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(40);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        FlowPane flowpane = new FlowPane();

        Button emailSubmit = new Button("Email Login");
        emailSubmit.setOnAction(e -> {
            String emailAddress = email.getText();
            cashMachine.login(emailAddress);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnSubmit = new Button("Set Account ID");
        flowpane.getChildren().add(btnSubmit);
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");


        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());

            flowpane.getChildren().add(btnDeposit);
            flowpane.getChildren().add(btnWithdraw);
            flowpane.getChildren().remove(btnSubmit);
            flowpane.getChildren().remove(emailSubmit);
        });

        MenuItem basic =new MenuItem("Basic");
        MenuItem premium = new MenuItem("Premium");
        MenuItem ksa = new MenuItem("KSA");
        MenuButton menuButton =new MenuButton("Account Type",null,basic,premium,ksa);
        Label accountselected = new Label("No Account selected");
        basic.setOnAction(e ->{
            accountselected.setText("Basic Account");


        });
        premium.setOnAction(e ->{
            accountselected.setText("Premium Account");

        });
        ksa.setOnAction(e ->{
            accountselected.setText("Kids Savings Account");

        });



        //Button btnDeposit = new Button("Deposit");

        btnDeposit.setOnAction(e -> {

            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());

        });

//        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {

            int amount = Integer.parseInt(field.getText());

            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });



        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());

            flowpane.getChildren().add(btnSubmit);
            flowpane.getChildren().add(emailSubmit);
            flowpane.getChildren().remove(btnWithdraw);
            flowpane.getChildren().remove(btnDeposit);

        });

//        FlowPane flowpane = new FlowPane();
        field.setText("Field Prefill");
        email.setText("Email Address"); // you can set the prefilled text with this method


        flowpane.getChildren().add(emailSubmit);
//        flowpane.getChildren().add(btnSubmit);
//        flowpane.getChildren().add(btnDeposit);
//        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);

        flowpane.getChildren().add(menuButton);
        vbox.getChildren().addAll(field, email,flowpane,accountselected, areaInfo);

        return vbox;
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

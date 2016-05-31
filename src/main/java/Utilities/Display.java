package Utilities;

import javafx.application.Application;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

/**
 * Created by Michael on 5/28/2016.
 */
public class Display extends Application
{
    public static void displayMessage(String message)
    {
        //        Platform.runLater(new Runnable() {
        //            @Override
        //            public void run()
        //            {
        //                Alert alert = new Alert(AlertType.INFORMATION);
        //                alert.setContentText(message);
        //                alert.showAndWait();
        //            }
        //        });
        System.out.println(message);

    }

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running. <p> <p> NOTE: This method is called on the
     * JavaFX Application Thread. </p>
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set. The
     *                     primary stage will be embedded in the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be primary stages and will
     *                     not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ButtonType okButton = new ButtonType("Quit", ButtonBar.ButtonData.OK_DONE);
        Dialog dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.setContentText("Are you sure you want to quit?");
        boolean disabled = false;
        dialog.getDialogPane().lookupButton(okButton).setDisable(disabled);

        dialog.showAndWait().filter(result -> result == ButtonType.OK);

    }
}

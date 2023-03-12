package controller;

import javafx.event.ActionEvent;
import utils.HelperFunctions;

import java.io.IOException;

public class UpdateApptController {
	public void updateApptBtnClick(ActionEvent actionEvent) {
	}

	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}

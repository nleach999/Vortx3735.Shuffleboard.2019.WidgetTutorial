package edu.vortx3735.MyPlugin;

import edu.wpi.first.shuffleboard.api.data.types.NumberType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import java.util.LinkedList;


@Description(dataTypes = { NumberType.class }, name = "My First Data Bound Widget")
@ParametrizedController(value = "MyDataBoundWidget.fxml")
public class MyDataBoundWidget extends SimpleAnnotatedWidget<Number> {

	@FXML
	private AnchorPane _thePane;

	@FXML
	Gauge _theGauge;

	@Override
	public Pane getView() {

		_theGauge.valueProperty().bind(dataProperty());

		return _thePane;
	}

	@Override
	public java.util.List<edu.wpi.first.shuffleboard.api.prefs.Group> getSettings() {

		LinkedList<Group> propertyList = new LinkedList<Group>();

		propertyList.add(Group.of("Display Limits"
		, Setting.of("Min Value", _theGauge.minValueProperty())
		, Setting.of("Max Value", _theGauge.maxValueProperty())
		, Setting.of("Visible", _theGauge.valueVisibleProperty())
		// Shuffleboard appears to have a problem with this boolean value, leaving it out.
		// , Setting.of("Animated", _theGauge.animatedProperty())
		));

		propertyList.add(Group.of("Tick Lable Display"
		, Setting.of("Tick Label Orientation", _theGauge.tickLabelOrientationProperty())
		, Setting.of("Tick Label Color", _theGauge.tickLabelColorProperty())
		, Setting.of("Tick Lavel Location", _theGauge.tickLabelLocationProperty())
		));

		propertyList.add(Group.of("Needle Display"
		, Setting.of("Needle Type", _theGauge.needleTypeProperty())
		, Setting.of("Needle Size", _theGauge.needleSizeProperty())
		));

		propertyList.add(Group.of("Tick Display"
		, Setting.of("Tick Shape", _theGauge.needleShapeProperty())
		, Setting.of("Tick Color", _theGauge.tickMarkColorProperty())
		, Setting.of("Tick Major", _theGauge.majorTickMarkTypeProperty())
		, Setting.of("Tick Minor", _theGauge.minorTickMarkTypeProperty())
		));

		return propertyList;
	  }

}

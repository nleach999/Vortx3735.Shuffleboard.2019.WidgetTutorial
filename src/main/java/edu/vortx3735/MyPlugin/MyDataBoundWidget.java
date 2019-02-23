package edu.vortx3735.MyPlugin;

import edu.wpi.first.shuffleboard.api.data.types.NumberType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.TickLabelLocation;
import eu.hansolo.medusa.TickLabelOrientation;
import eu.hansolo.medusa.TickMarkType;
import eu.hansolo.medusa.Gauge.NeedleShape;
import eu.hansolo.medusa.Gauge.NeedleSize;
import eu.hansolo.medusa.Gauge.NeedleType;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
		, Setting.of("Min Value", _theGauge.minValueProperty(), Double.class)
		, Setting.of("Max Value", _theGauge.maxValueProperty(), Double.class)
		, Setting.of("Visible", _theGauge.valueVisibleProperty(), Boolean.class)
		, Setting.of("Animated", _theGauge.animatedProperty(), Boolean.class)
		));

		propertyList.add(Group.of("Tick Lable Display"
		, Setting.of("Tick Label Orientation", _theGauge.tickLabelOrientationProperty(), TickLabelOrientation.class)
		, Setting.of("Tick Label Color", _theGauge.tickLabelColorProperty(), Color.class)
		, Setting.of("Tick Lavel Location", _theGauge.tickLabelLocationProperty(), TickLabelLocation.class)
		));

		propertyList.add(Group.of("Needle Display"
		, Setting.of("Needle Type", _theGauge.needleTypeProperty(), NeedleType.class)
		, Setting.of("Needle Size", _theGauge.needleSizeProperty(), NeedleSize.class)
		));

		propertyList.add(Group.of("Tick Display"
		, Setting.of("Tick Shape", _theGauge.needleShapeProperty(), NeedleShape.class)
		, Setting.of("Tick Color", _theGauge.tickMarkColorProperty(), Color.class)
		, Setting.of("Tick Major", _theGauge.majorTickMarkTypeProperty(), TickMarkType.class)
		, Setting.of("Tick Minor", _theGauge.minorTickMarkTypeProperty(), TickMarkType.class)
		));

		return propertyList;
	  }

}

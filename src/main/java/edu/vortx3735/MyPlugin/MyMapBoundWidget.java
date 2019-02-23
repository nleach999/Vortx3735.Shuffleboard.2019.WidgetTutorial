package edu.vortx3735.MyPlugin;

import edu.wpi.first.shuffleboard.api.data.MapData;
import edu.wpi.first.shuffleboard.api.data.types.MapType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import java.util.LinkedList;

@Description(dataTypes = { MapType.class }, name = "My Map Bound Widget")
@ParametrizedController(value = "MyMapBoundWidget.fxml")
public class MyMapBoundWidget extends SimpleAnnotatedWidget<MapData> implements ChangeListener<MapData> {

	@FXML
	private AnchorPane _thePane;

	@FXML
	private Gauge _topGauge;

	@FXML
	private Gauge _middleGauge;

	@FXML
	private Gauge _bottomGauge;

	private final SimpleStringProperty _topKey = new SimpleStringProperty(this, "topGaugeKey", "");
	private final SimpleStringProperty _middleKey = new SimpleStringProperty(this, "middleGaugeKey", "");
	private final SimpleStringProperty _bottomKey = new SimpleStringProperty(this, "bottomGaugeKey", "");
	
	
	public MyMapBoundWidget ()
	{
		// Export the properties to set the key value to bind to each gauge.
		
		// Set up a listener that gets triggered every time the map is updated.
		dataProperty().addListener(this);
	}

	
	public String getTopGaugeKey ()
	{
		return _topKey.getValue();
	}
	
	public void setTopGaugeKey (String top)
	{
		_topKey.setValue(top);
	}
	
	public String getMiddleGaugeKey ()
	{
		return _middleKey.getValue();
	}
	
	public void setMiddleGaugeKey (String middle)
	{
		_middleKey.setValue(middle);
	}
	

	public String getBottomGaugeKey ()
	{
		return _bottomKey.getValue();
	}
	
	public void setBottomGaugeKey (String bottom)
	{
		_bottomKey.setValue(bottom);
	}
	
	@Override
	public java.util.List<edu.wpi.first.shuffleboard.api.prefs.Group> getSettings() {

		LinkedList<Group> propertyList = new LinkedList<Group>();

		propertyList.add(Group.of("Map Key Values"
		, Setting.of("Top Key", "The key value in the map to assign to the top gauge.", _topKey, String.class)
		, Setting.of("Middle Key", "The key value in the map to assign to the middle gauge.", _middleKey, String.class)
		, Setting.of("Bottom Key", "The key value in the map to assign to the bottom gauge.", _bottomKey, String.class)
		));


		return propertyList;
	  }


	@Override
	public Pane getView() {

		return _thePane;
	}

	
	/**
	 * Sets the value of the specified gauge control with some validation to make sure the gauge control
	 * is assigned to a value in the map.
	 * 
	 * @param g The gauge control to set the value in.
	 * @param key The key value from the map to find the data to set in the gauge.
	 */
	private void setValue (Gauge g, String key)
	{
		if (key != null && !key.isEmpty() && dataProperty().get().get(key) != null)
			g.valueProperty().set((double) dataProperty().get().get(key));
	}
	
	@Override
	public void changed(ObservableValue<? extends MapData> arg0, MapData arg1, MapData arg2) {
		
		// Invoked when the map changes.  It gets the value from the map by the key value,
		// then updates the appropriate gauge.
		
		setValue (_topGauge, _topKey.getValue());
		setValue (_middleGauge, _middleKey.getValue());
		setValue (_bottomGauge, _bottomKey.getValue());

	}

}

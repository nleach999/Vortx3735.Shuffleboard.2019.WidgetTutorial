package edu.vortx3735.MyPlugin;

import java.util.List;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "VorTX 3735", name = "MyPlugin", summary = "A test plugin", version = "1.0.0")
public class App extends Plugin {
	@Override
	@SuppressWarnings("all")
	public List<ComponentType> getComponents() {

		return ImmutableList.of(WidgetType.forAnnotatedWidget(MyWidget.class),
				WidgetType.forAnnotatedWidget(MyDataBoundWidget.class),
				WidgetType.forAnnotatedWidget(MyMapBoundWidget.class));
	}
}

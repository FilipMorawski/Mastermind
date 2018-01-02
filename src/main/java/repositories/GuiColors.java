package repositories;

import java.awt.Color;

public class GuiColors {

	private static final Color BGCOLOR;
	private static final Color MARKCOLOR;

	static {
		BGCOLOR = new Color(179, 229, 252);
		MARKCOLOR = new Color(179, 229, 252);

	}

	public static Color getBGCOLOR() {
		return BGCOLOR;
	}

	public static Color getMARKCOLOR() {
		return MARKCOLOR;
	}
}

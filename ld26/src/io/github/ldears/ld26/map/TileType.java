package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public enum TileType {

	EMPTY(""),

	CEIL("ceil"),
	WALL_LEFT("wall_left"),
	WALL_RIGHT("wall_right"),
	WALL_CLB("wall_corner_left_bottom"),
	WALL_CRB("wall_corner_right_bottom"),
	WALL_MC("wall_middle_ceil"),
	WALL_MD("wall_middle"),
	WALL_X("wall_x"),

	IN_DOOR_T("inner_door_top"),
	IN_DOOR_B("inner_door_bottom"),

	DOOR_T("door_top"),
	DOOR_B("door_bottom"),
	
	BC_TL("bookcase_top_left"),
	BC_TR("bookcase_top_right"),
	BC_ML("bookcase_middle_left"),
	BC_MR("bookcase_middle_right"),
	BC_BL("bookcase_bottom_left"),
	BC_BR("bookcase_bottom_right"),
	
	SO_L("sofa_left"),
	SO_R("sofa_right"),
	
	TV_T("tv_top"),
	TV_B("tv_bottom"),
	
	COMP_TL("comp_top_left"),
	COMP_TR("comp_top_right"),
	COMP_BL("comp_bottom_left"),
	COMP_BR("comp_bottom_right"),
	
	BOX("box"),
	
	BUCKT("bucket"),
	
	BATH_L("bath_left"),
	BATH_R("bath_right"),
	
	TO_T("toilet_top"),
	TO_B("toilet_bottom"),
	
	CLK_T("clock_top"),
	CLK_M("clock_middle"),
	CLK_B("clock_bottom"),
	
	CR_TL("comp_top_left"),
	CR_TR("comp_top_right"),
	CR_TML("comp_top_middle_left"),
	CR_TMR("comp_top_middle_right"),
	CR_BL("comp_bottom_left"),
	CR_BR("comp_bottom_right"),
	CR_BML("comp_bottom_middle_left"),
	CR_BMR("comp_bottom_middle_right"),

	CKR_T("cooker_top"),
	CKR_B("cooker_bottom"),
	
	FRI_T("fridge_top"),
	FRI_B("fridge_bottom"),
	
	MW_OV("microwave_oven"),
	MIN_T("minitable"),
	
	;

	public static final int PLAYER_WIDTH 		= 2*16;
	public static final int WALL_MIDDLE_WIDTH 	= 8;

	public final String name;
	public final int index;

	private TileType(String name) {
		this.name = name;

		index = ordinal() - 1;
	}

	public static TileType last() {
		TileType[] values = values();
		return values[values.length - 1];
	}

	public static int count() {
		return values().length - 1;
	}
}

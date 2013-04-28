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
	
	BC_TL("bookshelf_tl"),
	BC_TR("bookshelf_tr"),
	BC_ML("bookshelf_ml"),
	BC_MR("bookshelf_mr"),
	BC_BL("bookshelf_bl"),
	BC_BR("bookshelf_br"),
	
	SO_L("sofa_l"),
	SO_R("sofa_r"),
	
	TV_T("tv_t"),
	TV_B("tv_b"),
	
	COMP_TL("comp_tl"),
	COMP_TR("comp_tr"),
	COMP_BL("comp_bl"),
	COMP_BR("comp_br"),
	
	BOX("catbox"),
	
	BUCKT("bucket"),
	
	BATH_L("bath_l"),
	BATH_R("bath_r"),
	
	TO_T("toilet_t"),
	TO_B("toilet_b"),
	
	CLK_T("clock_t"),
//	CLK_M("clock_m"),
	CLK_B("clock_b"),
	
	CR_TL("car_tl"),
	CR_TR("car_tr"),
	CR_TML("car_tml"),
	CR_TMR("car_tmr"),
	CR_BL("car_bl"),
	CR_BR("car_br"),
	CR_BML("car_bml"),
	CR_BMR("car_bmr"),

	CKR_T("coocker_t"),
	CKR_B("coocker_b"),
	
	FRI_T("refrigerator_t"),
	FRI_B("refrigerator_b"),
	
	MW_OV("microoven"),
	MIN_T("minitable"),
	
	BED_L("bed_l"),
	BED_R("bed_r"),
	
	WDB_T("wardrobe_t"),
	WDB_B("wardrobe_b"),

	GC_T("guitar_case_t"),
	GC_B("guitar_case_b"),

	PHONE("phone"),
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

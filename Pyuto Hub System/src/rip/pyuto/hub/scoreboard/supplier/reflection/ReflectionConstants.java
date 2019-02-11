package rip.pyuto.hub.scoreboard.supplier.reflection;

import java.util.Collection;

import rip.pyuto.hub.reflection.Reflection;

public class ReflectionConstants {

	public static final Class<?> SCOREBOARD_OBJECTIVE_CLASS = Reflection
			.getMinecraftClass("PacketPlayOutScoreboardObjective");
	public static final Reflection.ConstructorInvoker SCOREBOARD_OBJECTIVE_CONSTRUCTOR = Reflection
			.getConstructor(SCOREBOARD_OBJECTIVE_CLASS);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_OBJECTIVE_NAME = Reflection
			.getField(SCOREBOARD_OBJECTIVE_CLASS, String.class, 0);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_OBJECTIVE_TITLE = Reflection
			.getField(SCOREBOARD_OBJECTIVE_CLASS, String.class, 1);
	public static final Reflection.FieldAccessor<Integer> SCOREBOARD_OBJECTIVE_ACTION = Reflection
			.getField(SCOREBOARD_OBJECTIVE_CLASS, Integer.TYPE, 0);
	public static final Class<?> SCOREBOARD_DISPLAY_OBJECTIVE_CLASS = Reflection
			.getMinecraftClass("PacketPlayOutScoreboardDisplayObjective");
	public static final Reflection.ConstructorInvoker SCOREBOARD_DISPLAY_OBJECTIVE_CONSTRUCTOR = Reflection
			.getConstructor(SCOREBOARD_DISPLAY_OBJECTIVE_CLASS);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_DISPLAY_OBJECTIVE_NAME = Reflection
			.getField(SCOREBOARD_DISPLAY_OBJECTIVE_CLASS, String.class, 0);
	public static final Reflection.FieldAccessor<Integer> SCOREBOARD_DISPLAY_OBJECTIVE_POSITION = Reflection
			.getField(SCOREBOARD_DISPLAY_OBJECTIVE_CLASS, Integer.TYPE, 0);
	public static final Class<?> SCOREBOARD_TEAM_CLASS = Reflection.getMinecraftClass("PacketPlayOutScoreboardTeam");
	public static final Reflection.ConstructorInvoker SCOREBOARD_TEAM_CONSTRUCTOR = Reflection
			.getConstructor(SCOREBOARD_TEAM_CLASS);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_TEAM_NAME = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, String.class, 0);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_TEAM_DISPLAY_NAME = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, String.class, 1);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_TEAM_PREFIX = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, String.class, 2);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_TEAM_SUFFIX = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, String.class, 3);
	@SuppressWarnings("rawtypes")
	public static final Reflection.FieldAccessor<Collection> SCOREBOARD_TEAM_PLAYERS = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, Collection.class, 0);
	public static final Reflection.FieldAccessor<Integer> SCOREBOARD_TEAM_ACTION = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, Integer.TYPE, 0);
	public static final Reflection.FieldAccessor<Integer> SCOREBOARD_TEAM_OPTIONS = Reflection
			.getField(SCOREBOARD_TEAM_CLASS, Integer.TYPE, 1);
	public static final Class<?> SCOREBOARD_SCORE_CLASS = Reflection.getMinecraftClass("PacketPlayOutScoreboardScore");
	public static final Reflection.ConstructorInvoker SCOREBOARD_SCORE_CONSTRUCTOR = Reflection
			.getConstructor(SCOREBOARD_SCORE_CLASS);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_SCORE_NAME = Reflection
			.getField(SCOREBOARD_SCORE_CLASS, String.class, 0);
	public static final Reflection.FieldAccessor<String> SCOREBOARD_SCORE_OBJECTIVE = Reflection
			.getField(SCOREBOARD_SCORE_CLASS, String.class, 1);
	public static final Reflection.FieldAccessor<Integer> SCOREBOARD_SCORE_SCORE = Reflection
			.getField(SCOREBOARD_SCORE_CLASS, Integer.TYPE, 0);
	public static final Reflection.FieldAccessor<?> SCOREBOARD_SCORE_ACTION = Reflection
			.getField(SCOREBOARD_SCORE_CLASS, Integer.TYPE, 1);

}

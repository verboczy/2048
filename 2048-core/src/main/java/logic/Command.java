package logic;

public enum Command {
    UP, DOWN, RIGHT, LEFT, EXIT, NEW_GAME;

    public String getLowerCaseName() {
        return this.name().toLowerCase();
    }
}

package enums;

public enum LogLevel {
    DEBUG(1), INFO(2), WARNING(3), ERROR(4);

    private final int level;
    LogLevel(int level){
        this.level=level;
    }

    public int getLevel() {
        return level;
    }
}

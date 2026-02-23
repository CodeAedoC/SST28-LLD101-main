public interface PersistableStore {
    void save(String name, String content);

    int countLines(String name);
}

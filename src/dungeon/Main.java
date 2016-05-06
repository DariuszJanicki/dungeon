package dungeon;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class Main {

    /* ========== STATIC ========== */
    public static final ObjectMapper mapper = new ObjectMapper();

    /* ========== MAIN ========== */
    public static void main(String[] args) {
        new Thread(new Runner()).start();
    }
}
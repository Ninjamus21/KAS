package Storage;

import Model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple central manager for the current Tilmelding (registration).
 * Use createNew(konference) to start a new registration workflow,
 * getCurrent() to access it, and commit(...) to attach the Deltager
 * and ensure the Deltager is stored centrally.
 */
public class TilmeldingManager {
    private static final ArrayList<Tilmelding> tilmeldings = new ArrayList<>();
    private static Tilmelding current;

    public static Tilmelding createNew(Konference konference) {
        if (konference == null) throw new IllegalArgumentException("konference cannot be null");
        // create a tilmelding via Konference so the Konference knows about it
        Tilmelding t = konference.createTilmelding(null);
        tilmeldings.add(t);
        current = t;
        return t;
    }

    public static Tilmelding getCurrent() {
        return current;
    }

    public static List<Tilmelding> getAllTilmeldings() {
        return Collections.unmodifiableList(tilmeldings);
    }

    public static void commitCurrent(Deltager deltager) {
        commit(current, deltager);
    }

    public static void commit(Tilmelding tilmelding, Deltager deltager) {
        if (tilmelding == null || deltager == null) return;
        tilmelding.setDeltager(deltager);
        // ensure deltager is in central storage
        Storage.addDeltager(deltager);
    }

    public static void clearCurrent() {
        current = null;
    }
}

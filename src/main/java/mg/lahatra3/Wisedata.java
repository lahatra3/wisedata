package mg.lahatra3;

import mg.lahatra3.utils.Env4j;

public class Wisedata {
    public static void main(String[] args) {
        Env4j env4j = new Env4j();
        env4j.load();

        WisedataRunner wisedataRunner = new WisedataRunner();
        wisedataRunner.run();
    }
}
package mg.lahatra3;

import mg.lahatra3.utils.EnvProperties;

public class Wisedata {
    public static void main(String[] args) {
        EnvProperties.load();
        WisedataRunner wisedataRunner = new WisedataRunner();
        wisedataRunner.run();
    }
}